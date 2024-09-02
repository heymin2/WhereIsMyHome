<script setup>
import { ref } from 'vue';
import Steps from 'primevue/steps';
import Step1 from './Step1.vue';
import Step2 from './Step2.vue';
import Step3 from './Step3.vue';
import Step4 from './Step4.vue';
import Step5 from './Step5.vue';
import { useCreateProductStore } from '@/stores/createProduct';

const createProductStore = useCreateProductStore();

const items = ref([
  {
    label: '거래유형 및 가격'
  },
  {
    label: '주소'
  },
  {
    label: '상세정보'
  },
  {
    label: '사진등록'
  },
  {
    label: '등록완료'
  }
]);
</script>

<template>
  <div>
    <Steps v-model:activeStep="createProductStore.step" :model="items" readonly />

    <!-- 거래유형 -->
    <div v-if="createProductStore.step === 0">
      <Step1 />
    </div>
    <!-- 주소 -->
    <div v-else-if="createProductStore.step === 1">
      <Step2 />
    </div>
    <div v-else-if="createProductStore.step === 2">
      <Step3 />
    </div>
    <!-- 사진등록 -->
    <div v-else-if="createProductStore.step === 3">
      <Step4 />
    </div>
    <!-- 등록완료 -->
    <div v-else-if="createProductStore.step === 4">
      <Step5 />
    </div>
  </div>
</template>

<style scoped></style>
