<template>
    <div class="nav">
        <el-menu
          default-active="1"
          @open="handleOpen"
          @close="handleClose"
        >
          <el-menu-item index="1">
            <el-icon><icon-menu /></el-icon>
            <span>车辆管理</span>
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon><document /></el-icon>
            <span>车位总览</span>
          </el-menu-item>
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
  const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
  }
  const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
  }
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
</style>