<template>
  <div class="back-button-container">
    <router-link to="/mobile/usercenter" class="back-button">
      <span class="arrow">←</span> 返回上一页
    </router-link>
  </div>

  <div class="profile-page">
    <h2>👤 用户信息</h2>
    <form @submit.prevent="updateUserInfo">
      <!-- 用户名 -->
      <div class="form-group">
        <label for="username">用户名:</label>
        <input v-model="userName" type="text" id="username" :readonly="!isEditing" placeholder="请输入用户名" />
      </div>
      
      <!-- 车牌号 -->
      <div class="form-group">
        <label for="licensePlate">车牌号:</label>
        <input v-model="carId" type="text" id="licensePlate" :readonly="!isEditing" placeholder="请输入车牌号" />
      </div>

      <div class="buttons">
        <!-- 保存和取消按钮，只有在编辑状态时显示 -->
        <button v-if="isEditing" type="submit" class="save-button">保存</button>
        <button v-if="isEditing" type="button" @click="cancelEditing" class="cancel-button">取消</button>

        <!-- 编辑按钮，只有在非编辑状态时显示 -->
        <button v-else @click="startEditing" class="edit-button">编辑</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// 用户信息数据
const userName = ref('')  // 用户名
const carId = ref('')     // 车牌号
const isEditing = ref(false) // 是否处于编辑状态

// 模拟的用户 ID，通常应该从登录状态中获取
const userId = 1  // 这里用一个固定的 ID，实际情况可以从登录信息获取

// 获取用户信息

const fetchUserInfo = async () => {
  try {
    // 向后端发送请求，通过查询参数传递 userId
    const response = await axios.get(`http://localhost:8080/userInfo`, {
      params: { userId: userId }
    });

    const data = response.data.data;

    console.log(data)

    // 更新前端显示的用户信息
    userName.value = data.userName || '';
    carId.value = data.carId || '';
  } catch (error) {
    console.error('获取用户信息失败:', error);
    // 如果请求失败，清空数据
    userName.value = '';
    carId.value = '';
  }
}

// 开始编辑
const startEditing = () => {
  isEditing.value = true
}

// 取消编辑
const cancelEditing = () => {
  isEditing.value = false
  fetchUserInfo()  // 恢复原始数据
}

// 更新用户信息
const updateUserInfo = async () => {
  try {
    await axios.put(`${BASE_URL}/api/userInfo`, {
      userId: userId,  // 使用模拟的 userId
      userName: userName.value,
      carId: carId.value
    })
    isEditing.value = false
    alert('更新成功！')
    fetchUserInfo()  // 更新数据
  } catch (error) {
    console.error('更新用户信息失败:', error)
    alert('更新失败，请稍后再试')
  }
}

// 页面加载时获取用户信息
onMounted(() => {
  fetchUserInfo()  // 页面加载时模拟获取用户信息
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

body {
  font-family: 'Roboto', sans-serif;
}

.profile-page {
  padding: 20px;
  max-width: 500px;
  margin: 50px auto;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  font-size: 28px;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

label {
  display: block;
  font-weight: 500;
  margin-bottom: 8px;
}

input,
select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

input[readonly],
select[disabled] {
  background-color: #f5f5f5;
}

.buttons {
  display: flex;
  gap: 15px;
  justify-content: center;
}

.save-button {
  background-color: #1a73e8;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.save-button:hover {
  background-color: #145bb5;
}

.edit-button {
  background-color: #4caf50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.edit-button:hover {
  background-color: #388e3c;
}

.cancel-button {
  background-color: #f44336;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
}

.cancel-button:hover {
  background-color: #e53935;
}

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
</style>
