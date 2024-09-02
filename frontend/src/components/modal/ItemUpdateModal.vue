<script setup>
import { getItemDetail } from '@/api/item';
import { inject, onMounted, ref } from 'vue';
import InputNumber from 'primevue/inputnumber';
import InputText from 'primevue/inputtext';
import Textarea from 'primevue/textarea';
import Button from 'primevue/button';
import { updateItem as requestUpdateItem } from '@/api/item';
import { useMutation, useQueryClient } from '@tanstack/vue-query';
import { computed } from 'vue';

const dialogRef = inject('dialogRef');
const queryClient = useQueryClient();

const { isPending, mutateAsync: updateItem } = useMutation({
  mutationFn: requestUpdateItem
});

const inputs = ref({
  itemId: null,
  size: null,
  rooms: null,
  bathrooms: null,
  title: null,
  description: null,
  type: null,
  salePrice: null,
  monthlyRent: null,
  deposit: null
});

const closeModal = () => {
  dialogRef.value.close();
};

const isValid = computed(() => {
  return (
    inputs.value.size &&
    inputs.value.rooms &&
    inputs.value.bathrooms &&
    inputs.value.title &&
    inputs.value.description
  );
});

const onSubmit = () => {
  if (!isValid.value) return;

  updateItem(inputs.value).then(() => {
    queryClient.invalidateQueries(['myItemList']);
    closeModal();
    console.log('무야호');
  });
};

onMounted(() => {
  const itemId = dialogRef.value.options.data.itemId;
  getItemDetail(itemId).then((res) => {
    const {
      size,
      rooms,
      bathrooms,
      title,
      description,
      itemId,
      type,
      salePrice,
      monthlyRent,
      deposit
    } = res.data;
    inputs.value = {
      itemId,
      size,
      rooms,
      bathrooms,
      title,
      description,
      type,
      salePrice,
      monthlyRent,
      deposit
    };
  });
});
</script>

<template>
  <div v-if="inputs.itemId">
    <div class="w-full flex flex-col gap-2">
      <label class="flex flex-col gap-1">
        <span class="font-bold">전용면적</span>
        <div class="flex items-center gap-1">
          <InputNumber v-model="inputs.size" class="text-right" min-fraction-digits="2" />
          <p><span>m</span><sup>2</sup></p>
        </div>
      </label>
      <label class="flex flex-col gap-1">
        <span class="font-bold">방</span>
        <div class="flex items-center gap-1">
          <InputNumber v-model="inputs.rooms" class="text-right" />
          <span>개</span>
        </div>
      </label>
      <label class="flex flex-col gap-1">
        <span class="font-bold">화장실</span>
        <div class="flex items-center gap-1">
          <InputNumber v-model="inputs.bathrooms" class="text-right" />
          <span>개</span>
        </div>
      </label>

      <label class="flex flex-col gap-1">
        <span class="font-bold">제목</span>
        <InputText v-model="inputs.title" />
      </label>
      <label class="flex flex-col gap-1">
        <span class="font-bold">매물 소개</span>
        <Textarea v-model="inputs.description" class="h-48" />
      </label>
    </div>
    <div class="mt-8 flex justify-end gap-2">
      <Button @click="closeModal" severity="secondary"> 취소 </Button>
      <Button @click="onSubmit" :disabled="isPending">
        <i v-if="isPending" class="pi pi-spin pi-spinner"></i>
        <span v-else>매물 수정</span>
      </Button>
    </div>
  </div>
</template>

<style scoped></style>
