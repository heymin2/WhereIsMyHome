<script setup>
import Dropdown from 'primevue/dropdown';

import { useHouseStore } from '@/stores/house';
import { computed, ref } from 'vue';
import { toYYYYMMDD } from '@/utils/dateTime';

const houseStore = useHouseStore();

const address = computed(() => {
  if (houseStore.selectedHouse === null) return '';
  const { sido, gugun, dong, jibun } = houseStore.selectedHouse;
  return `${sido} ${gugun} ${dong} ${jibun}`;
});

const selectedSize = ref('전체');

const onSelectSize = (size) => {
  selectedSize.value = size;
};

const filteredDealList = computed(() => {
  if (selectedSize.value === null) return [];
  if (selectedSize.value === '전체') return houseStore.dealList;
  return houseStore.dealList.filter((deal) => deal.size === selectedSize.value);
});

const aptAge = computed(() => {
  if (houseStore.selectedHouse === null) return '';
  const { buildYear } = houseStore.selectedHouse;
  const currentYear = new Date().getFullYear();
  return currentYear - buildYear + 1 + '년차';
});

const sizeOptions = computed(() => {
  if (houseStore.dealList === null) return ['전체'];
  const set = new Set();
  houseStore.dealList.forEach((deal) => {
    set.add(deal.size);
  });
  return ['전체', ...[...set].sort((a, b) => a - b)];
});
</script>

<template>
  <div>
    <div class="flex items-start justify-between py-2">
      <div class="flex flex-col mb-8">
        <h3 class="text-xl font-bold text-primary mb-1">
          {{ houseStore.selectedHouse?.apartmentName }}
        </h3>

        <p class="text-surface-600 text-sm">{{ address }}</p>
        <div class="flex items-center text-xs opacity-90 gap-1">
          <span>건축년도</span>
          <span class="">{{ houseStore.selectedHouse?.buildYear }}년({{ aptAge }})</span>
        </div>
      </div>
      <Dropdown
        :modelValue="selectedSize"
        @change="onSelectSize($event.value)"
        optionLabel="label"
        :options="sizeOptions"
        placeholder="면적"
      >
        <template #value="slotProps">
          <div v-if="slotProps.value" class="flex items-center">
            <div v-if="slotProps.value === '전체'">전체</div>
            <div v-else>{{ slotProps.value.toFixed(1) }}m<sup>2</sup></div>
          </div>
          <span v-else>
            {{ slotProps.placeholder }}
          </span>
        </template>
        <template #option="slotProps">
          <div class="flex items-center">
            <div v-if="slotProps.option === '전체'">전체</div>
            <div v-else>{{ slotProps.option.toFixed(1) }}m<sup>2</sup></div>
          </div>
        </template>
      </Dropdown>
    </div>
    <table class="w-full">
      <thead class="bg-gray-100 border sticky">
        <tr>
          <th class="px-4 py-2">거래일</th>
          <th class="px-4 py-2">거래금액(만원)</th>
          <th class="px-4 py-2">전용면적</th>
          <th class="px-4 py-2">층</th>
        </tr>
      </thead>
      <tbody class="border">
        <tr
          v-for="deal in filteredDealList"
          :key="deal.dealId"
          class="[&:not(:last-child)]:border-b even:bg-gray-100"
        >
          <td class="px-4 py-2">
            {{ toYYYYMMDD(new Date(deal.dealYear, deal.dealMonth - 1, deal.dealDay)) }}
          </td>
          <td class="px-4 py-2 text-right">{{ deal.dealAmount.toLocaleString() }}</td>
          <td class="px-4 py-2 text-center">{{ deal.size.toFixed(1) }}m<sup>2</sup></td>
          <td class="px-4 py-2 text-center">{{ deal.floor }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped></style>
