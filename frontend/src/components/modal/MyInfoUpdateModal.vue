<script setup>
import { onUnmounted, ref, inject, onMounted } from 'vue';
import { useMutation } from '@tanstack/vue-query';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { useToast } from 'primevue/usetoast';
import { useDialog } from 'primevue/usedialog';
import MyInfoModal from '@/components/modal/MyInfoModal.vue';
import { updateMember as updateMemberRequest } from '@/api/member';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
const { storeUser } = userStore;

const dialog = useDialog();
const dialogRef = inject('dialogRef');
const toast = useToast();
const {
  isPending,
  isError,
  mutateAsync: updateUser
} = useMutation({
  mutationFn: (data) => updateMemberRequest(data)
});

const inputs = ref({
  id: '',
  pw: '',
  name: '',
  email: '',
  phone: ''
});

const closeModal = () => {
  dialogRef.value.close();
};

const resetInputs = () => {
  inputs.value = {
    id: userStore.user.id,
    pw: '',
    name: userStore.user.name,
    email: userStore.user.email,
    phone: userStore.user.phone
  };
};

const INITIAL_ERROR_MESSAGES = {
  id: null,
  pw: null,
  email: null,
  name: null,
  phone: null,
  form: null
};

const openMyInfoModal = () => {
  dialogRef.value.close();
  dialog.open(MyInfoModal, {
    props: {
      style: {
        width: '20rem'
      },
      modal: true,
      header: '내 정보',
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

const onSubmitUpdate = (e) => {
  updateUser({
    pw: inputs.value.pw,
    name: inputs.value.name,
    email: inputs.value.email,
    phone: inputs.value.phone
  })
    .then((res) => {
      storeUser(res.data);
      toast.add({
        severity: 'success',
        summary: '내 정보 수정 완료',
        detail: `${userStore.user.name}님 정보가 수정되었습니다`,
        life: 3000
      });
      openMyInfoModal();
    })
    .catch(() => {
      errorMessages.value.form = '내 정보 수정에 실패했습니다. 다시 시도해주세요.';
    });
};

onMounted(() => {
  resetInputs();
});

onUnmounted(() => {
  resetInputs();
  clearErrorMessages();
});
</script>

<template>
  <div class="">
    <form @submit.prevent="onSubmitUpdate">
      <div class="flex flex-col gap-1 mb-3">
        <label for="id" class="font-semibold">아이디</label>
        <span :v-show="errorMessages.id" class="text-red-600 text-sm">
          {{ errorMessages.id }}
        </span>
        <InputText
          v-model="inputs.id"
          id="id"
          name="id"
          class="w-full"
          required
          placeholder="kim123"
          readonly
          disabled
        />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="pw" class="font-semibold">비밀번호</label>
        <span :v-show="errorMessages.pw" class="text-red-600 text-sm">
          {{ errorMessages.pw }}
        </span>
        <InputText v-model="inputs.pw" type="password" id="pw" name="pw" class="w-full" required />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="name" class="font-semibold">이름</label>
        <span :v-show="errorMessages.name" class="text-red-600 text-sm">
          {{ errorMessages.name }}
        </span>
        <InputText v-model="inputs.name" id="name" name="name" class="w-full" required />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="email" class="font-semibold">이메일</label>
        <span :v-show="errorMessages.email" class="text-red-600 text-sm">
          {{ errorMessages.email }}
        </span>
        <InputText
          v-model="inputs.email"
          type="email"
          id="email"
          name="email"
          class="w-full"
          required
        />
      </div>
      <div class="flex flex-col gap-1 mb-3">
        <label for="phone" class="font-semibold">전화번호</label>
        <span :v-show="errorMessages.phone" class="text-red-600 text-sm">
          {{ errorMessages.password }}
        </span>
        <InputText
          v-model="inputs.phone"
          type="tel"
          id="phone"
          name="phone"
          class="w-full"
          required
        />
      </div>
      <p :v-if="errorMessages.form" class="text-red-600 text-sm mb-3">
        {{ errorMessages.form }}
      </p>
      <div class="flex gap-2 mt-5 justify-end">
        <Button @click="closeModal" type="button" severity="secondary"> <span>취소</span></Button>
        <Button type="submit" :loading="isPending"> <span v-if="!isPending">수정</span></Button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
