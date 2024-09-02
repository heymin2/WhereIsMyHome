import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useHouseStore = defineStore('house', () => {
  const selectedHouseId = ref(null);
  const selectedHouse = ref(null);
  const likeCount = ref(0);
  const dealList = ref(null);

  const setLikeCount = (count) => {
    likeCount.value = count;
  };

  const setSelectedHouseId = (id) => {
    selectedHouseId.value = id;
  };

  const setSelectedHouse = (apt) => {
    selectedHouse.value = apt;
  };

  const setDealList = (list) => {
    dealList.value = list;
  };

  return {
    selectedHouseId,
    selectedHouse,
    dealList,
    likeCount,
    setSelectedHouseId,
    setSelectedHouse,
    setDealList,
    setLikeCount
  };
});
