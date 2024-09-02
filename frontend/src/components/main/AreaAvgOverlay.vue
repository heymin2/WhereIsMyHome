<script setup>
import { defineProps } from 'vue';
import { KakaoMapCustomOverlay } from 'vue3-kakao-maps';

import { useMainMapStore } from '@/stores/mainMap';
import { computed } from 'vue';

const props = defineProps({
  area: Object
});
const mainMapStore = useMainMapStore();

const { setCoordinate, setZoomLevel } = mainMapStore;

const name = computed(() => {
  if (props.area.level === 1) {
    return props.area.sido;
  } else if (props.area.level === 2) {
    return props.area.gugun;
  } else if (props.area.level === 3) {
    return props.area.dong;
  }
  return '';
});

const address = computed(() => {
  if (props.apt === null) return '';
  const { sido, gugun, dong } = props.area;
  return `${sido ?? ''} ${gugun ?? ''} ${dong ?? ''}`;
});

const price = computed(() => {
  if (props.apt === null) return '';
  const { amount } = props.area;
  if (amount < 1000) {
    return amount;
  } else if (amount < 10000 && amount % 1000 == 0) {
    return amount / 1000 + '천';
  } else if (amount < 10000) {
    return (amount / 1000).toFixed(1) + '천';
  } else if (amount % 10000 == 0) {
    return amount / 10000 + '억';
  } else {
    return (amount / 10000).toFixed(1) + '억';
  }
});

const onClickOverlay = () => {
  const { level } = props.area;

  setCoordinate(props.area.latitude, props.area.longitude);
  if (level === 1) {
    setZoomLevel(7);
  } else if (level === 2) {
    setZoomLevel(5);
  } else if (level === 3) {
    setZoomLevel(3);
  }
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

// onMounted(() => {
//   const position = new window.kakao.maps.LatLng(props.area.latitude, props.area.longitude);

//   // 커스텀 오버레이를 생성합니다
//   const customOverlay = new window.kakao.maps.CustomOverlay({
//     position,
//     content,
//     yAnchor: 1
//   });

//   customOverlay.setMap(mainMapStore.map);
// });
</script>

<template>
  <KakaoMapCustomOverlay :lat="area.latitude" :lng="area.longitude">
    <div
      v-if="0 < area.amount"
      class="group flex flex-col relative z-10 items-center hover:-translate-y-1 transition-transform z-10"
    >
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-9 border rounded -left-[70%] z-20"
      >
        <span class="text-xs font-bold text-surface-700">{{ address }}</span>
      </div>
      <button
        @click="onClickOverlay"
        class="flex flex-col items-center justify-center bg-primary p-2 rounded"
      >
        <span class="text-[10px] text-primary-inverse opacity-90">{{ name }}</span>
        <span class="text-primary-inverse text-[11px] font-bold">{{ price }}</span>
      </button>
    </div>
    <div
      v-else
      class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform z-10"
    >
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-9 border rounded -left-[70%] z-20"
      >
        <span class="text-xs font-bold text-surface-700">{{ address }}</span>
      </div>
      <button
        @click="onClickOverlay"
        class="flex flex-col items-center justify-center rounded bg-surface-600 p-2"
      >
        <span class="text-[10px] text-primary-inverse opacity-90">{{ name }}</span>
      </button>
    </div></KakaoMapCustomOverlay
  >
</template>

<style scoped></style>
