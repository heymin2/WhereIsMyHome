<script setup>
import { KakaoMapCustomOverlay } from 'vue3-kakao-maps';

const props = defineProps({
  cafe: {
    type: Object,
    required: true
  }
});
</script>

<template>
  <KakaoMapCustomOverlay :lat="cafe.latitude" :lng="cafe.longitude">
    <div
      class="group flex flex-col items-center relative drop-shadow hover:-translate-y-1 transition-transform z-10"
    >
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-14 border rounded -left-[100%] z-20"
      >
        <span class="text-sm font-bold text-surface-700">{{ cafe.name }}</span>
        <span class="text-xs text-surface-500">{{ cafe.jibun }}</span>
      </div>
      <div
        class="w-8 h-8 flex justify-center items-center rounded-full bg-yellow-800 text-primary-inverse"
      >
        <svg
          fill="#ffffff"
          width="20px"
          height="20px"
          viewBox="0 0 50 50"
          version="1.2"
          baseProfile="tiny"
          xmlns="http://www.w3.org/2000/svg"
          overflow="inherit"
        >
          <path
            d="M37 7h-28v27c0 2.2 1.8 4 4 4h20c2.2 0 4-1.8 4-4v-5c6.076 0 11-4.925 11-11s-4.924-11-11-11zm0 17v-12c3.314 0 6 2.686 6 6 0 3.313-2.686 6-6 6zm-35 16v2.301c0 1.896 2.069 2.699 4.6 2.699h36.8c2.53 0 4.6-.803 4.6-2.699v-2.301h-46z"
          />
        </svg>
      </div>
      <div
        class="border-r-4 border-r-transparent border-l-4 border-l-transparent border-t-4 border-t-yellow-800 relative bottom-[1px]"
      ></div>
    </div>
  </KakaoMapCustomOverlay>
</template>

<style scoped></style>
