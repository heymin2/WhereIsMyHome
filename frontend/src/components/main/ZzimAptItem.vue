<script setup>
import { toYYYYMMDD } from '@/utils/dateTime';
import { computed, defineProps } from 'vue';
import { useQueryClient } from '@tanstack/vue-query';
import { useHouseStore } from '@/stores/house';
import { useMainMapStore } from '@/stores/mainMap';
import router from '@/router';
import { unlikeApt } from '@/api/like';

const props = defineProps({
  item: {
    type: {
      zzimId: Number,
      aptId: Number,
      sido: String,
      gugun: String,
      dong: String,
      jibun: String,
      apartmentName: String,
      createdAt: String,
      longitude: Number,
      latitude: Number
    },
    required: true
  },
  order: {
    type: Number,
    required: true
  }
});

const queryClient = useQueryClient();
const houseStore = useHouseStore();
const { setCoordinate } = useMainMapStore();

const address = computed(() => {
  return `${props.item.sido} ${props.item.gugun} ${props.item.dong} ${props.item.jibun}`;
});

const goDetail = (e) => {
  if (e.target.id === 'deleteBtn' || e.target.parentElement.id === 'deleteBtn') return;

  houseStore.setSelectedHouseId(props.item.aptId);
  router.push(`/apt/${props.item.aptId}`);
  setCoordinate(props.item.latitude, props.item.longitude);
};

const onClickDelete = () => {
  unlikeApt(props.item.aptId).then(() => {
    queryClient.invalidateQueries('zzimApt');
  });
};
</script>

<template>
  <div
    @click="goDetail"
    class="flex items-center w-full cursor-pointer hover:bg-surface-50 transition-colors px-4 py-4 gap-4"
  >
    <div class="font-bold text-surface-800 text-lg">{{ order }}</div>
    <div class="flex flex-col">
      <span class="font-semibold text-surface-800">{{ item.apartmentName }}</span>
      <span class="text-sm text-surface-600">{{ address }}</span>
      <div class="flex items-center gap-1 text-xs text-surface-600">
        <div class="flex items-center gap-1">
          <i class="pi pi-calendar"></i>
          <span>찜한날짜</span>
        </div>
        <span>:</span>
        <span>{{ toYYYYMMDD(item.createdAt) }}</span>
      </div>
    </div>
    <button
      id="deleteBtn"
      @click="onClickDelete"
      class="ml-auto text-surface-400 hover:text-red-500 transition-colors"
    >
      <i class="pi pi-trash"></i>
    </button>
  </div>
</template>

<style scoped></style>
