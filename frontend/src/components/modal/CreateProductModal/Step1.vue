<script setup>
import Button from 'primevue/button';
import InputNumber from 'primevue/inputnumber';
import { useCreateProductStore } from '@/stores/createProduct';

const createProductStore = useCreateProductStore();
const { setType, setStep } = createProductStore;
</script>

<template>
  <div class="mt-4">
    <h4 class="py-4 text-lg font-bold text-surface-800">거래유형</h4>
    <div class="flex flex-col gap-2">
      <Button
        @click="setType('매매')"
        :outlined="createProductStore.inputs.type !== '매매'"
        :severity="createProductStore.inputs.type === '매매' ? null : 'secondary'"
        >매매</Button
      >
      <Button
        @click="setType('전세')"
        :outlined="createProductStore.inputs.type !== '전세'"
        :severity="createProductStore.inputs.type === '전세' ? null : 'secondary'"
        >전세</Button
      >
      <Button
        @click="setType('월세')"
        :outlined="createProductStore.inputs.type !== '월세'"
        :severity="createProductStore.inputs.type === '월세' ? null : 'secondary'"
        >월세</Button
      >
    </div>

    <div v-if="createProductStore.inputs.type === '매매'">
      <div class="flex flex-col gap-2 mt-4">
        <label for="salePrice" class="font-bold text-lg text-surface-800"> 가격 </label>
        <div class="flex items-center gap-2">
          <InputNumber v-model="createProductStore.inputs.salePrice" inputId="salePrice" />
          <span class="font-bold whitespace-nowrap">만원</span>
        </div>
      </div>
    </div>
    <div v-else-if="createProductStore.inputs.type === '전세'">
      <div class="flex flex-col gap-2 mt-4">
        <label for="deposit" class="font-bold text-lg text-surface-800"> 보증금 </label>
        <div class="flex items-center gap-2">
          <InputNumber v-model="createProductStore.inputs.deposit" inputId="deposit" />
          <span class="font-bold whitespace-nowrap">만원</span>
        </div>
      </div>
    </div>
    <div v-else-if="createProductStore.inputs.type === '월세'">
      <div class="flex flex-col gap-2 mt-4">
        <label for="deposit" class="font-bold text-lg text-surface-800"> 보증금 </label>
        <div class="flex items-center gap-2">
          <InputNumber v-model="createProductStore.inputs.deposit" inputId="deposit" />
          <span class="font-bold whitespace-nowrap">만원</span>
        </div>
      </div>
      <div class="flex flex-col gap-2 mt-4">
        <label for="monthlyRent" class="font-bold text-lg text-surface-800"> 월세 </label>
        <div class="flex items-center gap-2">
          <InputNumber v-model="createProductStore.inputs.monthlyRent" inputId="monthlyRent" />
          <span class="font-bold whitespace-nowrap">만원</span>
        </div>
      </div>
    </div>
    <div class="mt-4 flex justify-end">
      <Button @click="setStep(1)" :disabled="!createProductStore.completeStep1">
        <div class="flex items-center gap-2">
          <span>다음</span>
          <i class="pi pi-arrow-right text-xs"></i>
        </div>
      </Button>
    </div>
  </div>
</template>

<style scoped></style>
