import axios from 'axios';
import { HTTP_METHOD } from './myAxios';
export const searchAddress = (params) => {
  return axios({
    url: 'https://www.juso.go.kr/addrlink/addrLinkApi.do',
    method: HTTP_METHOD.GET,
    params: {
      confmKey: import.meta.env.VITE_JUSO_API_KEY,
      resultType: 'json',
      ...params
    }
  });
};
