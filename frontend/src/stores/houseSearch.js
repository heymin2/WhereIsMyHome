import { computed, ref } from 'vue';
import { defineStore } from 'pinia';

const INITIAL_PARAMS = {
  keyword: '',
  startLongitude: null,
  endLongitude: null,
  startLatitude: null,
  endLatitude: null,
  minPrice: 0,
  maxPrice: 200,
  minArea: null,
  maxArea: null,
  sido: null,
  gugun: null,
  dong: null
};

export const useHouseSearchStore = defineStore('houseSearch', () => {
  const params = ref({ ...INITIAL_PARAMS });

  const isFiltered = computed(() => {
    return (
      params.value.minPrice ||
      params.value.maxPrice ||
      params.value.minArea ||
      params.value.maxArea ||
      params.value.sido ||
      params.value.gugun ||
      params.value.dong
    );
  });

  const setKeyword = (keyword) => {
    params.value.keyword = keyword;
  };

  const setStartLongitude = (startLongitude) => {
    params.value.startLongitude = startLongitude;
  };

  const setEndLongitude = (endLongitude) => {
    params.value.endLongitude = endLongitude;
  };

  const setStartLatitude = (startLatitude) => {
    params.value.startLatitude = startLatitude;
  };

  const setEndLatitude = (endLatitude) => {
    params.value.endLatitude = endLatitude;
  };

  const setMinPrice = (minPrice) => {
    params.value.minPrice = minPrice;
  };

  const setMaxPrice = (maxPrice) => {
    params.value.maxPrice = maxPrice;
  };

  const setMinArea = (minArea) => {
    params.value.minArea = minArea;
  };

  const setMaxArea = (maxArea) => {
    params.value.maxArea = maxArea;
  };

  const clearFilters = () => {
    params.value.minPrice = null;
    params.value.maxPrice = null;
    params.value.minArea = null;
    params.value.maxArea = null;
    params.value.sido = null;
    params.value.gugun = null;
    params.value.dong = null;
  };

  const setSido = (sido) => {
    params.value.sido = sido;
  };

  const setGugun = (gugun) => {
    params.value.gugun = gugun;
  };

  const setDong = (dong) => {
    params.value.dong = dong;
  };

  const clickedFilter = ref(null);

  const onClickFilter = (filter) => {
    clickedFilter.value = filter;
  };

  return {
    params,
    isFiltered,
    clearFilters,
    clickedFilter,
    setKeyword,
    setStartLongitude,
    setEndLongitude,
    setStartLatitude,
    setEndLatitude,
    setMinPrice,
    setMaxPrice,
    setMinArea,
    setMaxArea,
    onClickFilter,
    setSido,
    setGugun,
    setDong
  };
});
