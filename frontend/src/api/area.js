/**
 * 지역 정보를 조회하는 API
 */

import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'area';

/**
 *
 * @returns {string[]}
 */
export const getSidoList = () => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.GET
  });
};

/**
 *
 * @returns {string[]}
 */
export const getGugunList = (sido) => {
  return myAxios({
    url: `${PREFIX}/${sido}`,
    method: HTTP_METHOD.GET
  });
};

/**
 *
 * @returns {string[]}
 */
export const getDongList = (sido, gugun) => {
  return myAxios({
    url: `${PREFIX}/${sido}/${gugun}`,
    method: HTTP_METHOD.GET
  });
};
