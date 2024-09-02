<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import InputGroup from 'primevue/inputgroup';
import { useCreateProductStore } from '@/stores/createProduct';
import { ref } from 'vue';
import { twMerge } from 'tailwind-merge';
import { searchAddress } from '@/api/address';

const createProductStore = useCreateProductStore();
const { setStep } = createProductStore;

const searchParams = ref({
  keyword: ''
});

const searchResults = ref({
  common: null,
  juso: null
});

const detailAddressRef = ref(null);

const onSearchAddress = () => {
  if (searchParams.value.keyword.length < 2) return;
  searchAddress(searchParams.value).then((res) => {
    searchResults.value = res.data.results;
  });
};

const onChangeSearchInput = (e) => {
  searchParams.value.keyword = e.target.value;
};

const onSelectAddress = (address) => {
  createProductStore.inputs.address = address;
  detailAddressRef.value.focus();
};
</script>

<template>
  <div class="flex flex-col">
    <div class="flex flex-col gap-2">
      <label for="searchKeyword" class="font-bold text-surface-800">주소 검색</label>
      <form @submit.prevent="onSearchAddress">
        <InputGroup>
          <InputText
            :modelValue="searchParams.keyword"
            placeholder="2글자 이상 입력"
            id="searchKeyword"
            @input="onChangeSearchInput"
          />
          <Button type="submit" icon="pi pi-search" />
        </InputGroup>
      </form>
    </div>
    <div v-if="searchResults.common?.errorCode === 'E0006'" class="flex justify-center p-4">
      <span> 주소를 상세히 입력해 주시기 바랍니다.</span>
    </div>
    <div v-else-if="searchResults.juso?.length === 0" class="flex justify-center p-4">
      <span> 검색 결과가 없습니다.</span>
    </div>
    <div v-else-if="0 < searchResults.juso?.length">
      <div class="mt-4">
        <div class="py-4 flex gap-2 items-center">
          <h4 class="text-lg font-bold text-surface-800">검색결과</h4>
          <span
            :class="
              twMerge(
                searchResults.common.totalCount === 0 ? 'text-surface-600' : 'text-primary',
                'font-bold text-lg'
              )
            "
            >{{ searchResults.common.totalCount }}</span
          >
        </div>
        <ul class="border">
          <li
            v-for="juso in searchResults.juso"
            :key="juso.roadAddr"
            class="[&:not(:last-child)]:border-b p-2"
          >
            <div class="flex items-center justify-between gap-2">
              <p class="flex flex-col">
                <span class="font-semibold text-surface-800">{{ juso.roadAddr }}</span>
                <span class="text-surface-600 text-sm">{{ juso.jibunAddr }}</span>
              </p>
              <Button @click="onSelectAddress(juso.jibunAddr)" size="small">선택</Button>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="flex flex-col gap-2 mt-4">
      <div class="flex flex-col gap-1">
        <span class="font-bold text-surface-800">주소</span>
        <label for="searchKeyword" class="w-full">
          <div
            class="w-full border min-h-[41px] rounded-md border-surface-300 px-3 py-2 text-surface-800"
          >
            {{ createProductStore.inputs.address }}
          </div>
        </label>
      </div>
      <div class="flex flex-col gap-1">
        <label ref="detailAddressRef" for="detailAddress" class="font-bold text-surface-800"
          >상세주소</label
        >
        <InputText id="detailAddress" v-model="createProductStore.inputs.detailAddress" />
      </div>
    </div>
    <div class="mt-8 flex justify-between">
      <Button @click="setStep(0)" severity="secondary">
        <div class="flex items-center gap-2">
          <i class="pi pi-arrow-left text-xs"></i>
          <span>이전</span>
        </div>
      </Button>
      <Button @click="setStep(2)" :disabled="!createProductStore.completeStep2">
        <div class="flex items-center gap-2">
          <span>다음</span>
          <i class="pi pi-arrow-right text-xs"></i>
        </div>
      </Button>
    </div>
  </div>
</template>

<style scoped></style>
