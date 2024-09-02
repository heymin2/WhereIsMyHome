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

const toast = useToast();
const queryClient = useQueryClient();

const dialogRef = inject('dialogRef');

const noticeStore = useNoticeStore();

const closeModal = () => {
  dialogRef.value.close();
};

const editContent = ref({
  title: '',
  content: ''
});

const onSubmitUpdatenotice = (e) => {
  console.log(e);
  console.log('오ㅐ그래');
  console.log(editContent.value);
  console.log(e.target.title.value);
  console.log(e.target.content.value);
  updateNotice({
    title: editContent.value.title,
    content: editContent.value.content,
    noticeId: noticeStore.notice.noticeId
  })
    .then(() => {
      queryClient.invalidateQueries('noticeList');
      toast.add({
        severity: 'success',
        summary: '수정 완료',
        detail: '공지사항이 수정되었습니다.',
        life: 3000
      });
      dialogRef.value.close();
    })
    .catch(() => {
      toast.add({
        severity: 'error',
        summary: '수정 실패',
        detail: '공지사항 수정에 실패했습니다.',
        life: 3000
      });
    });
};

onMounted(() => {
  editContent.value.title = noticeStore.notice.title;
  editContent.value.content = noticeStore.notice.content;
});
</script>

<template>
  <div>
    <form @submit.prevent="onSubmitUpdatenotice">
      <div class="flex flex-col gap-3 mb-3">
        <label for="title" class="font-semibold">제목</label>
        <InputText
          v-model="editContent.title"
          id="title"
          name="title"
          class="w-full"
          autocomplete="off"
          required
        />
      </div>
      <div class="flex flex-col gap-3 mb-5">
        <label for="content" class="font-semibold">내용</label>
        <Textarea
          v-model="editContent.content"
          id="content"
          name="content"
          class="w-full h-60 resize-none"
          required
          autocomplete="off"
        />
      </div>
      <div class="flex justify-end gap-2">
        <Button type="button" @click="closeModal" severity="secondary">취소</Button>
        <Button type="submit">저장</Button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
