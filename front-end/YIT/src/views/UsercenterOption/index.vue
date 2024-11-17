<template>
 <div class="nav">
    <div class="nav-item">
      <router-link to="/mobile/usercenter/userinformation" class="menu-link">
        <div class="menu-content">
          <el-icon><UserFilled /></el-icon>
          <span>个人信息</span>
        </div>
      </router-link>
    </div>
    <div class="nav-item">
      <router-link to="/mobile/usercenter/userwallet" class="menu-link">
        <div class="menu-content">
          <el-icon><WalletFilled /></el-icon>
          <span>我的钱包</span>
        </div>
      </router-link>
    </div>
    <div class="nav-item">
      <router-link to="/mobile/usercenter/usercars" class="menu-link">
        <div class="menu-content">
          <el-icon><Van /></el-icon>
          <span>我的车辆</span>
        </div>
      </router-link>
    </div>
  </div>
  <RouterView />
</template>

<script lang="ts" setup>
import { ref } from 'vue';

import {
  UserFilled,
  WalletFilled,
  Van

} from '@element-plus/icons-vue';



const currentActive = ref(window.location.pathname);
</script>

<style scoped>
.nav {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around; /* 均匀分布 */
  position: fixed;
  bottom: 10%; 
  left: 0;
  width: 100%;
  height: 50%; /* 占据屏幕下半部分 */
  background-color: #fff;
  border-top: 1px solid #dcdfe6;
  padding: 20px 0; /* 适当的内边距 */
}

.nav-item {
  width: 90%; /* 每个盒子的宽度 */
  background-color: #f7f7f7; /* 背景色 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px 0;
}

.menu-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu-link {
  text-decoration: none; /* 去除下划线 */
  color: #333; /* 设置默认文字颜色为深灰色 */
  font-size: 18px; /* 设置字体大小 */
  padding: 12px 24px; /* 增加内边距，给按钮留更多空间 */
  border-radius: 8px; /* 设置圆角 */
  background-color: #f7f7f7; /* 背景色设置为 #f7f7f7 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 轻微阴影，增加立体感 */
  display: inline-block; /* 确保链接块级展示 */
  transition: all 0.3s ease; /* 平滑过渡效果 */
}

.menu-link:hover {
  color: #fff; /* 悬停时文字颜色变为白色 */
  background-color: #409EFF; /* 悬停时背景颜色变为蓝色 */
  border-radius: 8px; /* 保持圆角效果 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 增强阴影效果 */
}

.menu-link:focus {
  outline: none; /* 去除焦点时的默认轮廓 */
  border: 1px solid #409EFF; /* 焦点时边框颜色变为蓝色 */
}
.menu-content span {
  font-family: 'Dancing Script', cursive; /* 使用飘逸的字体 */
  font-size: 30px; /* 增大字体以更突出 */
  
  margin-top: 5px;
}
</style>