<script setup>
import { KakaoMapCustomOverlay } from 'vue3-kakao-maps';

const props = defineProps({
  restaurant: {
    type: Object,
    required: true
  }
});
</script>

<template>
  <KakaoMapCustomOverlay :lat="restaurant.latitude" :lng="restaurant.longitude">
    <div
      class="group flex flex-col items-center relative drop-shadow hover:-translate-y-1 transition-transform z-10"
    >
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-14 border rounded -left-[100%] z-20"
      >
        <span class="text-sm font-bold text-surface-700">{{ restaurant.name }}</span>
        <span class="text-xs text-surface-500">{{ restaurant.jibun }}</span>
      </div>
      <div
        class="w-8 h-8 flex justify-center fill-white items-center rounded-full bg-orange-400 text-primary-inverse"
      >
        <svg
          width="20px"
          height="20px"
          viewBox="0 0 50 50"
          version="1.2"
          baseProfile="tiny"
          xmlns="http://www.w3.org/2000/svg"
          overflow="inherit"
        >
          <path
            d="M22 1.932v11.068h-2v-11c0-.552-.448-1-1-1s-1 .448-1 1v11h-2v-11.036c0-1.287-2-1.243-2-.033v11.069h-2v-10.99c0-1.363-2-1.313-2-.054v14.472c0 2.087 2 3.463 4 3.463v26.109c0 4 6 4 6 0v-26.108c2 0 4-1.662 4-3.227v-14.701c0-1.275-2-1.226-2-.032zm9 3.068v25h2v16c0 4 7 4 7 0v-41c0-5-9-5-9 0z"
          />
        </svg>
      </div>
      <div
        class="border-r-4 border-r-transparent border-l-4 border-l-transparent border-t-4 border-t-orange-400 relative bottom-[1px]"
      ></div>
    </div>
  </KakaoMapCustomOverlay>
</template>

<style scoped></style>
