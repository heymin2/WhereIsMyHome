import { HTTP_METHOD, myAxios } from './myAxios';

const PREFIX = 'auth';

export const login = (id, pw) => {
  return myAxios({
    url: `${PREFIX}/login`,
    method: HTTP_METHOD.POST,
    data: {
      id,
      pw
    }
  });
};

export const logout = () => {
  return myAxios({
    url: `${PREFIX}/logout`,
    method: HTTP_METHOD.POST
  });
};

export const fetchUserInfo = () => {
  return myAxios({
    url: `${PREFIX}`,
    method: HTTP_METHOD.GET
  });
};

/**
 * @param {Object} data
 * @param {string} data.name
 * @param {string} data.id
 *
 * @returns
 */
export const findPassword = (data) => {
  return myAxios({
    url: `${PREFIX}/findPw`,
    method: HTTP_METHOD.POST,
    data
  });
};
