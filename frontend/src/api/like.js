import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'like';

// 로그인 후 사용 가능
export const likeApt = (aptId) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.POST,
    data: {
      aptId
    }
  });
};

// 로그인 후 사용 가능
export const unlikeApt = (aptId) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.DELETE,
    data: {
      aptId
    }
  });
};

// 사용자의 관심 아파트 목록 조회, 로그인 후 사용 가능
export const getLikeList = () => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.GET
  });
};

export const getLikeItemList = () => {
  return myAxios({
    url: `${PREFIX}/item`,
    method: HTTP_METHOD.GET
  });
};

export const getLikeCount = (aptId) => {
  return myAxios({
    url: `${PREFIX}/${aptId}`,
    method: HTTP_METHOD.GET
  });
};

export const likeItem = (itemId) => {
  return myAxios({
    url: `${PREFIX}/item`,
    method: HTTP_METHOD.POST,
    data: {
      itemId
    }
  });
};

export const unlikeItem = (itemId) => {
  return myAxios({
    url: `${PREFIX}/item`,
    method: HTTP_METHOD.DELETE,
    data: {
      itemId
    }
  });
};

export const getItemLikeCount = (itemId) => {
  return myAxios({
    url: `${PREFIX}/item/${itemId}`,
    method: HTTP_METHOD.GET
  });
};
