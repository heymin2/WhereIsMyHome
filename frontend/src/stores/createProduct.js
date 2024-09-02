import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useCreateProductStore = defineStore('createProduct', () => {
  const inputs = ref({
    type: null,
    address: null,
    detailAddress: null,
    title: null,
    description: null,
    salePrice: null,
    deposit: null,
    monthlyRent: null,
    rooms: null,
    bathrooms: null,
    size: null
  });
  const step = ref(0);

  const setType = (type) => {
    inputs.value.type = type;
    inputs.value.salePrice = null;
    inputs.value.deposit = null;
    inputs.value.monthlyRent = null;
  };

  const setStep = (value) => {
    step.value = value;
  };

  const completeStep1 = computed(() => {
    if (inputs.value.type === null) return false;
    if (inputs.value.type === '매매') {
      return inputs.value.salePrice !== null;
    } else if (inputs.value.type === '전세') {
      return inputs.value.deposit !== null;
    } else if (inputs.value.type === '월세') {
      return inputs.value.deposit !== null && inputs.value.monthlyRent !== null;
    }
    return false;
  });

  const completeStep2 = computed(() => {
    return inputs.value.address?.trim() && inputs.value.detailAddress?.trim();
  });

  const completeStep3 = computed(() => {
    return (
      inputs.value.title?.trim() &&
      inputs.value.description?.trim() &&
      0 <= inputs.value.rooms &&
      0 <= inputs.value.bathrooms &&
      0 <= Number(inputs.value.size)
    );
  });

  return { inputs, step, setStep, setType, completeStep1, completeStep2, completeStep3 };
});
