import { useMainMapStore } from '@/stores/mainMap';
import { watch } from 'vue';

export const useAreaAvgOverlay = () => {
  const mainMapStore = useMainMapStore();

  const overlayList = [];

  const renderOverlay = () => {
    if (!mainMapStore.areaAvgList) return;
    mainMapStore.areaAvgList.forEach((area) => {
      const address = `${area.sido} ${area.gugun} ${area.dong}`;

      const name = (() => {
        if (area.level === 1) {
          return area.sido;
        } else if (area.level === 2) {
          area.gugun;
        } else if (area.level === 3) {
          return area.dong;
        } else {
          return '';
        }
      })();

      const price = (() => {
        const { amount } = area;
        if (amount < 1000) {
          return amount;
        } else if (amount < 10000 && amount % 1000 == 0) {
          return amount / 1000 + '천';
        } else if (amount < 10000) {
          return (amount / 1000).toFixed(1) + '천';
        } else if (amount % 10000 == 0) {
          return amount / 10000 + '억';
        } else {
          return (amount / 10000).toFixed(1) + '억';
        }
      })();

      const onClickOverlay = () => {};

      const position = new window.kakao.maps.LatLng(area.latitude, area.longitude);
      const content = document.createElement('div');
      content.innerHTML = `
  <div class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform">
    <div class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-9 border rounded -left-[70%]">
      <span class="text-xs font-bold text-surface-700">${address}</span>
    </div>
    <button class="flex flex-col items-center justify-center bg-primary p-2 rounded">
      <span class="text-[10px] text-primary-inverse opacity-90">${name}</span>
      <span class="text-primary-inverse text-[11px] font-bold">${price}</span>
    </button>
  </div>
`;

      const button = content.querySelector('button');
      button.addEventListener('click', () => {
        onClickOverlay();
      });

      const noPriceContent = document.createElement('div');
      noPriceContent.innerHTML = `
      <div class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform">
        <div class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-9 border rounded -left-[70%]">
          <span class="text-xs font-bold text-surface-700">${address}</span>
        </div>
        <button class="flex flex-col items-center justify-center rounded bg-surface-600 p-2">
          <span class="text-[10px] text-primary-inverse opacity-90">${name}</span>
        </button>
      </div>
    `;
      noPriceContent.querySelector('button').addEventListener('click', () => {
        onClickOverlay();
      });

      // 커스텀 오버레이를 생성합니다
      const customOverlay = new window.kakao.maps.CustomOverlay({
        position,
        content: area.amount === 0 ? noPriceContent : content,
        yAnchor: 1
      });

      overlayList.push(customOverlay);
      customOverlay.setMap(mainMapStore.map);
    });
  };

  watch(
    () => mainMapStore.areaAvgList,
    () => {
      if (mainMapStore.areaAvgList) {
        overlayList.forEach((overlay) => {
          overlay.setMap(null);
        });
        renderOverlay();
      } else {
        overlayList.forEach((overlay) => {
          overlay.setMap(null);
        });
      }
    }
  );
};
