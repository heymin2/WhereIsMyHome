<script setup>
import HouseSearch from '@/components/main/HouseSearch.vue';
import AptDetail from '@/components/main/AptDetail.vue';
import HouseSearchFilter from '@/components/main/HouseSearchFilter.vue';
import MyMenu from '@/components/main/MyMenu.vue';
import { useHouseStore } from '@/stores/house';

const houseStore = useHouseStore();
</script>

<template>
  <div class="w-full sm:w-96 bg-white overflow-y-auto">
    <div>
      <HouseSearch />
    </div>
    <div v-show="houseStore.selectedHouse === null">
      <HouseSearchFilter />
    </div>
    <div v-show="houseStore.selectedHouse !== null">
      <AptDetail />
    </div>
  </div>
</template>

<style scoped></style>
