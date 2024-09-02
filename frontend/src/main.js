import './assets/css/main.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';
import { useKakao } from 'vue3-kakao-maps';
import VueApexCharts from 'vue3-apexcharts';

import PrimeVue from 'primevue/config';
import ToastService from 'primevue/toastservice';
import DialogService from 'primevue/dialogservice';
import ConfirmationService from 'primevue/confirmationservice';

import Tooltip from 'primevue/tooltip';
import BadgeDirective from 'primevue/badgedirective';
import StyleClass from 'primevue/styleclass';
import FocusTrap from 'primevue/focustrap';
import AnimateOnScroll from 'primevue/animateonscroll';

import Aura from '@/presets/aura';
import App from './App.vue';
import router from './router';
import { VueQueryPlugin } from '@tanstack/vue-query';

useKakao(import.meta.env.VITE_KAKAO_API_KEY, ['clusterer', 'services', 'drawing']);

const app = createApp(App);
app.use(createPinia());
app.use(VueApexCharts);
app.use(router);
app.use(VueQueryPlugin);
app.use(PrimeVue, {
  unstyled: true,
  pt: Aura
});

app.use(ToastService);
app.use(DialogService);
app.use(ConfirmationService);

app.directive('tooltip', Tooltip);
app.directive('badge', BadgeDirective);
app.directive('styleclass', StyleClass);
app.directive('focustrap', FocusTrap);
app.directive('animateonscroll', AnimateOnScroll);

app.mount('#app');
