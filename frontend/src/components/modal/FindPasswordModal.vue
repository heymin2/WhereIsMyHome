<script setup>
import { onUnmounted, ref, inject } from 'vue';
import { useMutation } from '@tanstack/vue-query';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { useToast } from 'primevue/usetoast';
import { useDialog } from 'primevue/usedialog';
import LoginModal from '@/components/modal/LoginModal.vue';
import { findPassword as findPasswordRequest } from '@/api/auth';
import { useUserStore } from '@/stores/user';

const { storeUser } = useUserStore();
const dialog = useDialog();
const dialogRef = inject('dialogRef');
const toast = useToast();
const {
  isPending,
  isError,
  mutateAsync: findPw
} = useMutation({
  mutationFn: (data) => findPasswordRequest(data)
});

const INITIAL_ERROR_MESSAGES = {
  id: null,
  pw: null,
  email: null,
  name: null,
  phone: null,
  form: null
};

const openLoginModal = () => {
  dialogRef.value.close();
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

const errorMessages = ref({ ...INITIAL_ERROR_MESSAGES });

const clearErrorMessages = () => {
  errorMessages.value = {
    ...INITIAL_ERROR_MESSAGES
  };
};

const onSubmit = (e) => {
  const {
    id: { value: id },
    name: { value: name }
  } = e.target;

  findPw({ id, name })
    .then((res) => {
      toast.add({
        severity: 'success',
        summary: '비밀번호 찾기 성공',
        detail: `메일함을 확인해주세요`,
        life: 3000
      });
      openLoginModal();
    })
    .catch(() => {
      // todo: 에러메시지 처리
      errorMessages.value.form = '아이디와 이름을 확인해주세요';
    });
};

onUnmounted(() => {
  clearErrorMessages();
});
</script>

<template>
  <div class="">
    <p class="text-sm text-surface-500 mb-2">이메일로 비밀번호를 전송해드립니다</p>
    <form @submit.prevent="onSubmit">
      <div class="flex flex-col gap-1 mb-3">
        <label for="id" class="font-semibold">아이디</label>
        <span :v-show="errorMessages.id" class="text-red-600 text-sm">
          {{ errorMessages.id }}
        </span>
        <InputText id="id" name="id" class="w-full" required placeholder="kim123" />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="name" class="font-semibold">이름</label>
        <span :v-show="errorMessages.name" class="text-red-600 text-sm">
          {{ errorMessages.name }}
        </span>
        <InputText placeholder="김수환" type="" id="name" name="name" class="w-full" required />
      </div>
      <div :v-if="errorMessages.form" class="text-red-600 text-sm mb-2">
        {{ errorMessages.form }}
      </div>
      <div class="flex flex-col gap-2 mb-5">
        <Button type="submit" :loading="isPending"> <span v-if="!isPending">전송하기</span></Button>
      </div>

      <div class="flex justify-between">
        <span class="text-sm text-surface-500"></span>
        <Button @click="openLoginModal" link label="로그인" size="small"></Button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
