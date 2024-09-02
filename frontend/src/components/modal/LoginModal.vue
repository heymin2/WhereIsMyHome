<script setup>
import { onUnmounted, ref, inject } from 'vue';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { RouterLink } from 'vue-router';
import { useToast } from 'primevue/usetoast';
import { useDialog } from 'primevue/usedialog';
import { useUserStore } from '@/stores/user';
import { useMutation } from '@tanstack/vue-query';
import SignupModal from '@/components/modal/SignupModal.vue';
import FindPasswordModal from '@/components/modal/FindPasswordModal.vue';
import { login as loginRequest } from '@/api/auth';

const { storeUser } = useUserStore();

const dialog = useDialog();
const dialogRef = inject('dialogRef');
const toast = useToast();
const {
  isPending,
  isError,
  mutateAsync: login
} = useMutation({
  mutationFn: ({ id, password }) => loginRequest(id, password)
});

const errorMessages = ref({
  id: null,
  password: null,
  form: null
});

const clearErrorMessages = () => {
  errorMessages.value = {
    id: null,
    password: null,
    form: null
  };
};

const openSignupModal = () => {
  dialogRef.value.close();
  dialog.open(SignupModal, {
    props: {
      style: {
        width: '20rem'
      },
      modal: true,
      header: '회원가입',
      draggable: false
      // dismissableMask: true
    },
    onClose: () => {}
  });
};

const openFindPasswordModal = () => {
  dialogRef.value.close();
  dialog.open(FindPasswordModal, {
    props: {
      style: {
        width: '20rem'
      },
      modal: true,
      header: '비밀번호 찾기',
      draggable: false
      // dismissableMask: true
    },
    onClose: () => {}
  });
};

const onSubmitLogin = (e) => {
  console.log(dialogRef.value);
  const {
    id: { value: id },
    password: { value: password }
  } = e.target;

  login({ id, password })
    .then((res) => {
      console.log('로그인성공');
      console.log(res.data);
      storeUser(res.data);
      dialogRef.value.close();
      toast.add({
        severity: 'success',
        summary: '로그인 성공',
        detail: `${res.data.name}님 환영합니다`,
        life: 3000
      });
    })
    .catch((err) => {
      // todo: 에러메시지 처리
      errorMessages.value.form = '로그인실패';
    });
};

onUnmounted(() => {
  clearErrorMessages();
});
</script>

<template>
  <div class="">
    <form @submit.prevent="onSubmitLogin">
      <div class="flex flex-col gap-1 mb-3">
        <label for="id" class="font-semibold">아이디</label>
        <span :v-show="errorMessages.id" class="text-red-600 text-sm">
          {{ errorMessages.id }}
        </span>
        <InputText id="id" name="id" class="w-full" required placeholder="kim123" />
      </div>
      <div class="flex flex-col gap-1 mb-5">
        <label for="password" class="font-semibold">비밀번호</label>
        <span :v-show="errorMessages.password" class="text-red-600 text-sm">
          {{ errorMessages.password }}
        </span>
        <InputText type="password" id="password" name="password" class="w-full" required />
      </div>
      <span :v-show="errorMessages.form" class="text-red-600 text-sm">
        {{ errorMessages.form }}
      </span>
      <div class="flex flex-col gap-2 mb-5">
        <Button type="submit" :loading="isPending"> <span v-if="!isPending">로그인</span></Button>
      </div>
      <div class="flex justify-between items-center">
        <Button @click="openFindPasswordModal" link label="비밀번호찾기" size="small"></Button>
        <Button @click="openSignupModal" link label="회원가입" size="small"></Button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
