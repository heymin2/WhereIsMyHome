import { createRouter, createWebHistory } from 'vue-router';

import MainView from '@/views/main/MainView.vue';
import SearchPanel from '@/components/main/SearchPanel.vue';
import MyMenu from '@/components/main/MyMenu.vue';
import AptDetail from '@/components/main/AptDetail.vue';
import ZzimAptList from '@/components/main/ZzimAptList.vue';
import ZzimItemList from '@/components/main/ZzimItemList.vue';
import MyItemList from '@/components/main/MyItemList.vue';
import ItemDetail from '@/components/main/ItemDetail.vue';
import { useUserStore } from '@/stores/user';
import NoticeList from '@/components/main/notice/NoticeList.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
      children: [
        {
          path: '/',
          name: 'SearchPanel',
          component: SearchPanel
        },
        {
          path: '/my',
          name: 'MyMenu',
          component: MyMenu,
          beforeEnter: (to, from, next) => {
            const userStore = useUserStore();
            if (userStore.isLogin) {
              return next();
            } else {
              return next('/');
            }
          }
        },
        {
          path: '/apt/:aptId',
          name: 'AptDetail',
          component: AptDetail
        },
        {
          path: '/zzim-apt',
          name: 'ZzimApt',
          component: ZzimAptList,
          beforeEnter: (to, from, next) => {
            const userStore = useUserStore();
            if (userStore.isLogin) {
              return next();
            } else {
              return next('/');
            }
          }
        },
        {
          path: '/myitem',
          name: 'MyItem',
          component: MyItemList,
          beforeEnter: (to, from, next) => {
            const userStore = useUserStore();

            if (userStore.isLogin) {
              return next();
            } else {
              return next('/');
            }
          }
        },
        {
          path: '/item/:itemId',
          name: 'ItemDetail',
          component: ItemDetail
        },
        {
          path: '/zzim-item',
          name: 'ZzimItem',
          component: ZzimItemList,
          beforeEnter: (to, from, next) => {
            const userStore = useUserStore();

            if (userStore.isLogin) {
              return next();
            } else {
              return next('/');
            }
          }
        },
        {
          path: '/notice',
          name: 'Notice',
          component: NoticeList
        }
      ]
    }
  ]
});

export default router;
