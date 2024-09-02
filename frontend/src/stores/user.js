import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { fetchUserInfo, login as requestLogin } from '@/api/auth';

const INITIAL_STATE = {
  memberId: null,
  name: null,
  phone: null,
  email: null,
  createAt: null,
  isAdmin: null
};

export const useUserStore = defineStore('user', () => {
  const user = ref({
    ...INITIAL_STATE
  });
  const isLogin = computed(() => user.value.memberId !== null);

  const storeUser = (info) => {
    user.value = info;
    window.localStorage.setItem('user', JSON.stringify(info));
  };

  const loadUser = () => {
    const storedInfo = window.localStorage.getItem('user');
    if (!storedInfo) return;

    const info = JSON.parse(storedInfo);

    if (info?.memberId) {
      user.value = info;
    }
  };

  const clearUser = () => {
    user.value = {
      ...INITIAL_STATE
    };
    window.localStorage.removeItem('user');
  };

  const boot = async () => {
    loadUser();
    if (!user.value.memberId) return;
    try {
      const info = await fetchUserInfo();
      storeUser(info.data);
    } catch {
      clearUser();
      window.alert('로그인이 만료되었습니다.');
    }
  };

  const login = async (id, pw) => {
    try {
      const info = await requestLogin(id, pw);
      storeUser(info);
    } catch (err) {
      return err;
    }
  };

  return {
    user,
    isLogin,
    login,
    boot,
    storeUser,
    loadUser,
    clearUser
  };
});
