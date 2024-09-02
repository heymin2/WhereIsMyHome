<script setup>
import { createNotice } from '@/api/notice';
import { inject, ref } from 'vue';
import { useToast } from 'primevue/usetoast';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Textarea from 'primevue/textarea';

const toast = useToast();
const dialogRef = inject('dialogRef');

const editContent = ref({
  title: '',
  content: ''
});

const errorMessages = ref({
  title: null,
  content: null
});

const resetEditContent = () => {
  editContent.value = {
    title: null,
    content: null
  };
};

const closeModal = () => {
  clearErrorMessages();
  resetEditContent();
  dialogRef.value.close();
};

const clearErrorMessages = () => {
  errorMessages.value = {
    title: null,
    content: null
  };
};

const onSubmit = (e) => {
  const {
    title: { value: title },
    content: { value: content }
  } = e.target;

  let isValid = true;

  if (title.trim().length === 0) {
    errorMessages.value = { ...errorMessages.value, title: '제목은 1글자 이상이어야 합니다' };
    isValid = false;
  }

  if (content.trim().length === 0) {
    errorMessages.value = { ...errorMessages.value, content: '내용은 1글자 이상이어야 합니다' };
    isValid = false;
  }

  if (!isValid) return;
  createNotice({ title, content })
    .then(() => {
      toast.add({
        severity: 'success',
        summary: '등록 완료',
        detail: '공지사항이 등록되었습니다.',
        life: 3000
      });
      closeModal();
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
</script>

<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="flex flex-col gap-3 mb-3">
        <label for="title" class="font-semibold">제목</label>
        <span :v-show="errorMessages.title" class="text-red-600 text-sm">
          {{ errorMessages.title }}
        </span>
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
        <span :v-show="errorMessages.content" class="text-red-600 text-sm">
          {{ errorMessages.content }}
        </span>
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
        <Button type="cancle" @click="closeModal" severity="secondary">취소</Button>
        <Button type="submit">저장</Button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
