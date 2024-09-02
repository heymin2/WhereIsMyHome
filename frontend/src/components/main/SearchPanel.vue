<script setup>
import HouseSearch from '@/components/main/HouseSearch.vue';
import HouseSearchFilter from '@/components/main/HouseSearchFilter.vue';
import { useHouseStore } from '@/stores/house';

const houseStore = useHouseStore();
</script>

<template>
  <div class="w-full sm:w-96 sm:p-3 overflow-y-auto">
    <div class="bg-white">
      <HouseSearch />
      <HouseSearchFilter />
    </div>
  </div>
</template>

<style scoped></style>
