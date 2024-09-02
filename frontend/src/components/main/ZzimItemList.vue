<script setup>
import router from '@/router';
import Skeleton from 'primevue/skeleton';
import Button from 'primevue/button';
import { useQuery } from '@tanstack/vue-query';
import { getLikeItemList } from '@/api/like';
import ZzimItem from '@/components/main/ZzimItem.vue';

const {
  data: zzimList,
  isPending,
  isError,
  refetch
} = useQuery({
  queryKey: ['zzimItemList'],
  queryFn: getLikeItemList,
  select: (res) => res.data
});

const goBack = () => {
  router.back();
};

const close = () => {
  router.replace('/');
};
</script>

<template>
  <div class="w-full sm:w-96 h-screen flex flex-col bg-white">
    <header class="flex items-center justify-between bg-white px-4 py-2">
      <button @click="goBack">
        <i class="pi pi-arrow-left"></i>
      </button>
      <h2 class="text-surface-700">관심 매물</h2>
      <button @click="close">
        <i class="pi pi-times"></i>
      </button>
    </header>
    <div class="flex bg-white gap-2 flex-1 overflow-y-auto">
      <div v-if="isPending" class="flex items-start justify-center w-full px-4 py-2">
        <ul class="w-full">
          <li class="mb-4" v-for="n in 10" :key="n">
            <div class="flex">
              <div class="self-center flex-1">
                <Skeleton width="100%" class="mb-2"></Skeleton>
                <Skeleton width="75%" class="mb-2"></Skeleton>
                <Skeleton width="85%"></Skeleton>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div
        v-else-if="isError"
        class="flex flex-col gap-2 items-center justify-center w-full px-4 py-2"
      >
        <div class="text-surface-600 flex items-center gap-2">
          <i class="pi pi-exclamation-triangle"></i>
          <span>데이터를 가져오는데 실패했습니다.</span>
        </div>
        <Button @click="refetch" outlined severity="secondary" size="small">재시도</Button>
      </div>
      <div v-else-if="zzimList.length === 0" class="flex items-center justify-center w-full">
        <span class="text-surface-500"> 관심 매물 없습니다.</span>
      </div>
      <div v-else class="w-full">
        <ZzimItem v-for="(item, i) in zzimList" :key="item.zzimId" :item="item" :order="i + 1" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
