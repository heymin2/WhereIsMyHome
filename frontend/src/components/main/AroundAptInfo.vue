<script setup>
import AroundInfoSkeleton from '@/components/main/AroundInfoSkeleton.vue';
import { twMerge } from 'tailwind-merge';
import { computed } from 'vue';
import { useDialog } from 'primevue/usedialog';
import Button from 'primevue/button';
import {
  useAroundCafe,
  useAroundRestaurant,
  useAroundBusStop
} from '@/composables/useAroundAptInfo';
import AroundAptInfoModal from '@/components/modal/AroundAptInfoModal.vue';

const dialog = useDialog();

const openAroundAptInfoModal = (type) => {
  const header = type === 'bus' ? '주변 버스정류장' : type === 'cafe' ? '주변 카페' : '주변 음식점';
  dialog.open(AroundAptInfoModal, {
    props: {
      style: {},
      modal: true,
      header,
      draggable: false
    },
    data: {
      type,
      list:
        type === 'bus' ? busStopList.value : type === 'cafe' ? cafeList.value : restaurantList.value
    }
  });
};

const { cafeList, isLoading: isCafeLoading } = useAroundCafe();
const { restaurantList, isLoading: isRestaurantLoading } = useAroundRestaurant();
const { busStopList, isLoading: isBusStopLoading } = useAroundBusStop();

const previewCafeList = computed(() => {
  return cafeList.value.slice(0, 3);
});

const previewRestaurantList = computed(() => {
  return restaurantList.value.slice(0, 3);
});

const previewBusStopList = computed(() => {
  return busStopList.value.slice(0, 3);
});
</script>

