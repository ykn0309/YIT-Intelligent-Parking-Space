<template>
  <div class="back-button-container">
    <router-link to="/mobile/usercenter" class="back-button">
      <span class="arrow">←</span> 返回上一页
    </router-link>
  </div>
  <div class="wallet-page">
    <h2>💰 我的钱包</h2>
    <div class="balance-display">
      <p>当前余额：</p>
      <span>{{ balance.toFixed(2) }} 元</span>
    </div>
    <button @click="showRechargeModal" class="recharge-button">充值</button>

    <!-- 充值弹窗 -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal-content">
        <h3>输入充值金额</h3>
        <input v-model="rechargeAmount" type="number" placeholder="请输入金额" />
        <button @click="rechargeBalance">确认充值</button>
        <button @click="closeModal" class="cancel-button">取消</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const balance = ref(0)
const showModal = ref(false)
const rechargeAmount = ref('')

// 模拟的用户 ID，实际情况应该通过登录信息获取
const userId = 1

// 获取用户余额的函数
const fetchBalance = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/wallet`, {
      params: { userId: userId }
    });

    
    balance.value = response.data.data || 0
  } catch (error) {
    console.error('获取余额失败:', error)
    balance.value = 0
  }
}

// 充值弹窗控制
const showRechargeModal = () => {
  rechargeAmount.value = ''
  showModal.value = true
}

const closeModal = () => {
  showModal.value = false
}

// 充值功能
const rechargeBalance = async () => {
  // 检查金额是否合法
  if (!rechargeAmount.value || rechargeAmount.value <= 0) {
    alert('请输入有效的充值金额');
    return;
  }

  try {
    // 通过 GET 请求向后端发送充值请求
    const response = await axios.get(`http://localhost:8080/recharge`, {
      params: { userId: userId, wallet: rechargeAmount.value }  // 修改为 wallet
    });

    // 假设后端返回的响应包含一个状态字段
    if (response.data.status === 'success') {
      // 刷新余额
      await fetchBalance();
      alert('充值成功！');
    } else {
      alert('充值失败：' + response.data.message || '未知错误');
    }
    
    // 关闭弹窗
    closeModal();  

  } catch (error) {
    console.error('充值失败:', error);
    alert('充值失败，请稍后再试');
  }
}



// 页面加载时获取用户余额
onMounted(() => {
  fetchBalance()
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

body {
  font-family: 'Roboto', sans-serif;
}

.wallet-page {
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

.balance-display {
  font-size: 24px;
  margin-bottom: 30px;
}

.recharge-button {
  background-color: #1a73e8;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.recharge-button:hover {
  background-color: #145bb5;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 12px;
  text-align: center;
  width: 300px;
}

input {
  width: 80%;
  padding: 10px;
  margin-bottom: 20px;
}

.cancel-button {
  background-color: #f44336;
  color: #fff;
  padding: 10px;
  margin-top: 10px;
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
