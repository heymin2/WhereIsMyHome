<script setup>
import { onMounted } from 'vue';
import { RouterView } from 'vue-router';

import ConfirmPopup from 'primevue/confirmpopup';

import Toast from 'primevue/toast';
import DynamicDialog from 'primevue/dynamicdialog';
import ConfirmDialog from 'primevue/confirmdialog';
import { useUserStore } from './stores/user';

const { boot } = useUserStore();

onMounted(() => {
  boot();
});
</script>

<template>
  <RouterView />
  <Toast />
  <DynamicDialog />
  <ConfirmDialog />
  <!-- <ConfirmPopup /> -->
</template>

<style scoped></style>
