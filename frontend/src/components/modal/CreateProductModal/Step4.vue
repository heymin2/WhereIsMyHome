<script setup>
import { ref } from 'vue';
import { useToast } from 'primevue/usetoast';
import Button from 'primevue/button';
import Galleria from 'primevue/galleria';
import { useCreateProductStore } from '@/stores/createProduct';
import { createItem as requestCreateItem } from '@/api/item';
import { useMutation } from '@tanstack/vue-query';
const createProductStore = useCreateProductStore();
const { setStep } = createProductStore;
const toast = useToast();

const images = ref([]);

const base64Images = ref([]);

const files = ref(null);
const onChangeFileInput = (e) => {
  files.value = e.target.files;

  base64Images.value = []; // Clear the existing images

  Array.from(e.target.files).forEach((file) => {
    const reader = new FileReader();
    reader.onload = (e) => {
      base64Images.value.push(e.target.result);
    };
    reader.readAsDataURL(file);
  });
  console.log(base64Images.value);
};

const { isPending, mutateAsync: createItem } = useMutation({
  mutationFn: requestCreateItem
});

const onSubmit = () => {
  const formData = new FormData();
  for (let i = 0; i < files.value.length; i++) {
    formData.append('file', files.value[i]);
  }
  const json = {
    type: createProductStore.inputs.type,
    address: createProductStore.inputs.address + ' ' + createProductStore.inputs.detailAddress,
    title: createProductStore.inputs.title,
    description: createProductStore.inputs.description,
    salePrice: createProductStore.inputs.salePrice,
    deposit: createProductStore.inputs.deposit,
    monthlyRent: createProductStore.inputs.monthlyRent,
    rooms: Number(createProductStore.inputs.rooms),
    bathrooms: Number(createProductStore.inputs.bathrooms),
    size: Number(createProductStore.inputs.size)
  };
  formData.append('data', new Blob([JSON.stringify(json)], { type: 'application/json' }));

  createItem(formData)
    .then((res) => {
      setStep(4);
    })
    .catch((err) => {
      toast.add({
        severity: 'error',
        summary: '매물등록 실패',
        life: 3000
      });
    });
};
</script>

<template>
  <div class="flex flex-col">
    <div class="flex flex-col mt-10 items-center">
      <label for="fileInput" class="mb-5">
        <div
          class="flex items-center gap-2 bg-primary text-primary-inverse w-max px-4 py-2 text-sm rounded-md"
        >
          <i class="pi pi-plus text-xs"></i>
          <span>이미지 업로드 </span>
        </div>
      </label>
      <input @change="onChangeFileInput" type="file" multiple class="hidden" id="fileInput" />
      <Galleria
        :value="base64Images"
        :responsiveOptions="responsiveOptions"
        :numVisible="5"
        circular
        containerStyle="object-fit: contain;  "
      >
        <template #item="slotProps">
          <img :src="slotProps.item" style="width: 100%" />
        </template>
        <template #thumbnail="slotProps">
          <img :src="slotProps.item" />
        </template>
      </Galleria>
    </div>
    <div class="mt-8 flex justify-between">
      <Button @click="setStep(2)" severity="secondary">
        <div class="flex items-center gap-2">
          <i class="pi pi-arrow-left text-xs"></i>
          <span>이전</span>
        </div>
      </Button>
      <Button @click="onSubmit" :disabled="!files || files?.length === 0 || isPending">
        <i v-if="isPending" class="pi pi-spin pi-spinner"></i>
        <span v-else>매물 등록</span>
      </Button>
    </div>
  </div>
</template>

<!--  -->
