import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'member';

/**
 *
 * @param {Object} data
 * @param {string} data.name
 * @param {string} data.id
 * @param {string} data.pw
 * @param {string} data.phone
 * @param {string} data.email
 * @param {boolean} data.admin
 
 * @returns
 */
export const signUp = (data) => {
  return myAxios({
    url: `${PREFIX}/signUp`,
    method: HTTP_METHOD.POST,
    data
  });
};

/**
 * @param {Object} data
 * @param {string} data.name
 * @param {string} data.pw
 * @param {string} data.phone
 * @param {string} data.email
 *
 * @returns
 */
export const updateMember = (data) => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.PUT,
    data
  });
};

/**
 * @param {string} id
 *
 * @returns
 */
export const checkDuplicatedId = (id) => {
  return myAxios({
    url: `${PREFIX}/${id}`,
    method: HTTP_METHOD.GET
  });
};
