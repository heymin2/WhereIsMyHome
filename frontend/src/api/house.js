import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'house';

export const getAptList = (params) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.POST,
    data: params
  });
};

export const getAvgDealAmount = (params) => {
  return myAxios({
    url: `${PREFIX}/area`,
    method: HTTP_METHOD.POST,
    data: params
  });
};

export const getAptDetail = (aptId) => {
  return myAxios({
    url: `area/apt/${aptId}`,
    method: HTTP_METHOD.GET
  });
};

export const getCafeList = (params) => {
  return myAxios({
    url: `${PREFIX}/cafe`,
    method: HTTP_METHOD.POST,
    data: params
  });
};

export const getRestaurantList = (params) => {
  return myAxios({
    url: `${PREFIX}/food`,
    method: HTTP_METHOD.POST,
    data: params
  });
};

export const getBusStopList = (params) => {
  return myAxios({
    url: `${PREFIX}/bus`,
    method: HTTP_METHOD.POST,
    data: params
  });
};

export const getAptDealList = (aptId) => {
  return myAxios({
    url: `area/deal/${aptId}`,
    method: HTTP_METHOD.GET
  });
};

export const getAroundCafeList = (aptId) => {
  return myAxios({
    url: `area/apt/cafe/${aptId}`,
    method: HTTP_METHOD.GET
  });
};

export const getAroundRestaurantList = (aptId) => {
  return myAxios({
    url: `area/apt/food/${aptId}`,
    method: HTTP_METHOD.GET
  });
};

export const getAroundBusStopList = (aptId) => {
  return myAxios({
    url: `area/apt/bus/${aptId}`,
    method: HTTP_METHOD.GET
  });
};
