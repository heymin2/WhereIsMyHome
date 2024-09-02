<script setup>
import { KakaoMap, KakaoMapCustomOverlay } from 'vue3-kakao-maps';
import AptOverlay from '@/components/main/AptOverlay.vue';
import AreaAvgOverlay from '@/components/main/AreaAvgOverlay.vue';
import CafeOverlay from '@/components/main/CafeOverlay.vue';
import RestaurantOverlay from '@/components/main/RestaurantOverlay.vue';
import BusStopOverlay from '@/components/main/BusStopOverlay.vue';
import ItemOverlay from '@/components/main/ItemOverlay.vue';

import { useMainMapStore } from '@/stores/mainMap';
import { useMainMap } from '@/composables/useMainMap';

const mainMapStore = useMainMapStore();
const { updateMap } = useMainMap();

// useAptOverlay();
// useAreaAvgOverlay();

const onLoadKakaoMap = (mapRef) => {
  // kakaomap의 ref를 저장
  mainMapStore.setMap(mapRef);
  updateMap();

  // 클릭 이벤트 등록
  //   window.kakao.maps.event.addListener(mainMapStore.map, 'click', function (mouseEvent) {
  //     // 클릭한 위도, 경도 정보를 가져옵니다
  //     const latlng = mouseEvent.latLng;
  //     console.log(latlng.getLat(), latlng.getLng());
  //   });

  // 요청 너무 많이 날아감
  // window.kakao.maps.event.addListener(mainMapStore.map, 'center_changed', function () {
  //   updateMap();
  // });

  window.kakao.maps.event.addListener(mainMapStore.map, 'zoom_changed', function () {
    updateMap();
  });

  window.kakao.maps.event.addListener(mainMapStore.map, 'dragend', function () {
    updateMap();
  });
};
</script>

<template>
  <div class="w-screen h-screen fixed z-[-1]">
    <KakaoMap
      width="100%"
      height="100%"
      :lat="mainMapStore.coordinate.lat"
      :lng="mainMapStore.coordinate.lng"
      :draggable="true"
      @onLoadKakaoMap="onLoadKakaoMap"
    >
      <KakaoMapCustomOverlay
        v-if="mainMapStore.currentCoordinate"
        :lat="mainMapStore.currentCoordinate.lat"
        :lng="mainMapStore.currentCoordinate.lng"
      >
        <div
          class="w-5 h-5 rounded-full drop-shadow-xl border bg-white animate-pulse flex items-center justify-center"
        >
          <div class="w-3 h-3 bg-red-500 rounded-full"></div>
        </div>
      </KakaoMapCustomOverlay>
      <ItemOverlay v-for="item in mainMapStore.itemList" :key="item.itemId" :item="item" />
      <AreaAvgOverlay v-for="area in mainMapStore.areaAvgList" :key="area.addressId" :area="area" />
      <AptOverlay v-for="apt in mainMapStore.aptList" :key="apt.aptId" :apt="apt" />

      <CafeOverlay v-for="cafe in mainMapStore.cafeList" :key="cafe.storeId" :cafe="cafe" />
      <RestaurantOverlay
        v-for="restaurant in mainMapStore.restaurantList"
        :key="restaurant.storeId"
        :restaurant="restaurant"
      />
      <BusStopOverlay
        v-for="busStop in mainMapStore.busStopList"
        :key="busStop.busId"
        :busStop="busStop"
      />
    </KakaoMap>
  </div>
</template>

<style scoped></style>
