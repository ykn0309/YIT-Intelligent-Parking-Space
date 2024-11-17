<template>
  <div class="avatar-uploader">
    
    <input type="file" accept="image/*" @change="handleFileChange" class="upload-input" />
    <div class="avatar-preview" @click="triggerUpload">
      <img v-if="avatarSrc" :src="avatarSrc" alt="Avatar" />
      <span v-else class="placeholder">上传头像</span>
    </div>
  </div>
  <RouterView />
</template>

<script lang="ts" setup>

import { ref } from 'vue';
const avatarSrc = ref<string | null>(null);

const handleFileChange = (event: Event) => {
  const file = (event.target as HTMLInputElement).files?.[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      avatarSrc.value = reader.result as string;
    };
    reader.readAsDataURL(file);
  }
};

const triggerUpload = () => {
  const input = document.querySelector('.upload-input') as HTMLInputElement;
  input.click();
};

</script>

<style scoped>
.avatar-uploader {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.upload-input {
  display: none;
}

.avatar-preview {
  width: 160px;
  height: 160px;
  border-radius: 50%;
  background-color: #f0f0f0;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border: 1px solid #dcdfe6;
  overflow: hidden;
}

.avatar-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.placeholder {
  font-size: 14px;
  color: #999;
}



.menu-link {
  width: 100%;
  text-align: center;
  text-decoration: none;
}


</style>