<template>
  <div class="flex flex-col gap-6">
    <AroundInfoSkeleton v-if="isCafeLoading" />
    <div v-else>
      <div class="flex items-center gap-4 mb-2">
        <h4 class="text-lg font-bold">주변 카페</h4>
        <span
          :class="
            twMerge(cafeList?.length === 0 ? 'text-surface-400' : 'text-primary', 'font-bold')
          "
          >{{ cafeList?.length }}</span
        >
      </div>
      <div v-if="cafeList.length === 0" class="flex items-center justify-center">
        <span class="text-sm text-surface-500">주변 카페 정보가 없습니다</span>
      </div>
      <div v-else>
        <div class="flex flex-col gap-2">
          <div v-for="cafe in previewCafeList" :key="cafe.storeId" class="w-full">
            <div class="flex items-center">
              <div class="fill-surface-600 mr-2">
                <svg
                  width="1rem"
                  height="1rem"
                  viewBox="0 0 50 50"
                  version="1.2"
                  baseProfile="tiny"
                  xmlns="http://www.w3.org/2000/svg"
                  overflow="inherit"
                >
                  <path
                    d="M37 7h-28v27c0 2.2 1.8 4 4 4h20c2.2 0 4-1.8 4-4v-5c6.076 0 11-4.925 11-11s-4.924-11-11-11zm0 17v-12c3.314 0 6 2.686 6 6 0 3.313-2.686 6-6 6zm-35 16v2.301c0 1.896 2.069 2.699 4.6 2.699h36.8c2.53 0 4.6-.803 4.6-2.699v-2.301h-46z"
                  />
                </svg>
              </div>
              <span class="text-[15px] font-semibold text-surface-700">{{ cafe.name }}</span>
            </div>
            <div class="flex flex-col">
              <span class="text-sm text-surface-500">{{ cafe.jibun }}</span>
              <span class="text-sm text-surface-500">{{ cafe.distance }}m</span>
            </div>
          </div>
          <Button
            @click="openAroundAptInfoModal('cafe')"
            v-if="3 < cafeList.length"
            size="small"
            outlined
            >카페 전체 보기</Button
          >
        </div>
      </div>
    </div>
    <AroundInfoSkeleton v-if="isRestaurantLoading" />
    <div>
      <div class="flex items-center gap-4 mb-2">
        <h4 class="text-lg font-bold">주변 음식점</h4>
        <span
          :class="
            twMerge(restaurantList?.length === 0 ? 'text-surface-400' : 'text-primary', 'font-bold')
          "
          >{{ restaurantList?.length }}</span
        >
      </div>
      <div v-if="restaurantList.length === 0" class="flex items-center justify-center">
        <span class="text-sm text-surface-500">주변 음식점 정보가 없습니다</span>
      </div>
      <div v-else>
        <div class="flex flex-col gap-2">
          <div v-for="restaurant in previewRestaurantList" :key="restaurant.storeId" class="w-full">
            <div class="flex items-center">
              <div class="fill-surface-600 mr-2">
                <svg
                  width="1rem"
                  height="1rem"
                  viewBox="0 0 50 50"
                  version="1.2"
                  baseProfile="tiny"
                  xmlns="http://www.w3.org/2000/svg"
                  overflow="inherit"
                >
                  <path
                    d="M22 1.932v11.068h-2v-11c0-.552-.448-1-1-1s-1 .448-1 1v11h-2v-11.036c0-1.287-2-1.243-2-.033v11.069h-2v-10.99c0-1.363-2-1.313-2-.054v14.472c0 2.087 2 3.463 4 3.463v26.109c0 4 6 4 6 0v-26.108c2 0 4-1.662 4-3.227v-14.701c0-1.275-2-1.226-2-.032zm9 3.068v25h2v16c0 4 7 4 7 0v-41c0-5-9-5-9 0z"
                  />
                </svg>
              </div>
              <span class="text-[15px] font-semibold text-surface-700">{{ restaurant.name }}</span>
            </div>
            <div class="flex flex-col">
              <span class="text-sm text-surface-500">{{ restaurant.jibun }}</span>
              <p v-if="restaurant.tel" class="flex gap-2 text-sm text-surface-500 items-center">
                <a :href="`tel:${restaurant.tel}`" class="">{{ restaurant.tel }}</a>
              </p>
              <span class="text-sm text-surface-500">{{ restaurant.distance }}m</span>
            </div>
          </div>
          <Button
            @click="openAroundAptInfoModal('restaurant')"
            v-if="3 < restaurantList.length"
            size="small"
            outlined
            >음식점 전체 보기</Button
          >
        </div>
      </div>
    </div>
    <AroundInfoSkeleton v-if="isBusStopLoading" />
    <div>
      <div class="flex items-center gap-4 mb-2">
        <h4 class="text-lg font-bold">주변 버스정류장</h4>
        <span
          :class="
            twMerge(busStopList?.length === 0 ? 'text-surface-400' : 'text-primary', 'font-bold')
          "
          >{{ busStopList?.length }}</span
        >
      </div>
      <div v-if="busStopList.length === 0" class="flex items-center justify-center">
        <span class="text-sm text-surface-500">주변 버스정류장 정보가 없습니다</span>
      </div>
      <div v-else>
        <div class="flex flex-col gap-2">
          <div v-for="busStop in previewBusStopList" :key="busStop.busId" class="w-full">
            <div class="flex items-center">
              <div class="fill-surface-600 mr-2">
                <svg
                  width="1rem"
                  height="1rem"
                  viewBox="0 0 50 50"
                  version="1.2"
                  baseProfile="tiny"
                  xmlns="http://www.w3.org/2000/svg"
                  overflow="inherit"
                >
                  <path
                    d="M41.162 25h-32.02c-.933 0-1.701-.802-1.701-1.714 0-.152.028-.324.059-.462l1.704-11.899c.145-.773.841-.925 1.674-.925h28.553c.827 0 1.529.139 1.672.909l1.704 12.116c.026.141.06.224.06.376-.001.912-.773 1.599-1.705 1.599zm-1.281 13.345c-1.803 0-3.265-1.419-3.265-3.188 0-1.757 1.462-3.174 3.265-3.174 1.791 0 3.256 1.417 3.256 3.174 0 1.769-1.465 3.188-3.256 3.188zm-29.501 0c-1.79 0-3.253-1.419-3.253-3.188 0-1.757 1.463-3.174 3.253-3.174 1.808 0 3.268 1.417 3.268 3.174 0 1.769-1.46 3.188-3.268 3.188zm5.62-35.345h20v3h-20c-2 0-2-3 0-3zm28.202 4.59c-.584-2.813-2.29-3.946-5.073-5.078-2.778-1.128-9.216-2.48-14.058-2.48-4.863 0-11.334 1.353-14.115 2.48-2.782 1.133-4.46 2.265-5.039 5.078l-1.917 15.659v21.751h3v2c0 4 5 4 5 0v-2h25v2c0 4 6 4 6 0v-2h3v-21.751l-1.798-15.659z"
                  />
                </svg>
              </div>
              <span class="text-[15px] font-semibold text-surface-700"
                >{{ busStop.name }}({{ busStop.busId }})</span
              >
            </div>
            <div>
              <span class="text-sm text-surface-500">{{ busStop.distance }}m</span>
            </div>
          </div>
          <Button
            @click="openAroundAptInfoModal('bus')"
            v-if="3 < busStopList.length"
            size="small"
            outlined
            >버스정류장 전체 보기</Button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
