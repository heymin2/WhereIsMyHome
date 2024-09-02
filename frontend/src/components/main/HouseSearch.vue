<script setup>
import { useHouseSearchStore } from '@/stores/houseSearch';

const houseSearchStore = useHouseSearchStore();
const { params, setKeyword } = houseSearchStore;
</script>

<template>
  <div>
    <div class="bg-primary sm:bg-white flex flex-col w-full relative">
      <div class="flex p-3 items-center border-b gap-4">
        <i class="pi pi-warehouse text-xl text-white sm:text-primary"></i>
        <form class="flex flex-1 sm:px-1 items-center mr-4 sm:mr-0">
          <input
            type="text"
            name=""
            id=""
            :value="params.keyword"
            @input="setKeyword($event.target.value)"
            class="bg-primary sm:bg-white text-white text-lg outline-none flex-1 sm:text-surface-700 placeholder:text-surface-100 placeholder:opacity-80 sm:placeholder:text-surface-500"
            placeholder="지역명, 아파트명으로 검색"
          />
          <button type="reset" v-show="0 < params.keyword.length" @click="setKeyword('')">
            <i
              class="text-white opacity-70 sm:opacity-100 pi pi-times-circle sm:text-surface-500 mr-2"
            ></i>
          </button>
          <button type="submit" label="검색">
            <i class="text-white pi pi-search sm:text-surface-500 text-xl sm:text-base"></i>
          </button>
        </form>
        <button class="sm:hidden">
          <i class="text-white pi pi-user sm:text-surface-500 text-xl sm:text-base"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
