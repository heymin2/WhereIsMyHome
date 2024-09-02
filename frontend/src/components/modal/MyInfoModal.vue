<script setup>
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
</script>

<template>
  <section>
    <div>
      <div class="flex flex-col gap-1 mb-3">
        <span class="font-semibold">아이디</span>
        <span class="w-full">
          {{ userStore.user.id }}
        </span>
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <span class="font-semibold">이름</span>
        <span class="w-full">
          {{ userStore.user.name }}
        </span>
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <span class="font-semibold">이메일</span>
        <span class="w-full">
          {{ userStore.user.email }}
        </span>
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <span class="font-semibold">전화번호</span>
        <span class="w-full">
          {{ userStore.user.phone }}
        </span>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
