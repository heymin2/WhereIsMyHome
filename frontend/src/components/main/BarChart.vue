<script setup>
import { ref } from 'vue';
import { Bar, Line } from 'vue-chartjs';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const chart = ref({
  chartData: {
    labels: ['January', 'February', 'March'],
    datasets: [{ data: [40, 20, 12] }]
  },
  chartOptions: {
    responsive: true
  }
});
</script>

<template>
  <Line id="my-chart-id" :options="chart.chartOptions" :data="chart.chartData" />
</template>

<style scoped></style>
