import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useMainMapStore = defineStore('mainMap', () => {
  // 카카오 지도 객체
  const map = ref(null);

  // 지도의 중심 좌표
  const coordinate = ref({
    lat: 36.1025425,
    lng: 128.423445
  });

  const isLoading = ref(false);

  const setIsLoading = (loading) => {
    isLoading.value = loading;
  };

  // 현재 위치 좌표
  const currentCoordinate = ref(null);

  const aptList = ref([]);

  const areaAvgList = ref([]);

  const itemList = ref([]);

  const isCafeVisible = ref(false);
  const isRestaurantVisible = ref(false);
  const isBusStopVisible = ref(false);

  const setIsCafeVisible = (visible) => {
    isCafeVisible.value = visible;
  };

  const setIsRestaurantVisible = (visible) => {
    isRestaurantVisible.value = visible;
  };

  const setIsBusStopVisible = (visible) => {
    isBusStopVisible.value = visible;
  };

  const cafeList = ref([]);
  const restaurantList = ref([]);
  const busStopList = ref([]);

  const setCafeList = (list) => {
    cafeList.value = list;
  };

  const setRestaurantList = (list) => {
    restaurantList.value = list;
  };

  const setBusStopList = (list) => {
    busStopList.value = list;
  };

  const setItemList = (list) => {
    itemList.value = list;
  };

  // 카카오 지도 객체를 저장
  const setMap = (newMap) => {
    console.log(newMap);
    map.value = newMap;
    11;
  };

  const setCoordinate = (lat, lng) => {
    console.log('setCoordinate 호출 : ', lat, lng);
    coordinate.value = { lat, lng };
  };

  const setAptList = (list) => {
    aptList.value = list;
  };

  const setAreaAvgList = (list) => {
    areaAvgList.value = list;
  };

  const setCurrentCoordinate = (lat, lng) => {
    currentCoordinate.value = { lat, lng };
  };

  const setZoomLevel = (level) => {
    if (!map.value) return;
    map.value.setLevel(level);
  };

  const zoomToDong = () => {
    setZoomLevel(3);
  };

  const zoomIn = () => {
    setZoomLevel(map.value.getLevel() - 1);
  };

  const zoomOut = () => {
    setZoomLevel(map.value.getLevel() + 1);
  };

  return {
    coordinate,
    map,
    currentCoordinate,
    aptList,
    areaAvgList,
    cafeList,
    restaurantList,
    busStopList,
    isCafeVisible,
    isRestaurantVisible,
    isBusStopVisible,
    isLoading,
    itemList,
    setItemList,
    setIsLoading,
    setIsCafeVisible,
    setIsRestaurantVisible,
    setIsBusStopVisible,
    setCafeList,
    setRestaurantList,
    setBusStopList,
    setAptList,
    setAreaAvgList,
    setCoordinate,
    setZoomLevel,
    setCurrentCoordinate,
    zoomToDong,
    setMap,
    zoomIn,
    zoomOut
  };
});
