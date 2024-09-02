<script setup>
import { ref, inject, watch, onMounted } from 'vue';
import Skeleton from 'primevue/skeleton';
import Divider from 'primevue/divider';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Textarea from 'primevue/textarea';
import { useUserStore } from '@/stores/user';
import { useToast } from 'primevue/usetoast';
import { updateNotice } from '@/api/notice';
import { toYYYYMMDD } from '@/utils/dateTime';
import { useNoticeStore } from '@/stores/notice';
import { useQueryClient } from '@tanstack/vue-query';
import { useDialog } from 'primevue/usedialog';
import NoticeEditModal from '@/components/modal/NoticeEditModal.vue';

const userStore = useUserStore();
const toast = useToast();
const dialog = useDialog();

const dialogRef = inject('dialogRef');

const noticeStore = useNoticeStore();

const editContent = ref({
  title: '',
  content: ''
});
const mode = ref('view');

const openUpdateModal = () => {
  dialogRef.value.close();
  dialog.open(NoticeEditModal, {
    props: {
      header: '공지사항 수정하기',
      modal: true,
      draggable: false
    }
  });
};

const updateMode = () => {
  editContent.value.title = noticeStore.notice.title;
  editContent.value.content = noticeStore.notice.content;
  mode.value = 'update';
  dialogRef.value.options.props.header = '공지사항 수정하기';
};

onMounted(() => {
  dialogRef.value.options.props.header = '공지사항 상세조회';
});
</script>

<template>
  <div v-if="noticeStore.notice === null">
    <Skeleton height="2rem" class="mb-4" />
    <Skeleton class="mb-2" />
    <Skeleton width="80%" class="mb-2" />
    <Skeleton width="50%" class="mb-2" />
    <Skeleton width="60%" class="mb-2" />
    <Skeleton width="90%" class="mb-2" />
  </div>
  <div v-else-if="mode === 'view'" class="flex flex-col">
    <h3 class="font-bold text-xl mb-2">{{ noticeStore.notice.title }}</h3>

    <div class="flex">
      <div class="flex gap-1 items-center mr-3">
        <i class="pi pi-user"></i>
        <span> {{ noticeStore.notice.name }}</span>
      </div>
      <div class="flex gap-1">
        <span>작성일</span>
        <span>:</span>
        <span> {{ toYYYYMMDD(noticeStore.notice.createdAt) }}</span>
      </div>
      <div v-if="noticeStore.notice.createdAt === noticeStore.notice.updatedAt" class="flex gap-1">
        <span>수정일</span>
        <span>:</span>
        <!-- <span> {{ toYYYYMMDD(noticeStore.notice.updatedAt) }}</span> -->
      </div>
    </div>
    <Divider />
    <p class="mb-10">
      {{ noticeStore.notice.content }}
    </p>
    <div v-if="userStore.user.isAdmin" class="flex justify-end">
      <Button @click="openUpdateModal">수정하기</Button>
    </div>
  </div>
  <div v-else></div>
</template>

<style scoped></style>
