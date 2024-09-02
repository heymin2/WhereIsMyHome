<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import Dropdown from 'primevue/dropdown';
import Button from 'primevue/button';
import Divider from 'primevue/divider';

import Galleria from 'primevue/galleria';

import { twMerge } from 'tailwind-merge';
import { useUserStore } from '@/stores/user';
import { useDialog } from 'primevue/usedialog';
import LoginModal from '@/components/modal/LoginModal.vue';
import AroundAptInfo from '@/components/main/AroundAptInfo.vue';
import { getItemLikeCount, likeItem, unlikeItem } from '@/api/like';
import HouseSearch from '@/components/main/HouseSearch.vue';
import router from '@/router';
import { onBeforeRouteUpdate } from 'vue-router';
import { useItemStore } from '@/stores/ItemStore';
import { getItemDetail } from '@/api/item';

const itemStore = useItemStore();
const userStore = useUserStore();
const { setSelectedItemId, setSelectedItem, setLikeCount } = itemStore;
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

const likeCount = computed(() => {
  return itemStore.likeCount < 100 ? itemStore.likeCount : '99+';
});

const images = computed(() => {
  if (!itemStore.selectedItem) return [];
  return itemStore.selectedItem.img.map((img) => import.meta.env.VITE_API_ENDPOINT + img);
});

const size = computed(() => {
  if (!itemStore.selectedItem) return '';
  if (Number.isInteger(itemStore.selectedItem.size)) {
    return itemStore.selectedItem.size;
  } else {
    return itemStore.selectedItem.size.toFixed(1);
  }
});

const price = computed(() => {
  if (itemStore.selectedItem === null) return '';
  switch (itemStore.selectedItem.type) {
    case '매매': {
      const { salePrice } = itemStore.selectedItem;
      if (salePrice < 1000) {
        return salePrice;
      } else if (salePrice < 10000 && salePrice % 1000 == 0) {
        return salePrice / 1000 + '천';
      } else if (salePrice < 10000) {
        return (salePrice / 1000).toFixed(1) + '천';
      } else if (salePrice % 10000 == 0) {
        return salePrice / 10000 + '억';
      } else {
        return (salePrice / 10000).toFixed(1) + '억';
      }
    }
    case '전세': {
      const { deposit } = itemStore.selectedItem;
      if (deposit < 1000) {
        return deposit;
      } else if (deposit < 10000 && deposit % 1000 == 0) {
        return deposit / 1000 + '천';
      } else if (deposit < 10000) {
        return (deposit / 1000).toFixed(1) + '천';
      } else if (deposit % 10000 == 0) {
        return deposit / 10000 + '억';
      } else {
        return (deposit / 10000).toFixed(1) + '억';
      }
    }
    case '월세': {
      const { deposit, monthlyRent } = itemStore.selectedItem;
      if (deposit < 1000) {
        return deposit;
      } else if (deposit < 10000 && deposit % 1000 == 0) {
        return deposit / 1000 + '천 / ' + monthlyRent;
      } else if (deposit < 10000) {
        return (deposit / 1000).toFixed(1) + '천 / ' + monthlyRent;
      } else if (deposit % 10000 == 0) {
        return deposit / 10000 + '억 / ' + monthlyRent;
      } else {
        return (deposit / 10000).toFixed(1) + '억 / ' + monthlyRent;
      }
    }
    default:
      return '';
  }
});

const closeItemDdtail = () => {
  itemStore.setSelectedItemId(null);
  itemStore.setSelectedItem(null);
  router.push('/');
};

const onClickZzim = () => {
  if (!userStore.isLogin) {
    openLoginModal();
    return;
  }
  if (itemStore.selectedItem.zzim) {
    unlikeItem(itemStore.selectedItem.itemId)
      .then(() => {
        return itemStore.setSelectedItem({
          ...itemStore.selectedItem,
          zzim: false
        });
      })
      .then(() => {
        return getItemLikeCount(itemStore.selectedItem.itemId);
      })
      .then((res) => {
        itemStore.setLikeCount(res.data);
      });
  } else {
    likeItem(itemStore.selectedItem.itemId)
      .then(() => {
        return itemStore.setSelectedItem({
          ...itemStore.selectedItem,
          zzim: true
        });
      })
      .then(() => {
        return getItemLikeCount(itemStore.selectedItem.itemId);
      })
      .then((res) => {
        itemStore.setLikeCount(res.data);
      });
  }
};

