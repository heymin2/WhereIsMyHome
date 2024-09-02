/*
 매물관련 API
 */

import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'item';

export const createItem = (data) => {
  return myAxios({
    url: `${PREFIX}`,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    method: HTTP_METHOD.POST,
    data
  });
};

// 나의 매물 리스트 조회
export const getMyItemList = () => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.GET
  });
};

export const getItemDetail = (itemId) => {
  return myAxios({
    url: `${PREFIX}/place/${itemId}`,
    method: HTTP_METHOD.GET
  });
};

export const deleteItem = (itemId) => {
  return myAxios({
    url: `${PREFIX}/${itemId}`,
    method: HTTP_METHOD.DELETE
  });
};

export const soldItem = (itemId) => {
  return myAxios({
    url: `${PREFIX}/sold/${itemId}`,
    method: HTTP_METHOD.PUT
  });
};
export const getItemList = (params) => {
  return myAxios({
    url: `${PREFIX}/place`,
    method: HTTP_METHOD.POST,
    data: { ...params }
  });
};

export const updateItem = (data) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.PUT,
    data
  });
};

export const changeSoldStatus = (itemId) => {
  return myAxios({
    url: `${PREFIX}/sold/${itemId}`,
    method: HTTP_METHOD.PUT
  });
};
