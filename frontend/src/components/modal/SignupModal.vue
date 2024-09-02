<script setup>
import { onUnmounted, ref, inject } from 'vue';
import { useMutation } from '@tanstack/vue-query';
import { RouterLink } from 'vue-router';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { useToast } from 'primevue/usetoast';
import { useDialog } from 'primevue/usedialog';
import LoginModal from '@/components/modal/LoginModal.vue';
import { checkDuplicatedId, signUp as signUpRequest } from '@/api/member';
import { useUserStore } from '@/stores/user';
import { debounce } from 'lodash';

const { storeUser } = useUserStore();
const dialog = useDialog();
const dialogRef = inject('dialogRef');
const toast = useToast();
const {
  isPending,
  isError,
  mutateAsync: signup
} = useMutation({
  mutationFn: (data) => signUpRequest(data)
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

const isValidId = ref(false);

const clearErrorMessages = () => {
  errorMessages.value = {
    ...INITIAL_ERROR_MESSAGES
  };
};

const onChangeId = debounce((e) => {
  isValidId.value = false;
  const id = e.target.value;
  checkDuplicatedId(id)
    .then(() => {
      isValidId.value = true;
      errorMessages.value.id = null;
    })
    .catch(() => {
      isValidId.value = false;
      errorMessages.value.id = '이미 사용중인 아이디입니다.';
    });
}, 500);

const onSubmitSignup = (e) => {
  if (isValidId.value === false) {
    return;
  }
  const {
    id: { value: id },
    pw: { value: pw },
    name: { value: name },
    email: { value: email },
    phone: { value: phone }
  } = e.target;

  signup({ id, pw, name, email, phone, admin: false })
    .then((res) => {
      toast.add({
        severity: 'success',
        summary: '회원가입 성공',
        detail: `${name}님 회원가입을 축하합니다.`,
        life: 3000
      });
      openLoginModal();
    })
    .catch(() => {
      // todo: 에러메시지 처리
      errorMessages.value.form = '회원가입 실패';
    });
};

onUnmounted(() => {
  clearErrorMessages();
});
</script>

<template>
  <div class="">
    <form @submit.prevent="onSubmitSignup">
      <div class="flex flex-col gap-1 mb-3">
        <label for="id" class="font-semibold">아이디</label>
        <span :v-show="errorMessages.id" class="text-red-600 text-sm">
          {{ errorMessages.id }}
        </span>
        <InputText
          @input="onChangeId"
          id="id"
          name="id"
          class="w-full"
          required
          placeholder="kim123"
        />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="pw" class="font-semibold">비밀번호</label>
        <span :v-show="errorMessages.pw" class="text-red-600 text-sm">
          {{ errorMessages.pw }}
        </span>
        <InputText type="password" id="pw" name="pw" class="w-full" required />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="name" class="font-semibold">이름</label>
        <span :v-show="errorMessages.name" class="text-red-600 text-sm">
          {{ errorMessages.name }}
        </span>
        <InputText type="" id="name" name="name" class="w-full" required />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="email" class="font-semibold">이메일</label>
        <span :v-show="errorMessages.email" class="text-red-600 text-sm">
          {{ errorMessages.email }}
        </span>
        <InputText type="email" id="email" name="email" class="w-full" required />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="phone" class="font-semibold">전화번호</label>
        <span :v-show="errorMessages.phone" class="text-red-600 text-sm">
          {{ errorMessages.password }}
        </span>
        <InputText type="tel" id="phone" name="phone" class="w-full" required />
      </div>
      <p :v-if="errorMessages.form" class="text-red-600 text-sm mb-3">
        {{ errorMessages.form }}
      </p>
      <div class="flex flex-col gap-2 mb-5">
        <Button type="submit" :loading="isPending"> <span v-if="!isPending">회원가입</span></Button>
      </div>
      <div class="flex justify-between">
        <span class="text-sm text-surface-500">이미 아이디가 있으신가요?</span>
        <Button @click="openLoginModal" link label="로그인" size="small"></Button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
