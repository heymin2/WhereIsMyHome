<script setup>
import { KakaoMapCustomOverlay } from 'vue3-kakao-maps';

const props = defineProps({
  busStop: {
    type: Object,
    required: true
  }
});
</script>

<template>
  <KakaoMapCustomOverlay :lat="busStop.latitude" :lng="busStop.longitude">
    <div class="group flex flex-col items-center relative z-10">
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-14 border rounded -left-[100%] z-20"
      >
        <span class="text-sm font-bold text-surface-700"
          >{{ busStop.name }}({{ busStop.busId }})</span
        >
      </div>
      <div
        class="w-8 h-8 flex justify-center items-center rounded-full bg-blue-500 text-primary-inverse"
      >
        <i class="pi pi-car"></i>
      </div>
      <div
        class="border-r-4 border-r-transparent border-l-4 border-l-transparent border-t-4 border-t-blue-500 relative bottom-[1px]"
      ></div>
    </div>
  </KakaoMapCustomOverlay>
</template>

<style scoped></style>
