<template>
  <!-- 返回按钮的容器 -->
  <div class="back-button-container">
    <router-link to="/mobile/usercenter" class="back-button">
      <span class="arrow">←</span> 返回上一页
    </router-link>
  </div>

  <!-- 我的车辆页面的容器 -->
  <div class="my-vehicle-page">
    <h2>🚗 我的车辆</h2>
  </div>

  <!-- 车牌号的显示，放在另一个 div 中 -->
  <div class="vehicle-info">
    <p><strong>车牌号：</strong>{{ licensePlate || '' }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// 定义响应数据
const licensePlate = ref('') // 存储车牌号，默认空字符串

// 获取用户ID
//const userId = sessionStorage.getItem('userId')
const userId = 1;

// 获取车辆数据的函数
const fetchVehicle = async () => {
  if (!userId) {
    console.error('用户ID未找到，请重新登录')
    licensePlate.value = ''
    return
  }

  try {
    // 发送 GET 请求，传递 userId 参数
    const response = await axios.get(`http://localhost:8080/vehicle`, {
      params: { userId }
    })
    
    // 如果后端返回车牌号，则赋值；否则为空
    licensePlate.value = response.data.data || ''
  } catch (error) {
    console.error('获取车辆数据失败:', error)
    licensePlate.value = '' // 如果失败，设置为空
  }
}

// 组件挂载时调用 API 获取数据
onMounted(() => {
  fetchVehicle()
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

body {
  font-family: 'Roboto', sans-serif;
  background-color: #f5f7fa;
}

/* 返回按钮样式 */
.back-button-container {
  margin: 20px 0;
  display: flex;
  justify-content: flex-start;
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
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
}

/* 我的车辆页面样式 */
.my-vehicle-page {
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

/* 车牌号显示区域的样式 */
.vehicle-info {
  padding: 20px;
  max-width: 600px;
  margin: 0 auto;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
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
</style>
