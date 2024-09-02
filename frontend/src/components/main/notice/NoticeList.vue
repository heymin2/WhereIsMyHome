<script setup>
import router from '@/router';
import Skeleton from 'primevue/skeleton';
import Button from 'primevue/button';
import { useQuery } from '@tanstack/vue-query';
import { ref } from 'vue';
import NoticeItem from './NoticeItem.vue';
import { getNoticeList } from '@/api/notice';

const params = ref({
  page: 0,
  size: 10
});

const { data, isPending, isError, refetch } = useQuery({
  queryKey: ['noticeList', params.value.page, params.value.size],
  queryFn: () => getNoticeList(params.value),
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
      <h2 class="text-surface-700">공지사항</h2>
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
      <div
        v-else-if="data.pages.content.length === 0"
        class="flex items-center justify-center w-full"
      >
        <span class="text-surface-500"> 공지사항이 없습니다.</span>
      </div>
      <div v-else class="w-full">
        <NoticeItem
          v-for="(item, i) in data.pages.content"
          :key="item.noticeId"
          :notice="item"
          :order="i + 1"
        />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
