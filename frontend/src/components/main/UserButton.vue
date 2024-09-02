<script setup>
import { useDialog } from 'primevue/usedialog';
import { useUserStore } from '@/stores/user';
import { computed } from 'vue';

import LoginModal from '@/components/modal/LoginModal.vue';
import { twMerge } from 'tailwind-merge';
import router from '@/router';

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
const onClickUserButton = () => {
  if (userStore.isLogin) {
    const path = window.location.href.split('/').pop();
    if (path === 'my') router.back();
    else {
      router.push('/my');
    }
  } else {
    openLoginModal();
  }
};

const userStore = useUserStore();

const tooltip = computed(() => {
  return userStore.isLogin ? '회원정보' : '로그인';
});
</script>

<template>
  <button
    @click="onClickUserButton"
    v-tooltip.left="{
      value: tooltip
    }"
    :class="
      twMerge(
        ' aspect-square border w-10 drop-shadow-lg transition-colors',
        userStore.isLogin
          ? 'bg-primary text-primary-inverse border-primary-highlight'
          : 'bg-white hover:bg-surface-100'
      )
    "
  >
    <i class="pi pi-user"></i>
  </button>
</template>

<style scoped></style>
