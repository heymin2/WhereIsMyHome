import { useMainMapStore } from '@/stores/mainMap';
import { useHouseStore } from '@/stores/house';
import { getAptDealList, getAptDetail } from '@/api/house';
import { watch } from 'vue';

export const useAptOverlay = () => {
  const mainMapStore = useMainMapStore();
  const houseStore = useHouseStore();
  const { setSelectedHouseId, setSelectedHouse, setDealList } = houseStore;

  const overlayList = [];

  const renderOverlay = () => {
    if (!mainMapStore.aptList) return;
    mainMapStore.aptList.forEach((apt, index) => {
      const size = Number.isInteger(apt.size) ? apt.size : apt.size.toFixed(1);

      const address = `${apt.sido} ${apt.gugun} ${apt.dong} ${apt.jibun}`;

      const price = (() => {
        const { dealAmount } = apt;
        if (dealAmount < 1000) {
          return dealAmount;
        } else if (dealAmount < 10000 && dealAmount % 1000 == 0) {
          return dealAmount / 1000 + '천';
        } else if (dealAmount < 10000) {
          return (dealAmount / 1000).toFixed(1) + '천';
        } else if (dealAmount % 10000 == 0) {
          return dealAmount / 10000 + '억';
        } else {
          return (dealAmount / 10000).toFixed(1) + '억';
        }
      })();

      const onClickKakaoMapMarker = () => {
        if (houseStore.selectedHouseId == apt.aptId) return;

        setSelectedHouseId(apt.aptId);

        getAptDetail(apt.aptId).then((res) => {
          setSelectedHouse(res.data);
          console.log(houseStore.selectedHouse);
        });

        getAptDealList(apt.aptId).then((res) => {
          setDealList(res.data);
        });
      };

      const position = new window.kakao.maps.LatLng(apt.latitude, apt.longitude);
      const content = document.createElement('div');
      content.innerHTML = `
  <div class="group flex flex-col relative items-center hover:-translate-y-1 transition-transform">
    <div class="hidden group-hover:flex flex-col px-4 py-2 bg-white absolute -top-14 border rounded -left-[100%]">
      <span class="text-sm font-bold text-surface-700">${apt.apartmentName}</span>
      <span class="text-xs text-surface-500">${address}</span>  
    </div>
    <button class="flex flex-col items-center justify-center w-12 h-12 bg-primary">
      <span class="text-xs text-primary-inverse opacity-90">${size}m<sup>2</sup></span>
      <span class="text-primary-inverse text-sm font-bold">${price}</span>
    </button>
    <div class=" border-r-8 border-l-8 border-t-8 border-t-primary border-r-transparent border-l-transparent"></div>
  </div>
`;

      const button = content.querySelector('button');
      button.addEventListener('click', () => {
        onClickKakaoMapMarker();
      });

      // 커스텀 오버레이를 생성합니다
      const customOverlay = new window.kakao.maps.CustomOverlay({
        position,
        content,
        yAnchor: 1
      });

      overlayList.push(customOverlay);
      customOverlay.setMap(mainMapStore.map);
    });
  };

  watch(
    () => mainMapStore.aptList,
    () => {
      if (mainMapStore.aptList) {
        renderOverlay();
      } else {
        overlayList.forEach((overlay) => {
          overlay.setMap(null);
        });
      }
    }
  );
};
