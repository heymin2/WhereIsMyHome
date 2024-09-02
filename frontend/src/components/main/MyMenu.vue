<script setup>
import { useDialog } from 'primevue/usedialog';
import { useToast } from 'primevue/usetoast';

import MyInfoModal from '@/components/modal/MyInfoModal.vue';
import MyInfoUpdateModal from '@/components/modal/MyInfoUpdateModal.vue';
import CreateProductModal from '@/components/modal/CreateProductModal/CreateProductModal.vue';
import CreateNoticeModal from '@/components/modal/CreateNoticeModal.vue';
import { useUserStore } from '@/stores/user';
import router from '@/router';
import { logout } from '@/api/auth';

const userStore = useUserStore();
const { clearUser } = userStore;
const dialog = useDialog();
const toast = useToast();

const openMyInfoModal = () => {
  dialog.open(MyInfoModal, {
    props: {
      style: {
        width: '20rem'
      },
      modal: true,
      header: '내 정보',
      draggable: false
      // dismissableMask: true
    }
  });
};

const openMyInfoUpdateModal = () => {
  dialog.open(MyInfoUpdateModal, {
    props: {
      style: {
        width: '20rem'
      },
      modal: true,
      header: '내 정보 수정',
      draggable: false
      // dismissableMask: true
    }
  });
};

const openCreateProductModal = () => {
  dialog.open(CreateProductModal, {
    props: {
      style: {},
      modal: true,
      header: '매물 등록',
      draggable: false,
      closeOnEscape: false
      // dismissableMask: true
    }
  });
};

const openCreateNoticeModal = () => {
  dialog.open(CreateNoticeModal, {
    props: {
      style: {},
      modal: true,
      header: '공지사항 등록',
      draggable: false,
      closeOnEscape: false
      // dismissableMask: true
    }
  });
};

const goZzimApt = () => {
  router.push({ name: 'ZzimApt' });
};

const goZzimItem = () => {
  router.push({ name: 'ZzimItem' });
};

const goMyItem = () => {
  router.push({ name: 'MyItem' });
};

const goNotice = () => {
  router.push({ name: 'Notice' });
};

const goBack = () => {
  router.back();
};

const close = () => {
  router.replace('/');
};

const onClickLogout = () => {
  logout()
    .then(() => {
      toast.add({
        severity: 'success',
        summary: '로그아웃 되었습니다',
        life: 3000
      });
    })
    .catch(() => {})
    .finally(() => {
      clearUser();
      router.replace('/');
    });
};
</script>

<template>
  <div class="w-full sm:w-96 h-screen flex flex-col bg-white">
    <header class="flex items-center justify-between bg-white px-4 py-2">
      <button @click="goBack">
        <i class="pi pi-arrow-left"></i>
      </button>
      <h2 class="text-surface-700">내 메뉴</h2>
      <button @click="close">
        <i class="pi pi-times"></i>
      </button>
    </header>
    <div class="flex bg-white px-4 py-2 gap-2 items-center">
      <div class="w-12 h-12 bg-surface-200 rounded-full flex items-center justify-center">
        <i class="pi pi-user text-surface-600 text-xl"></i>
      </div>
      <p class="flex flex-col justify-start">
        <span class="font-bold text-lg">{{ userStore.user.name }}</span>
        <span class="text-surface-500">{{ userStore.user.id }}</span>
      </p>
    </div>
    <div class="bg-surface-100 h-2"></div>
    <div class="bg-white">
      <h3 class="px-4 pt-2 text-sm">내 정보</h3>
      <ul class="font-semibold text-surface-800">
        <li
          @click="openMyInfoModal"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          내 정보 조회
        </li>
        <li
          @click="openMyInfoUpdateModal"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          내 정보 수정
        </li>
      </ul>
    </div>
    <div class="bg-surface-100 h-2"></div>
    <div class="bg-white">
      <h3 class="px-4 pt-2 text-sm">관심</h3>
      <ul class="font-semibold text-surface-800">
        <li
          @click="goZzimApt"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          아파트
        </li>
        <li
          @click="goZzimItem"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          매물
        </li>
      </ul>
    </div>
    <div class="bg-surface-100 h-2"></div>
    <div class="bg-white">
      <h3 class="px-4 pt-2 text-sm">매물</h3>
      <ul class="font-semibold text-surface-800">
        <li
          @click="openCreateProductModal"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          매물등록
        </li>
        <li
          @click="goMyItem"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          나의 매물
        </li>
      </ul>
    </div>
    <div class="bg-surface-100 h-2"></div>

    <div class="bg-white">
      <h3 class="px-4 pt-2 text-sm">공지</h3>
      <ul class="font-semibold text-surface-800">
        <li
          @click="goNotice"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          공지사항
        </li>
        <li
          v-if="userStore.user.isAdmin"
          @click="openCreateNoticeModal"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          공지사항 등록
        </li>
      </ul>
    </div>
    <div class="bg-surface-100 h-2"></div>
    <div class="bg-white">
      <h3 class="px-4 text-sm"></h3>
      <ul class="font-semibold text-surface-800">
        <li
          @click="onClickLogout"
          class="px-4 py-4 cursor-pointer hover:bg-surface-50 transition-colors"
        >
          로그아웃
        </li>
      </ul>
    </div>
    <div class="bg-surface-100 h-2"></div>
  </div>
</template>

<style scoped></style>
