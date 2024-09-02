import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useItemStore = defineStore('item', () => {
  const selectedItemId = ref(null);
  const selectedItem = ref(null);
  const likeCount = ref(0);

  const setLikeCount = (count) => {
    likeCount.value = count;
  };

  const setSelectedItemId = (id) => {
    selectedItemId.value = id;
  };

  const setSelectedItem = (apt) => {
    selectedItem.value = apt;
  };

  return {
    selectedItemId,
    selectedItem,
    likeCount,
    setSelectedItem,
    setLikeCount,
    setSelectedItemId
  };
});
