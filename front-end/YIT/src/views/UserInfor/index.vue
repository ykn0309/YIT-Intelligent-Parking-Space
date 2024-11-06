<template>
  <div class="profile">
    <div class="profile-header">
      <h2>个人信息</h2>
    </div>
    <div class="profile-body">
      <p><strong>姓名：</strong>{{ name }}</p>
      <p><strong>年龄：</strong>{{ age }}</p>
      <p><strong>邮箱：</strong>{{ email }}</p>
    </div>
  </div>
  
  <div>
    <router-link to="/mobile/usercenter">返回上一页</router-link>
  </div>


</template>

<script lang="ts" setup>
import { ref } from 'vue';

// 个人信息
const name = ref<string>('张三');
const age = ref<number>(25);
const email = ref<string>('zhangsan@example.com');
</script>

<style scoped>
.profile {
  width: 300px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.profile-header h2 {
  text-align: center;
  margin-bottom: 20px;
}

.profile-body {
  text-align: center;
}

.profile-body p {
  margin: 8px 0;
  font-size: 16px;
}
</style>
