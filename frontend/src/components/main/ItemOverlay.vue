<script setup>
import { defineProps } from 'vue';
import { KakaoMapCustomOverlay } from 'vue3-kakao-maps';
import { computed } from 'vue';
import { twMerge } from 'tailwind-merge';
import router from '@/router';
import { useItemStore } from '@/stores/ItemStore';

const props = defineProps({
  item: Object
});

const itemStore = useItemStore();

const price = computed(() => {
  if (props.item === null) return '';
  switch (props.item.type) {
    case '매매': {
      const { salePrice } = props.item;
      if (salePrice < 1000) {
        return salePrice;
      } else if (salePrice < 10000 && salePrice % 1000 == 0) {
        return salePrice / 1000 + '천';
      } else if (salePrice < 10000) {
        return (salePrice / 1000).toFixed(1) + '천';
      } else if (salePrice % 10000 == 0) {
        return salePrice / 10000 + '억';
      } else {
        return (salePrice / 10000).toFixed(1) + '억';
      }
    }
    case '전세': {
      const { deposit } = props.item;
      if (deposit < 1000) {
        return deposit;
      } else if (deposit < 10000 && deposit % 1000 == 0) {
        return deposit / 1000 + '천';
      } else if (deposit < 10000) {
        return (deposit / 1000).toFixed(1) + '천';
      } else if (deposit % 10000 == 0) {
        return deposit / 10000 + '억';
      } else {
        return (deposit / 10000).toFixed(1) + '억';
      }
    }
    case '월세': {
      const { deposit, monthlyRent } = props.item;
      if (deposit < 1000) {
        return deposit + ' / ' + monthlyRent;
      } else if (deposit < 10000 && deposit % 1000 == 0) {
        return deposit / 1000 + '천 / ' + monthlyRent;
      } else if (deposit < 10000) {
        return (deposit / 1000).toFixed(1) + '천 / ' + monthlyRent;
      } else if (deposit % 10000 == 0) {
        return deposit / 10000 + '억 / ' + monthlyRent;
      } else {
        return (deposit / 10000).toFixed(1) + '억 / ' + monthlyRent;
      }
    }
    default:
      return '';
  }
});

const onClickOverlay = () => {
  if (itemStore.selectedItemId === props.item.itemId) return;
  console.log(props.item.itemId);
  itemStore.setSelectedItemId(props.item.itemId);
  router.push({ name: 'ItemDetail', params: { itemId: props.item.itemId } });
};
</script>

<template>
  <KakaoMapCustomOverlay :lat="item.latitude" :lng="item.longitude">
    <div
      class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform z-10"
    >
      <div
        class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-9 border rounded -left-[100%] z-20"
      >
        <span class="text-xs text-surface-500">{{ item.address }}</span>
      </div>
      <button
        @click="onClickOverlay"
        :class="
          twMerge(
            'flex flex-col items-center justify-center min-w-12 min-h-12 px-2',
            item.zzim ? 'bg-mypink-1' : 'bg-blue-500'
          )
        "
      >
        <span class="text-xs text-primary-inverse opacity-90">{{ item.type }}</span>
        <span class="text-primary-inverse text-sm font-bold">{{ price }}</span>
      </button>
      <div
        :class="
          twMerge(
            'border-r-8 border-l-8 border-t-8 border-t-primary border-r-transparent border-l-transparent',
            item.zzim ? 'border-t-mypink-1' : 'border-t-blue-500'
          )
        "
      ></div>
    </div>
  </KakaoMapCustomOverlay>
</template>

<style scoped></style>
