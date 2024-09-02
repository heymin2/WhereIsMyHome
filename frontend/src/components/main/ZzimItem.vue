<script setup>
import { toYYYYMMDD } from '@/utils/dateTime';
import { computed, defineProps } from 'vue';
import { useQueryClient } from '@tanstack/vue-query';
import { useMainMapStore } from '@/stores/mainMap';
import router from '@/router';
import { unlikeItem } from '@/api/like';
import { useItemStore } from '@/stores/ItemStore';

const props = defineProps({
  item: {
    type: Object,
    required: true
  },
  order: {
    type: Number,
    required: true
  }
});

const queryClient = useQueryClient();
const itemStore = useItemStore();
const { setCoordinate, setZoomLevel } = useMainMapStore();

const goDetail = (e) => {
  if (e.target.id === 'deleteBtn' || e.target.parentElement.id === 'deleteBtn') return;

  itemStore.setSelectedItemId(props.item.itemId);
  router.push(`/item/${props.item.itemId}`);
  setCoordinate(props.item.latitude, props.item.longitude);
  setZoomLevel(3);
};

const onClickDelete = () => {
  unlikeItem(props.item.itemId).then(() => {
    queryClient.invalidateQueries('zzimItem');
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
      <span class="text-sm text-surface-600">{{ item.address }}</span>
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
