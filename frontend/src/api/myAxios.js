import axios from 'axios';

export const HTTP_METHOD = {
  GET: 'GET',
  POST: 'POST',
  PUT: 'PUT',
  PATCH: 'PATCH',
  DELETE: 'DELETE'
};

export const myAxios = axios.create({
  baseURL: import.meta.env.VITE_API_ENDPOINT,
  timeout: 30000,
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Credentials': true,
    'ngrok-skip-browser-warning': true
  },
  withCredentials: true
});

myAxios.interceptors.request.use(
  (config) => ({
    ...config
  }),
  (error) => {
    alert(error.message);
    return Promise.reject(error);
  }
);

myAxios.interceptors.response.use(
  (res) => {
    if (import.meta.env.DEV) {
      console.log(res);
    }
    return res;
  },
  async (error) => {
    if (import.meta.env.DEV) {
      console.error(error);
    }

    return Promise.reject(error.response.data);
  }
);
