<template>
  <div class="back-button-container">
    <router-link to="/mobile/usercenter" class="back-button">
      <span class="arrow">←</span> 返回上一页
    </router-link>
  </div>

  <div class="profile-page">
    <h2>👤 个人信息</h2>
    <form @submit.prevent="updateUserInfo">
      <div class="form-group">
        <label for="name">姓名:</label>
        <input v-model="name" type="text" id="name" :readonly="!isEditing" placeholder="请输入姓名" />
      </div>
      <div class="form-group">
        <label for="nickname">昵称:</label>
        <input v-model="nickname" type="text" id="nickname" :readonly="!isEditing" placeholder="请输入昵称" />
      </div>
      <div class="form-group">
        <label for="gender">性别:</label>
        <select v-model="gender" id="gender" :disabled="!isEditing">
          <option value="">请选择性别</option>
          <option value="male">男</option>
          <option value="female">女</option>
          <option value="other">其他</option>
        </select>
      </div>
      <div class="form-group">
        <label for="contact">联系方式:</label>
        <input v-model="contact" type="tel" id="contact" :readonly="!isEditing" placeholder="请输入联系方式" />
      </div>

      <div class="buttons">
        <button v-if="isEditing" type="submit" class="save-button">保存</button>
        <button v-if="isEditing" type="button" @click="cancelEditing" class="cancel-button">取消</button>
        <button v-else @click="startEditing" class="edit-button">编辑</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const user = ref(null)
const isEditing = ref(false)
const name = ref('')
const nickname = ref('')
const gender = ref('')
const contact = ref('')

// 获取用户信息
const fetchUserInfo = async () => {
  try {
    const response = await axios.get('/api/user/info')
    const data = response.data
    name.value = data.name || ''
    nickname.value = data.nickname || ''
    gender.value = data.gender || ''
    contact.value = data.contact || ''
  } catch (error) {
    console.error('获取用户信息失败:', error)
  }
}

const startEditing = () => {
  isEditing.value = true
}

const cancelEditing = () => {
  isEditing.value = false
  fetchUserInfo()
}

const updateUserInfo = async () => {
  try {
    await axios.put('/api/user/update', {
      name: name.value,
      nickname: nickname.value,
      gender: gender.value,
      contact: contact.value
    })
    isEditing.value = false
    alert('更新成功！')
    fetchUserInfo()
  } catch (error) {
    console.error('更新用户信息失败:', error)
    alert('更新失败，请稍后再试')
  }
}

onMounted(() => {
  fetchUserInfo()
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
