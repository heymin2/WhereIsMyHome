<script setup>
import { toYYYYMMDD } from '@/utils/dateTime';
import { defineProps } from 'vue';
import { useQueryClient } from '@tanstack/vue-query';
import { useNoticeStore } from '@/stores/notice';
import { useDialog } from 'primevue/usedialog';
import NoticeDetailModal from '@/components/modal/NoticeDetailModal.vue';
import { deleteNotice } from '@/api/notice';
import { useUserStore } from '@/stores/user';
const props = defineProps({
  notice: {
    type: Object,
    required: true
  },
  order: {
    type: Number,
    required: true
  }
});

const dialog = useDialog();

const userStore = useUserStore();
const queryClient = useQueryClient();
const noticeStore = useNoticeStore();

const openDetailModal = (e) => {
  if (e.target.id === 'deleteBtn' || e.target.parentElement.id === 'deleteBtn') return;

  noticeStore.setNotice(props.notice);
  console.log(noticeStore.notice);

  dialog.open(NoticeDetailModal, {
    props: {
      header: '공지사항 상세조회',
      modal: true,
      draggable: false
    }
  });
};

const onClickDelete = () => {
  deleteNotice(props.notice.noticeId).then(() => {
    queryClient.invalidateQueries('noticeList');
  });
};
</script>

<template>
  <div
    @click="openDetailModal"
    class="flex items-center w-full cursor-pointer hover:bg-surface-50 transition-colors px-4 py-4 gap-4"
  >
    <div class="font-bold text-surface-800 text-lg">{{ order }}</div>
    <div class="flex flex-col flex-1 overflow-hidden">
      <span class="font-semibold text-surface-800">{{ notice.title }}</span>

      <div class="flex items-center gap-1 text-xs text-surface-600">
        <div class="flex items-center gap-1">
          <i class="pi pi-calendar"></i>
          <span>작성일</span>
        </div>
        <span>:</span>
        <span>{{ toYYYYMMDD(notice.createdAt) }}</span>
      </div>
    </div>
    <button
      v-if="userStore.user.isAdmin"
      id="deleteBtn"
      @click="onClickDelete"
      class="ml-auto text-surface-400 hover:text-red-500 transition-colors"
    >
      <i class="pi pi-trash"></i>
    </button>
  </div>
</template>

<style scoped></style>
