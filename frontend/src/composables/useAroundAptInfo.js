import { getAroundCafeList, getAroundRestaurantList, getAroundBusStopList } from '@/api/house';
import { useQuery } from '@tanstack/vue-query';
import { useHouseStore } from '@/stores/house';
import { h, onMounted, ref, watch } from 'vue';

export const useAroundCafe = () => {
  const houseStore = useHouseStore();

  const cafeList = ref([]);
  const isLoading = ref(false);

  watch(
    () => houseStore.selectedHouseId,
    (newVal) => {
      if (!newVal) return;
      isLoading.value = true;
      getAroundCafeList(houseStore.selectedHouseId).then((res) => {
        cafeList.value = res.data;
        isLoading.value = false;
      });
    }
  );

  onMounted(() => {
    if (!houseStore.selectedHouseId) return;
    isLoading.value = true;

    getAroundCafeList(houseStore.selectedHouseId).then((res) => {
      cafeList.value = res.data;
      isLoading.value = false;
    });
  });

  return { cafeList, isLoading };
};

export const useAroundRestaurant = () => {
  const houseStore = useHouseStore();

  const restaurantList = ref([]);
  const isLoading = ref(false);

  watch(
    () => houseStore.selectedHouseId,
    (newVal) => {
      if (!newVal) return;
      isLoading.value = true;
      getAroundRestaurantList(houseStore.selectedHouseId).then((res) => {
        restaurantList.value = res.data;
        isLoading.value = false;
      });
    }
  );

  onMounted(() => {
    if (!houseStore.selectedHouseId) return;
    isLoading.value = true;

    getAroundRestaurantList(houseStore.selectedHouseId).then((res) => {
      restaurantList.value = res.data;
      isLoading.value = false;
    });
  });

  return { restaurantList, isLoading };
};

export const useAroundBusStop = () => {
  const houseStore = useHouseStore();

  const busStopList = ref([]);
  const isLoading = ref(false);

  watch(
    () => houseStore.selectedHouseId,
    (newVal) => {
      if (!newVal) return;
      isLoading.value = true;
      getAroundBusStopList(houseStore.selectedHouseId).then((res) => {
        busStopList.value = res.data;
        isLoading.value = false;
      });
    }
  );

  onMounted(() => {
    if (!houseStore.selectedHouseId) return;
    isLoading.value = true;

    getAroundBusStopList(houseStore.selectedHouseId).then((res) => {
      busStopList.value = res.data;
      isLoading.value = false;
    });
  });

  return { busStopList, isLoading };
};
