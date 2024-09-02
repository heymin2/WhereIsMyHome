import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'notice';

export const getNoticeList = (params) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.GET,
    params
  });
};

export const getNoticeDetail = (id) => {
  return myAxios({
    url: `${PREFIX}/${id}`,
    method: HTTP_METHOD.GET
  });
};

export const createNotice = (data) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.POST,
    data
  });
};

export const updateNotice = (data) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.PUT,
    data
  });
};

export const deleteNotice = (id) => {
  return myAxios({
    url: `${PREFIX}/${id}`,
    method: HTTP_METHOD.DELETE
  });
};
