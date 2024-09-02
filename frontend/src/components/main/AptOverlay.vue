<script setup>
import { defineProps } from 'vue';
import { KakaoMapCustomOverlay } from 'vue3-kakao-maps';
import { useHouseStore } from '@/stores/house';
import { getAptDealList, getAptDetail } from '@/api/house';
import { computed } from 'vue';
import { getLikeCount } from '@/api/like';
import { twMerge } from 'tailwind-merge';
import router from '@/router';

const houseStore = useHouseStore();
const { setSelectedHouseId, setSelectedHouse, setDealList, setLikeCount } = houseStore;
const props = defineProps({
  apt: Object
});

const size = computed(() => {
  if (props.apt === null) return '';
  const { size } = props.apt;
  if (Number.isInteger(size)) {
    return size;
  } else {
    return size.toFixed(1);
  }
});

const address = computed(() => {
  if (props.apt === null) return '';
  const { sido, gugun, dong, jibun } = props.apt;
  return `${sido} ${gugun} ${dong} ${jibun}`;
});

const price = computed(() => {
  if (props.apt === null) return '';
  const { dealAmount } = props.apt;
  if (dealAmount < 1000) {
    return dealAmount;
  } else if (dealAmount < 10000 && dealAmount % 1000 == 0) {
    return dealAmount / 1000 + '천';
  } else if (dealAmount < 10000) {
    return (dealAmount / 1000).toFixed(1) + '천';
  } else if (dealAmount % 10000 == 0) {
    return dealAmount / 10000 + '억';
  } else {
    return (dealAmount / 10000).toFixed(1) + '억';
  }
});

const onClickOverlay = () => {
  if (houseStore.selectedHouseId == props.apt.aptId) return;

  setSelectedHouseId(props.apt.aptId);
  router.push({ name: 'AptDetail', params: { aptId: props.apt.aptId } });
};

// const content = document.createElement('div');
// content.innerHTML = `
//   <div class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform">
//     <div class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-14 border rounded -left-[100%]">
//       <span class="text-sm font-bold text-surface-700">${props.apt?.apartmentName}</span>
//       <span class="text-xs text-surface-500">${address.value}</span>
//     </div>
//     <Button class="flex flex-col items-center justify-center w-12 h-12 bg-primary">
//       <span class="text-xs text-primary-inverse opacity-90">${size.value}m<sup>2</sup></span>
//       <span class="text-primary-inverse text-sm font-bold">${price.value}</span>
//     </Button>
//     <div class=" border-r-8 border-l-8 border-t-8 border-t-primary border-r-transparent border-l-transparent"></div>
//   </div>
// `;

// const button = content.querySelector('button');
// button.addEventListener('click', () => {
//   onClickOverlay();
// });

// const position = new window.kakao.maps.LatLng(props.apt.latitude, props.apt.longitude);

// // 커스텀 오버레이를 생성합니다
// const customOverlay = new window.kakao.maps.CustomOverlay({
//   position,
//   content,
//   yAnchor: 1
// });

// onUpdated(() => {
//   if (!props.apt) customOverlay.setMap(null);
// });

// onMounted(() => {
//   customOverlay.setMap(mainMapStore.map);
// });
</script>

<template>
  <KakaoMapCustomOverlay :lat="apt.latitude" :lng="apt.longitude">
    <div
      class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform z-10"
    >
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-14 border rounded -left-[100%] z-20"
      >
        <span class="text-sm font-bold text-surface-700">{{ apt.apartmentName }}</span>
        <span class="text-xs text-surface-500">{{ address }}</span>
      </div>
      <button
        @click="onClickOverlay"
        :class="
          twMerge(
            'flex flex-col items-center justify-center w-12 h-12',
            apt.zzim ? 'bg-mypink-1' : 'bg-primary'
          )
        "
      >
        <span class="text-xs text-primary-inverse opacity-90">{{ size }}m<sup>2</sup></span>
        <span class="text-primary-inverse text-sm font-bold">{{ price }}</span>
      </button>
      <div
        :class="
          twMerge(
            'border-r-8 border-l-8 border-t-8 border-t-primary border-r-transparent border-l-transparent',
            apt.zzim ? 'border-t-mypink-1' : 'border-t-primary'
          )
        "
      ></div>
    </div>
  </KakaoMapCustomOverlay>
</template>

<style scoped></style>
