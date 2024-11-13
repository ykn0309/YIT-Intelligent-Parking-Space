<template>
    <div class="usernav">

          <span slot="title">我的主页1</span>
    </div>
    <RouterView />
</template>
  
<script lang="ts" setup>
  import {
    Document,
    Menu as IconMenu,
    VideoCameraFilled,
    Location,
    Setting,
  } from '@element-plus/icons-vue'
  import {ref} from 'vue';
  const currentActive = ref(window.location.pathname);
</script>

<style scoped>
    .usernav {
        float: left;
        width: 180px;
        height: calc(100vh - 51px)-200;
        border-right: 1px solid #dcdfe6;
    }

    .usernav .el-menu {
        border: none;
    }

    a {
      text-decoration: none;
    }
</style>