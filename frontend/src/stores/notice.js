import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useNoticeStore = defineStore('notice', () => {
  const notice = ref(null);

  const setNotice = (v) => {
    notice.value = v;
  };

  return { notice, setNotice };
});
