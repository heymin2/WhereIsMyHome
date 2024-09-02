<script setup>
import { ref, onMounted } from 'vue';
import Button from 'primevue/button';
import Dropdown from 'primevue/dropdown';
import Slider from 'primevue/slider';

import { useHouseSearchStore } from '@/stores/houseSearch';
import { getDongList, getGugunList, getSidoList } from '@/api/area';
import { useMainMapStore } from '@/stores/mainMap';
import { twMerge } from 'tailwind-merge';

const houseSearchStore = useHouseSearchStore();
const mainMapStore = useMainMapStore();
const { setSido, setGugun, setDong, setMinPrice, setMaxPrice } = houseSearchStore;
const { setCoordinate, setZoomLevel } = mainMapStore;

const isFilterOpen = ref(false);
const priceButtonRef = ref(null);
const sizeButtonRef = ref(null);
const regionButtonRef = ref(null);
const selectedFilter = ref(null);
const address = ref({
  sido: [],
  gugun: [],
  dong: []
});

const openFilter = () => {
  isFilterOpen.value = true;
};

const closeFilter = () => {
  isFilterOpen.value = false;
};

const onClickPreviewButton = (field) => {
  openFilter();
  switch (field) {
    case 'price':
      priceButtonRef.value.$el.click();
      break;
    case 'size':
      sizeButtonRef.value.$el.click();
      break;
    case 'region':
      regionButtonRef.value.$el.click();
      break;
  }
};

const onClickFilterButton = (field) => {
  switch (field) {
    case 'price':
      selectedFilter.value = 'price';

      break;
    case 'size':
      selectedFilter.value = 'size';

      break;
    case 'region':
      selectedFilter.value = 'region';
      break;
  }
};

const onClickSido = (sido) => {
  setSido(sido);
  setGugun(null);
  setDong(null);
  setZoomLevel(7);
  setCoordinate(sido.latitude, sido.longitude);
  getGugunList(sido.address).then((res) => {
    address.value.gugun = res.data;
  });
};

const onClickGugun = (gugun) => {
  setGugun(gugun);
  setDong(null);
  setZoomLevel(5);
  setCoordinate(gugun.latitude, gugun.longitude);
  getDongList(houseSearchStore.params.sido.address, gugun.address).then((res) => {
    address.value.dong = res.data;
  });
};

const onClickDong = (dong) => {
  setDong(dong);
  setCoordinate(dong.latitude, dong.longitude);
  setZoomLevel(3);
};

onMounted(() => {
  getSidoList().then((res) => {
    address.value.sido = res.data;
  });
});

const onClickPriceOption = (option) => {
  setMinPrice(option.minPrice);
  setMaxPrice(option.maxPrice);
};

const onChangeSlider = ([minPrice, maxPrice]) => {
  setMinPrice(minPrice);
  setMaxPrice(maxPrice);
};

const priceOptions = [
  {
    label: '전체',
    minPrice: 0,
    maxPrice: 200
  },
  {
    label: '~2억',
    minPrice: 0,
    maxPrice: 20
  },
  {
    label: '~3억',
    minPrice: 0,
    maxPrice: 30
  },
  {
    label: '~4억',
    minPrice: 0,
    maxPrice: 40
  },
  {
    label: '~5억',
    minPrice: 0,
    maxPrice: 50
  },
  {
    label: '~10억',
    minPrice: 0,
    maxPrice: 100
  },
  {
    label: '~15억',
    minPrice: 0,
    maxPrice: 150
  },
  {
    label: '20억~',
    minPrice: 200,
    maxPrice: null
  }
];
</script>

