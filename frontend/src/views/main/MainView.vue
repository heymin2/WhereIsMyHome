<script setup>
import ProgressSpinner from 'primevue/progressspinner';
import { RouterView } from 'vue-router';
import SideMenubar from '@/components/main/SideMenubar.vue';
import UserButton from '@/components/main/UserButton.vue';
import MainMap from '@/components/main/MainMap.vue';

import { useMainMapStore } from '@/stores/mainMap';

const mainMapStore = useMainMapStore();
</script>

<template>
  <MainMap />
  <div
    v-show="mainMapStore.isLoading"
    class="flex items-center justify-center w-full h-full absolute"
  >
    <ProgressSpinner
      style="width: 50px; height: 50px"
      strokeWidth="8"
      class="fill-surface-0 dark:fill-surface-800"
      aria-label="Custom ProgressSpinner"
    />
  </div>
  <div class="absolute sm:fixed right-3 top-32 sm:top-3 flex w-max flex-col items-end gap-2">
    <div class="hidden sm:flex">
      <UserButton />
    </div>
    <SideMenubar />
  </div>
  <div class="sm:w-max sm:absolute relative z-20 max-h-screen">
    <RouterView />
    <!-- <div class="sm:w-96 w-full max-h-screen overflow-auto flex flex-col sm:p-3">
      <div class="bg-primary h-96 w-full">hello</div>
      <div class="overflow-y-auto flex-1 h-96 bg-white w-full">
        <div class="h-[2000px]"></div>
      </div>
    </div> -->
    <!-- <InfoSection /> -->
  </div>
</template>

<style scoped></style>
