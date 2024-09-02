<script setup>
import { toYYYYMMDD } from '@/utils/dateTime';
import { computed, defineProps } from 'vue';
import { useQueryClient } from '@tanstack/vue-query';
import router from '@/router';
import Menu from 'primevue/menu';
import { ref } from 'vue';

import { useConfirm } from 'primevue/useconfirm';
import { useToast } from 'primevue/usetoast';
import { changeSoldStatus, deleteItem, getItemDetail } from '@/api/item';
import { useItemStore } from '@/stores/ItemStore';
import { useMainMapStore } from '@/stores/mainMap';
import { useDialog } from 'primevue/usedialog';
import ItemUpdateModal from '@/components/modal/ItemUpdateModal.vue';

const confirm = useConfirm();
const toast = useToast();
const dialog = useDialog();

const props = defineProps({
  item: {
    type: {
      itemId: Number,
      type: String,
      longitude: Number,
      latitude: Number,
      address: String,
      salePrice: Number,
      deposit: Number,
      monthlyRent: Number,
      size: Number,
      sold: Boolean,
      createdAt: String
    },
    required: true
  },
  order: {
    type: Number,
    required: true
  }
});

const queryClient = useQueryClient();
const { setCoordinate, setZoomLevel } = useMainMapStore();
const itemStore = useItemStore();
const { setSelectedItemId } = itemStore;

const goDetail = (e) => {
  getItemDetail(props.item.itemId).then((res) => {
    console.log(res.data);
  });
  if (e.target.id === 'menuBtn' || e.target.parentElement.id === 'menuBtn') return;

  setSelectedItemId(props.item.itemId);
  router.push(`/item/${props.item.itemId}`);
  setZoomLevel(3);
  setCoordinate(props.item.latitude, props.item.longitude);
};
const onClickDelete = () => {
  confirm.require({
    message: '해당 매물을 삭제하시겠습니까?',
    header: '매물 삭제',
    icon: 'pi pi-info-circle',
    rejectLabel: '취소',
    acceptLabel: '삭제',
    rejectClass: 'p-button-secondary p-button-outlined',
    acceptClass: 'p-button-danger',
    accept: () => {
      deleteItem(props.item.itemId).then(() => {
        queryClient.invalidateQueries('myItemList');
        toast.add({
          severity: 'success',
          summary: '매물 삭제 성공',
          detail: '매물이 삭제되었습니다.',
          life: 3000
        });
      });
    },
    reject: () => {}
  });
};

const onClickChangeStatus = () => {
  changeSoldStatus(props.item.itemId).then(() => {
    queryClient.invalidateQueries('myItemList');
    toast.add({
      severity: 'success',
      summary: '거래 상태 변경 완료',
      detail: '거래 상태가 변경되었습니다.',
      life: 3000
    });
  });
};
const openUpdateModal = () => {
  dialog.open(ItemUpdateModal, {
    props: {
      style: {},
      modal: true,
      header: '매물 수정하기',
      draggable: false

      // dismissableMask: true
    },
    data: {
      itemId: props.item.itemId
    },
    onClose: () => {}
  });
};

const menu = ref();
const menuItems = computed(() => {
  return [
    {
      label: props.item.sold ? '판매중으로 변경' : '거래완료로 변경',
      icon: 'pi pi-check',
      class: 'text-sm',
      command: onClickChangeStatus
    },
    {
      label: '수정하기',
      icon: 'pi pi-pencil',
      class: 'text-sm',
      command: () => {
        openUpdateModal();
      }
    },
    {
      label: '삭제하기',
      icon: 'pi pi-trash',
      class: 'text-sm',
      command: onClickDelete
    }
  ];
});

const toggle = (event) => {
  menu.value.toggle(event);
};
</script>

<template>
  <div
    @click="goDetail"
    class="flex items-center w-full cursor-pointer hover:bg-surface-50 transition-colors px-4 py-4 gap-4"
  >
    <div class="font-bold text-surface-800 text-lg">{{ order }}</div>
    <div class="flex flex-col gap-1">
      <span class="font-semibold text-surface-800">{{ item.address }}</span>
      <div class="flex items-center gap-1 text-xs text-surface-600">
        <div class="flex items-center gap-1">
          <i class="pi pi-calendar"></i>
          <span>등록일</span>
        </div>
        <span>:</span>
        <span>{{ toYYYYMMDD(item.createdAt) }}</span>
        <div v-if="item.sold" class="px-2 py-1 bg-surface-200 text-surface-600">
          <span class="text-xs">거래완료</span>
        </div>
        <div v-else class="px-2 py-1 bg-primary text-primary-inverse">
          <span class="text-xs">판매중</span>
        </div>
      </div>
    </div>
    <button
      id="menuBtn"
      class="ml-auto text-surface-400 p-2"
      @click="toggle"
      aria-haspopup="true"
      aria-controls="overlay_menu"
    >
      <i class="pi pi-ellipsis-v text-surface-400"></i>
    </button>
    <Menu ref="menu" id="overlay_menu" :model="menuItems" :popup="true" />
  </div>
</template>

<style scoped></style>
