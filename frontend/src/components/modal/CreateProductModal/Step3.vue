<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import InputNumber from 'primevue/inputnumber';
import Textarea from 'primevue/textarea';
import InputGroup from 'primevue/inputgroup';
import { useCreateProductStore } from '@/stores/createProduct';
import { ref } from 'vue';
import { twMerge } from 'tailwind-merge';
import { searchAddress } from '@/api/address';
const createProductStore = useCreateProductStore();
const { setStep } = createProductStore;
</script>

<template>
  <div class="w-full flex flex-col gap-2">
    <label class="flex flex-col gap-1">
      <span class="font-bold">전용면적</span>
      <div class="flex items-center gap-1">
        <InputNumber
          v-model="createProductStore.inputs.size"
          class="text-right"
          min-fraction-digits="2"
        />
        <p><span>m</span><sup>2</sup></p>
      </div>
    </label>
    <label class="flex flex-col gap-1">
      <span class="font-bold">방</span>
      <div class="flex items-center gap-1">
        <InputNumber v-model="createProductStore.inputs.rooms" class="text-right" />
        <span>개</span>
      </div>
    </label>
    <label class="flex flex-col gap-1">
      <span class="font-bold">화장실</span>
      <div class="flex items-center gap-1">
        <InputNumber v-model="createProductStore.inputs.bathrooms" class="text-right" />
        <span>개</span>
      </div>
    </label>

    <label class="flex flex-col gap-1">
      <span class="font-bold">제목</span>
      <InputText v-model="createProductStore.inputs.title" />
    </label>
    <label class="flex flex-col gap-1">
      <span class="font-bold">매물 소개</span>
      <Textarea v-model="createProductStore.inputs.description" class="h-48" />
    </label>

    <div class="mt-8 flex justify-between">
      <Button @click="setStep(1)" severity="secondary">
        <div class="flex items-center gap-2">
          <i class="pi pi-arrow-left text-xs"></i>
          <span>이전</span>
        </div>
      </Button>
      <Button @click="setStep(3)" :disabled="!createProductStore.completeStep3">
        <div class="flex items-center gap-2">
          <span>다음</span>
          <i class="pi pi-arrow-right text-xs"></i>
        </div>
      </Button>
    </div>
  </div>
</template>

<style scoped></style>
