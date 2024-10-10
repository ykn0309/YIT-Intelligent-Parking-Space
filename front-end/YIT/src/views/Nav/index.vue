<template>
    <div class="nav">
        <el-menu
          :default-active="currentActive"
        >
          <router-link to="/">
            <el-menu-item index="/">
              <el-icon><icon-menu /></el-icon>
              <span>车辆管理</span>
            </el-menu-item>
          </router-link>
          <router-link to="/map">
            <el-menu-item index="/map">
              <el-icon><document /></el-icon>
              <span>车位总览</span>
            </el-menu-item>
          </router-link>
        </el-menu>
    </div>
    <RouterView />
</template>
  
<script lang="ts" setup>
  import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
  } from '@element-plus/icons-vue'
  import {ref} from 'vue';
  const currentActive = ref(window.location.pathname);
</script>

<style scoped>
    .nav {
        float: left;
        width: 180px;
        height: calc(100vh - 51px);
        border-right: 1px solid #dcdfe6;
    }

    .nav .el-menu {
        border: none;
    }

    a {
      text-decoration: none;
    }
</style>