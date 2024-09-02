<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import Dropdown from 'primevue/dropdown';
import Button from 'primevue/button';
import Divider from 'primevue/divider';

import { useHouseStore } from '@/stores/house';
import { twMerge } from 'tailwind-merge';
import { useUserStore } from '@/stores/user';
import { useDialog } from 'primevue/usedialog';
import LoginModal from '@/components/modal/LoginModal.vue';
import AroundAptInfo from '@/components/main/AroundAptInfo.vue';
import AptDealListModal from '@/components/modal/AptDealListModal.vue';
import { getLikeCount, likeApt, unlikeApt } from '@/api/like';
import HouseSearch from '@/components/main/HouseSearch.vue';
import router from '@/router';
import { onBeforeRouteUpdate } from 'vue-router';
import { getAptDealList, getAptDetail } from '@/api/house';

const houseStore = useHouseStore();
const userStore = useUserStore();
const { setSelectedHouseId, setSelectedHouse, setDealList, setLikeCount } = houseStore;
const dialog = useDialog();

const openLoginModal = () => {
  dialog.open(LoginModal, {
    props: {
      style: {
        width: '20rem'
      },
      modal: true,
      header: '로그인',
      draggable: false
      // dismissableMask: true
    },

    onClose: () => {}
  });
};

const openDealListModal = () => {
  dialog.open(AptDealListModal, {
    props: {
      style: {},
      modal: true,
      draggable: false,
      header: ''
    }
  });
};

const recentDeal = ref(null);
const selectedSize = ref(null);

const likeCount = computed(() => {
  return houseStore.likeCount < 100 ? houseStore.likeCount : '99+';
});

const filteredDealList = computed(() => {
  if (selectedSize.value === null) return [];
  return houseStore.dealList.filter((deal) => deal.size === selectedSize.value);
});

const address = computed(() => {
  if (houseStore.selectedHouse === null) return '';
  const { sido, gugun, dong, jibun } = houseStore.selectedHouse;
  return `${sido} ${gugun} ${dong} ${jibun}`;
});

const price = computed(() => {
  if (recentDeal.value === null) return '';

  const { dealAmount } = recentDeal.value;
  if (dealAmount < 10000) {
    return `${dealAmount.toLocaleString()}만원`;
  } else {
    return `${Math.floor(dealAmount / 10000)}억 ${(dealAmount % 10000).toLocaleString()}만원`;
  }
});

const aptAge = computed(() => {
  if (houseStore.selectedHouse === null) return '';
  const { buildYear } = houseStore.selectedHouse;
  const currentYear = new Date().getFullYear();
  return currentYear - buildYear + 1 + '년차';
});

const sizeOptions = computed(() => {
  if (houseStore.dealList === null) return [];
  const set = new Set();
  houseStore.dealList.forEach((deal) => {
    set.add(deal.size);
  });
  return [...set].sort((a, b) => a - b);
});

const closeAptDdtail = () => {
  houseStore.setSelectedHouseId(null);
  houseStore.setSelectedHouse(null);
  router.push('/');
};

const onSelectSize = (size) => {
  selectedSize.value = size;
};

const onClickZzim = () => {
  if (!userStore.isLogin) {
    openLoginModal();
    return;
  }
  if (houseStore.selectedHouse.zzim) {
    unlikeApt(houseStore.selectedHouse.aptId)
      .then(() => {
        return houseStore.setSelectedHouse({
          ...houseStore.selectedHouse,
          zzim: false
        });
      })
      .then(() => {
        return getLikeCount(houseStore.selectedHouse.aptId);
      })
      .then((res) => {
        houseStore.setLikeCount(res.data);
      });
  } else {
    likeApt(houseStore.selectedHouse.aptId)
      .then(() => {
        return houseStore.setSelectedHouse({
          ...houseStore.selectedHouse,
          zzim: true
        });
      })
      .then(() => {
        return getLikeCount(houseStore.selectedHouse.aptId);
      })
      .then((res) => {
        houseStore.setLikeCount(res.data);
      });
  }
};

watch(
  () => houseStore.selectedHouseId,
  () => {
    infoRef.value.scrollTop = 0;
  }
);

watch(
  () => houseStore.dealList,
  (newVal) => {
    if (newVal === null) return;
    recentDeal.value = houseStore.dealList[0];
    selectedSize.value = recentDeal.value.size;
  }
);

watch(filteredDealList, (newVal) => {
  if (newVal.length === 0) {
    recentDeal.value = null;
  }

  recentDeal.value = newVal[0];
});

const chartOptions = computed(() => {
  return {
    chart: {},
    yaxis: {
      labels: {
        formatter: function (value) {
          if (value < 10000) {
            return `${value.toLocaleString()}만원`;
          } else {
            return `${Math.floor(value / 10000)}억 ${(value % 10000).toLocaleString()}만원`;
          }
        }
      }
    },
    xaxis: {
      categories: filteredDealList.value
        .map((deal) => `${deal.dealYear}.${deal.dealMonth}`)
        .reverse()
    }
  };
});

