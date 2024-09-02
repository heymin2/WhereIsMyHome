import dayjs from 'dayjs';

export const toYYYYMMDD = (date) => {
  return dayjs(date).format('YYYY.MM.DD');
};