<template>
  <div class="flex flex-col relative z-30">
    <div v-show="isFilterOpen">
      <div class="">
        <div class="grid grid-cols-2 gap-2 p-2">
          <Button
            @click="onClickFilterButton('region')"
            outlined
            :severity="selectedFilter === 'region' ? null : 'secondary'"
            size="small"
            ref="regionButtonRef"
            >지역</Button
          >
          <Button
            @click="onClickFilterButton('price')"
            outlined
            :severity="selectedFilter === 'price' ? null : 'secondary'"
            size="small"
            ref="priceButtonRef"
            >가격</Button
          >
          <Button
            @click="onClickFilterButton('size')"
            outlined
            :severity="selectedFilter === 'size' ? null : 'secondary'"
            size="small"
            ref="sizeButtonRef"
            >평형</Button
          >
        </div>
      </div>
      <div v-show="selectedFilter === 'region'" class="flex flex-col gap-3 px-2 py-3 mt-2">
        <div class="flex flex-col gap-1">
          <span class="font-bold text-sm">시도명</span>
          <Dropdown
            :modelValue="houseSearchStore.params.sido"
            @change="onClickSido($event.value)"
            optionLabel="address"
            :options="address.sido"
            placeholder="시·도 선택"
            class="w-full"
          />
        </div>
        <div class="flex flex-col gap-1">
          <span class="font-bold text-sm">시군구</span>
          <Dropdown
            :modelValue="houseSearchStore.params.gugun"
            @change="onClickGugun($event.value)"
            :options="address.gugun"
            optionLabel="address"
            :disabled="!houseSearchStore.params.sido"
            placeholder="시·군·구 선택"
            class="w-full"
          />
        </div>
        <div class="flex flex-col gap-1">
          <span class="font-bold text-sm">읍면동</span>
          <Dropdown
            :modelValue="houseSearchStore.params.dong"
            @change="onClickDong($event.value)"
            :options="address.dong"
            optionLabel="address"
            :disabled="!houseSearchStore.params.gugun"
            placeholder="읍·면·동 선택"
            class="w-full"
          />
        </div>
      </div>
      <div v-show="selectedFilter === 'price'" class="flex gap-1 p-2">
        <div class="flex flex-col gap-10 mt-10">
          <div class="w-full px-4">
            <Slider
              @change="onChangeSlider"
              :min="0"
              :max="200"
              :modelValue="[houseSearchStore.params.minPrice, houseSearchStore.params.maxPrice]"
              range
            />
          </div>
          <div class="flex gap-x-2 gap-y-3 flex-wrap">
            <button
              v-for="option in priceOptions"
              :key="option.label"
              @click="onClickPriceOption(option)"
              :class="
                twMerge(
                  'px-4 py-2 bg-surface-100 rounded-full',
                  houseSearchStore.params.minPrice === option.minPrice &&
                    houseSearchStore.params.maxPrice === option.maxPrice
                    ? 'bg-primary text-white'
                    : 'text-primary'
                )
              "
            >
              {{ option.label }}
            </button>
          </div>
        </div>
      </div>
      <div v-show="selectedFilter === 'size'" class="flex gap-1 p-2"></div>
      <div class="p-1 bg-surface-100 flex justify-end">
        <button v-show="houseSearchStore.isFiltered" class="flex items-center text-sm gap-1">
          <i class="pi pi-trash"></i>
          <span>조건 초기화</span>
        </button>
        <Button
          @click="closeFilter"
          v-tooltip.bottom="{
            value: '닫기'
          }"
          text
          severity="secondary"
          size="small"
          class="aspect-square"
          ><i class="pi pi-angle-up"></i
        ></Button>
      </div>
    </div>
    <div v-show="!isFilterOpen" class="flex gap-1 p-2">
      <Button @click="onClickPreviewButton('region')" outlined severity="secondary" size="small"
        >지역</Button
      >
      <Button @click="onClickPreviewButton('price')" outlined severity="secondary" size="small"
        >가격</Button
      >

      <Button @click="onClickPreviewButton('size')" outlined severity="secondary" size="small"
        >평형</Button
      >
    </div>
  </div>
</template>

<style scoped></style>
