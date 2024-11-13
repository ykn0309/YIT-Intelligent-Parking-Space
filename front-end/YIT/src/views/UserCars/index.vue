<template>
   <div class="back-button-container">
    <router-link to="/mobile/usercenter" class="back-button">
      <span class="arrow">←</span> 返回上一页
    </router-link>
  </div>
  <div class="my-vehicles-page">
    <h2>🚗 我的车辆</h2>
    <!-- 如果 vehicles 列表为空，则显示暂无车辆 -->
    <p v-if="!loading && vehicles.length === 0" class="no-vehicle">暂无车辆</p>
    <!-- 如果有车辆，则显示车牌号列表 -->
    <ul v-if="!loading && vehicles.length > 0">
      <li v-for="vehicle in vehicles" :key="vehicle.id">
        <p><strong>车牌号：</strong>{{ vehicle.licensePlate }}</p>
      </li>
    </ul>
    <!-- 加载状态 -->
    <p v-if="loading" class="loading">加载中...</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const vehicles = ref([]) // 初始化为空数组
const loading = ref(true) // 添加加载状态

// 获取车辆数据的函数
const fetchVehicles = async () => {
  try {
    const response = await axios.get('/api/user/vehicles')
    vehicles.value = Array.isArray(response.data) ? response.data : []
  } catch (error) {
    console.error('获取车辆数据失败:', error)
    vehicles.value = []
  } finally {
    loading.value = false // 请求完成后，无论成功或失败都设置为 false
  }
}

// 组件挂载时调用 API 获取数据
onMounted(() => {
  fetchVehicles()
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

body {
  font-family: 'Roboto', sans-serif;
  background-color: #f5f7fa;
}

.my-vehicles-page {
  padding: 20px;
  max-width: 600px;
  margin: 40px auto;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  font-size: 28px;
  font-weight: 700;
  margin-bottom: 20px;
  color: #333;
}

.no-vehicle {
  font-size: 20px;
  color: #777;
  margin-top: 10px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 15px;
  padding: 10px;
  border-bottom: 1px solid #e0e0e0;
  transition: background-color 0.3s;
}

li:hover {
  background-color: #f0f5ff;
}

p {
  margin: 5px 0;
  font-size: 18px;
  color: #555;
}

strong {
  font-weight: 500;
  color: #1a73e8;
}

.loading {
  font-size: 20px;
  font-style: italic;
  color: #888;
}

.back-button-container {
  margin: 20px 0;
  display: flex;
  justify-content: flex-start; /* 左对齐 */
}

.back-button {
  display: inline-flex;
  align-items: center;
  color: #333;
  text-decoration: none;
  font-size: 18px;
  font-weight: 600;
  padding: 5px 10px;
  transition: transform 0.2s ease, color 0.2s ease;
  position: relative;
}

.back-button::before {
  content: '';
  position: absolute;
  left: 0;
  bottom: -4px;
  width: 100%;
  height: 2px;
  background-color: #333;
  transform: scaleX(0);
  transform-origin: left;
  transition: transform 0.3s ease;
}

.back-button:hover::before {
  transform: scaleX(1);
}

.back-button:hover {
  color: #555;
  transform: translateX(-5px);
}

.arrow {
  font-size: 20px;
  margin-right: 8px;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2); /* 立体效果 */
}
</style>