const series = computed(() => {
  if (filteredDealList.value.length === 0) return [{ data: [] }];
  const dealAmounts = filteredDealList.value.map((deal) => deal.dealAmount).reverse();
  return [
    {
      name: '거래금액',
      data: dealAmounts
    }
  ];
});

const infoRef = ref(null);

onMounted(() => {
  const aptId = router.currentRoute.value.params.aptId;
  // 아파트 정보 조회
  setSelectedHouseId(aptId);
  getAptDetail(aptId).then((res) => {
    setSelectedHouse(res.data);
    console.log(houseStore.selectedHouse);
  });

  // 아파트 거래내역 조회
  getAptDealList(aptId).then((res) => {
    setDealList(res.data);
  });

  // 아파트 관심등록 개수 조회
  getLikeCount(aptId).then((res) => {
    setLikeCount(res.data);
  });
});

onBeforeRouteUpdate((to, from) => {
  const aptId = to.params.aptId;
  // 아파트 정보 조회
  getAptDetail(aptId).then((res) => {
    setSelectedHouse(res.data);
    console.log(houseStore.selectedHouse);
  });

  // 아파트 거래내역 조회
  getAptDealList(aptId).then((res) => {
    setDealList(res.data);
  });

  // 아파트 관심등록 개수 조회
  getLikeCount(aptId).then((res) => {
    setLikeCount(res.data);
  });
});
</script>

<template>
  <div class="flex flex-col h-screen sm:w-96 bg-white">
    <div><HouseSearch /></div>
    <div class="bg-primary">
      <div class="py-2 px-4 flex items-start justify-between">
        <div class="flex items-center gap-1">
          <button
            @click="onClickZzim"
            v-tooltip.right="{
              value: houseStore.selectedHouse?.zzim ? '관심 아파트 삭제' : '관심 아파트 추가'
            }"
          >
            <i
              :class="
                twMerge(
                  'pi text-lg',
                  houseStore.selectedHouse?.zzim
                    ? 'pi-heart-fill text-mypink-1'
                    : 'pi-heart text-primary-inverse'
                )
              "
            ></i>
          </button>
          <span class="text-primary-inverse text-sm">{{ likeCount }}</span>
        </div>
        <div class="flex flex-col items-center">
          <h3 class="text-primary-inverse text-lg">
            {{ houseStore.selectedHouse?.apartmentName }}
          </h3>
          <div
            class="flex items-center justify-center text-primary-inverse text-[11px] opacity-90 gap-1"
          >
            <span>건축년도</span>
            <span class="">{{ houseStore.selectedHouse?.buildYear }}년({{ aptAge }})</span>
          </div>
        </div>
        <button @click="closeAptDdtail">
          <i class="pi pi-times text-primary-inverse"></i>
        </button>
      </div>

      <div class="px-4 pb-2 text-center">
        <span class="opacity-90 text-primary-inverse text-sm">{{ address }} </span>
      </div>
    </div>
    <div ref="infoRef" class="flex-1 overflow-auto pb-10">
      <div class="flex flex-col p-4">
        <div class="flex justify-between items-start">
          <div>
            <span class="text-primary text-sm">최근 실거래가</span>
            <p class="font-bold text-xl text-primary">{{ price }}</p>
            <span class="text-sm text-surface-400"
              >{{ recentDeal?.dealYear }}.{{ recentDeal?.dealMonth }}.{{
                recentDeal?.dealDay
              }}</span
            >
          </div>

          <Dropdown
            :modelValue="selectedSize"
            @change="onSelectSize($event.value)"
            optionLabel="label"
            :options="sizeOptions"
            placeholder="면적"
          >
            <template #value="slotProps">
              <div v-if="slotProps.value" class="flex items-center">
                <div>{{ slotProps.value.toFixed(1) }}m<sup>2</sup></div>
              </div>
              <span v-else>
                {{ slotProps.placeholder }}
              </span>
            </template>
            <template #option="slotProps">
              <div class="flex items-center">
                <div>{{ slotProps.option.toFixed(1) }}m<sup>2</sup></div>
              </div>
            </template>
          </Dropdown>
        </div>
        <Divider type="dashed" />
        <div class="flex flex-col items-center">
          <apexchart type="line" :options="chartOptions" :series="series"></apexchart>
          <Button @click="openDealListModal" outlined class="w-full" size="small"
            >거래내역 자세히보기</Button
          >
        </div>
      </div>
      <div class="px-4"><Divider type="dashed" /></div>
      <div class="px-4 py-4">
        <AroundAptInfo v-if="houseStore.selectedHouseId" :aptId="houseStore.selectedHouseId" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
