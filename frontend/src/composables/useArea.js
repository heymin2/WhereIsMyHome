import { getSidoList } from '@/api/area';
import { useQuery } from '@tanstack/vue-query';

export const useArea = () => {
  const sidoQuery = useQuery({
    queryKey: ['sido'],
    queryFn: getSidoList,
    refetchOnMount: false,
    refetchOnReconnect: false,
    refetchOnWindowFocus: false
  });

  return { sidoQuery };
};
