<!-- App.vue -->
<template>
  <div id="app">
    <div class="tabs">
      <router-link to="/parking-management" class="tab-item">车位管理</router-link>
      <router-link to="/user-status" class="tab-item">用户状态</router-link>
      <router-link to="/mb/usercenter" class="tab-item">个人中心</router-link>
    </div>
    <div class="content">
      <!-- <router-view></router-view> -->
    </div>
  </div>
  <RouterView />
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* 微信小程序风格的底部导航栏 */
.tabs {
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: #ffffff;
  border-top: 1px solid #ebebeb;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0; 
  height: 50px; /* 根据实际需要调整高度 */
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.1);
}

/* 微信小程序风格的导航项 */
.tab-item {
  flex: 1;
  text-align: center;
  padding: 10px;
  font-size: 14px;
  color: #666666;
}

/* 当前激活的导航项样式 */
.tab-item.router-link-active {
  color: #1AAD19; /* 微信绿 */
  border-bottom: 2px solid #1AAD19;
}

/* 页面内容区域 */
.content {
  margin-bottom: 50px; /* 留出底部导航栏的高度 */
}
</style>