watch(
  () => itemStore.selectedItemId,
  () => {
    infoRef.value.scrollTop = 0;
  }
);

const infoRef = ref(null);

onMounted(() => {
  const itemId = router.currentRoute.value.params.itemId;
  console.log('itemId : ', itemId);
  // 아파트 정보 조회
  setSelectedItemId(itemId);
  getItemDetail(itemId).then((res) => {
    setSelectedItem(res.data);
  });
  // 아파트 관심등록 개수 조회
  getItemLikeCount(itemId).then((res) => {
    setLikeCount(res.data);
  });
});

onBeforeRouteUpdate((to) => {
  const itemId = to.params.itemId;
  console.log('itemId : ', itemId);

  getItemDetail(itemId).then((res) => {
    setSelectedItem(res.data);
  });

  getItemLikeCount(itemId).then((res) => {
    setLikeCount(res.data);
  });
});
</script>

<template>
  <div class="flex flex-col h-screen sm:w-96 bg-white">
    <div class="bg-primary">
      <div class="py-2 px-4 flex items-start justify-between">
        <div class="flex items-center gap-1">
          <button
            @click="onClickZzim"
            v-tooltip.right="{
              value: itemStore.selectedItem?.zzim ? '관심 매물 삭제' : '관심 매물 추가'
            }"
          >
            <i
              :class="
                twMerge(
                  'pi text-lg',
                  itemStore.selectedItem?.zzim
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
            {{ itemStore.selectedItem?.title }}
          </h3>
        </div>
        <button @click="closeItemDdtail">
          <i class="pi pi-times text-primary-inverse"></i>
        </button>
      </div>
      <div class="px-4 pb-2 text-center">
        <span class="opacity-90 text-primary-inverse text-sm"
          >{{ itemStore.selectedItem?.address }}
        </span>
      </div>
    </div>
    <div ref="infoRef" class="flex-1 overflow-auto pb-10">
      <div>
        <Galleria :value="images" :numVisible="4" containerStyle="max-width: 640px">
          <template #item="slotProps">
            <img :src="slotProps.item" style="width: 100%" />
          </template>
          <template #thumbnail="slotProps">
            <img :src="slotProps.item" />
          </template>
        </Galleria>
      </div>
      <div class="flex flex-col p-4">
        <div class="flex justify-between items-center">
          <div>
            <span v-if="itemStore.selectedItem?.type === '매매'" class="text-primary text-sm"
              >거래가</span
            >
            <span v-else-if="itemStore.selectedItem?.type == '전세'" class="text-primary text-sm"
              >보증금</span
            >
            <span v-else class="text-primary text-sm">보즘금 / 월세</span>
            <p class="font-bold text-xl text-primary">
              {{ itemStore.selectedItem?.type }} {{ price }}
            </p>
          </div>
          <div>
            <div
              v-if="!itemStore.selectedItem?.sold"
              class="px-2 py-1 bg-primary text-primary-inverse"
            >
              판매중
            </div>
            <div
              v-else-if="itemStore.selectedItem?.sold"
              class="px-2 py-1 bg-surface-200 text-surface-600"
            >
              거래완료
            </div>
          </div>
        </div>
        <Divider type="dashed" />
        <h4 class="font-semibold mb-1">매물 정보</h4>
        <span class="opacity-90 text-surface-500 text-sm mb-2"
          >{{ itemStore.selectedItem?.address }}
        </span>
        <div class="flex flex-col gap-1 text-surface-800 text-sm mb-8">
          <div class="flex justify-between">
            <span>전용면적</span>
            <span>{{ size }}m<sup>2</sup></span>
          </div>
          <div class="flex justify-between">
            <span>방 수</span>
            <span>{{ itemStore.selectedItem?.rooms || '-' }}개</span>
          </div>
          <div class="flex justify-between">
            <span>욕실 수</span>
            <span>{{ itemStore.selectedItem?.bathrooms || '-' }}개</span>
          </div>
        </div>
        <h4 class="font-semibold mb-1">매물 소개</h4>
        <div class="text-sm text-surface-800">
          {{ itemStore.selectedItem?.description }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
<!--  -->
