import {
  getAptList,
  getAvgDealAmount,
  getBusStopList,
  getCafeList,
  getRestaurantList
} from '@/api/house';
import { getItemList } from '@/api/item';
import { useMainMapStore } from '@/stores/mainMap';

export const useMainMap = () => {
  const mainMapStore = useMainMapStore();
  const {
    setAptList,
    setAreaAvgList,
    setCafeList,
    setRestaurantList,
    setBusStopList,
    setItemList
  } = mainMapStore;

  const updateMap = () => {
    mainMapStore.setIsLoading(true);
    if (!mainMapStore.map) return;
    const level = mainMapStore.map.getLevel();

    const startLongitude = mainMapStore.map.getBounds().getSouthWest().getLng();
    const endLongitude = mainMapStore.map.getBounds().getNorthEast().getLng();
    const startLatitude = mainMapStore.map.getBounds().getSouthWest().getLat();
    const endLatitude = mainMapStore.map.getBounds().getNorthEast().getLat();

    if (level <= 3) {
      setAreaAvgList(null);

      getAptList({
        startLongitude,
        endLongitude,
        startLatitude,
        endLatitude
      })
        .then((res) => {
          setAptList(res.data);
        })
        .finally(() => {
          mainMapStore.setIsLoading(false);
        });

      getItemList({
        startLongitude,
        endLongitude,
        startLatitude,
        endLatitude
      }).then((res) => {
        setItemList(res.data);
      });

      if (mainMapStore.isCafeVisible) {
        getCafeList({
          startLongitude,
          endLongitude,
          startLatitude,
          endLatitude
        }).then((res) => {
          setCafeList(res.data);
        });
      } else {
        setCafeList([]);
      }

      if (mainMapStore.isRestaurantVisible) {
        getRestaurantList({
          startLongitude,
          endLongitude,
          startLatitude,
          endLatitude
        }).then((res) => {
          setRestaurantList(res.data);
        });
      } else {
        setRestaurantList([]);
      }

      if (mainMapStore.isBusStopVisible) {
        getBusStopList({
          startLongitude,
          endLongitude,
          startLatitude,
          endLatitude
        }).then((res) => {
          setBusStopList(res.data);
        });
      } else {
        setBusStopList([]);
      }
    } else if (level <= 5) {
      setAptList(null);
      setCafeList(null);
      setRestaurantList(null);
      setBusStopList(null);
      setItemList(null);
      getAvgDealAmount({
        level: 3,
        startLongitude,
        endLongitude,
        startLatitude,
        endLatitude
      })
        .then((res) => {
          setAreaAvgList(res.data);
        })
        .finally(() => {
          mainMapStore.setIsLoading(false);
        });
    } else if (level <= 7) {
      setAptList(null);
      setCafeList(null);
      setRestaurantList(null);
      setBusStopList(null);
      setItemList(null);

      getAvgDealAmount({
        level: 2,
        startLongitude,
        endLongitude,
        startLatitude,
        endLatitude
      })
        .then((res) => {
          setAreaAvgList(res.data);
        })
        .finally(() => {
          mainMapStore.setIsLoading(false);
        });
    } else {
      setAptList(null);
      setCafeList(null);
      setRestaurantList(null);
      setBusStopList(null);
      setItemList(null);

      getAvgDealAmount({
        level: 1,
        startLongitude,
        endLongitude,
        startLatitude,
        endLatitude
      })
        .then((res) => {
          setAreaAvgList(res.data);
        })
        .finally(() => {
          mainMapStore.setIsLoading(false);
        });
    }
  };

  return { updateMap };
};
