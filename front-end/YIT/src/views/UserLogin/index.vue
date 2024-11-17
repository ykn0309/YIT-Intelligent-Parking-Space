<template>
  <div class="app-container">
    <el-form
          ref="loginForm"
          :model="loginUser"
          :rules="rules"
          label-width="100px"
          class="loginForm sign-in-form"
          v-if="!signUpMode"

          >
      <!-- 艺术字标题 -->
      <div class="title">易停</div>

      <!-- 输入框 -->
      <div class="input-container">       
        <!-- <el-form-item prop="username" align-items="center"> -->
          <input type="text" class="login-input" v-model="loginUser.username" placeholder="用户名">
        <!-- </el-form-item> -->
        <!-- <el-form-item prop="username"> -->
          <input type="password" class="login-input" v-model="loginUser.password" placeholder="密码">
        <!-- </el-form-item> -->
        
      </div>

      <!-- 登录按钮 -->
      <div class="button-container">
        <button class="login-button blue" @click="handleLogin(loginForm)">登录</button>
        <button class="login-button white" @click="signUpMode = !signUpMode">注册</button>
        <!-- <a href="/signup" class="button login-button white" @click="signUp()">注册</a> -->
        <!-- <button class="login-button white" @click="signUpMode = !signUpMode">注册</button> -->

        <!-- <el-button type="primary" class="login-button blue" plain>登录</el-button>
        <el-button type="success" class="login-button white" plain>注册</el-button> -->
      </div>

      <!-- 协议勾选框和链接 -->
      <div class="agreement-container">
        <input type="checkbox" id="agree" v-model="agree">
        <label for="agree">
          我已同意
          <a href="/terms-of-service" target="_blank">服务协议</a>
          与
          <a href="/privacy-policy" target="_blank">隐私政策</a>
        </label>
      </div>
    </el-form>
    <el-form
      ref="registerForm"
      :model="registerUser"
      :rules="registerRules"
      label-width="100px"
      class="registerForm sign-up-form"
      v-if="signUpMode"
      >
      <!-- 艺术字标题 -->
      <div class="title">易停</div>

      <!-- 输入框 -->
      <div class="input-container">
        <input type="text" class="login-input" placeholder="输入用户名">
        <input type="password" class="login-input" placeholder="输入密码">
        <input type="password" class="login-input" placeholder="确认密码">
      </div>

      <!-- 登录按钮 -->
      <div class="button-container">
        <button class="login-button blue" @click="handleRegister(registerForm)">注册</button>
        <button class="login-button white" @click="signUpMode = !signUpMode">去登录</button>
        <!-- <a href="/signup" class="button login-button white" @click="signUp()">注册</a> -->
        <!-- <button class="login-button white" @click="signUpMode = !signUpMode">注册</button> -->

        <!-- <el-button type="primary" class="login-button blue" plain>登录</el-button>
        <el-button type="success" class="login-button white" plain>注册</el-button> -->
      </div>

      <!-- 协议勾选框和链接 -->
      <div class="agreement-container">
        <input type="checkbox" id="agree" v-model="agree">
        <label for="agree">
          我已同意
          <a href="/terms-of-service" target="_blank">服务协议</a>
          与
          <a href="/privacy-policy" target="_blank">隐私政策</a>
        </label>
      </div>

    </el-form>
  </div>
  
</template>

<script lang="ts" setup>
import { ref,reactive} from 'vue';
import axios from 'axios'
import type { FormInstance, FormRules } from 'element-plus'
import { onMounted } from 'vue';
// import api from '@/apis';


import router from '@/router';
import http from '@/utils/http';



//import { ref } from 'vue';
const agree = ref(false); // 用于绑定勾选框的状态
const loginForm = ref<FormInstance>()
const registerForm= ref<FormInstance>()
const signUpMode = ref(false);
const loginUser = ref({
  username: "",
  password: "",
})

// function signUp() {
//   signUpMode.value = !signUpMode.value;
//   // this.$router.push('/signup');
// }

function handleLogin(formEl: FormInstance | undefined) {    
    if (!formEl) return
formEl.validate((valid) => {
  if (valid) {
    http.post('http://localhost:8080/login', loginUser.value)
      .then(response => {
            console.log(response)
        if (response.status == 'success') {
          router.push('map');
          window.sessionStorage.setItem('userid', response.data[0])
          window.sessionStorage.setItem('username', response.data[1])
          // window.sessionStorage.setItem('nickname', response.value[1])
              }
              else{
                  alert('用户不存在或者密码错误');
              }
              // sessionStorage.setItem('userId', loginUser.value.username);

              console.log('服务器响应:', response);
              // 成功回调
          })
          .catch(error => {
              console.error('There was an error!', error);
              // 失败回调
          });
    //console.log('submit!')
  } else {
    console.log('error submit!')
    return false
  }
})
}

const rules = ref({
  username: [{ required: true, message: "用户名不能为空", trigger: 'blur' }],
  password: [{ required: true, message: "密码不能为空", trigger: 'blur' }, { min: 6, max: 18, message: "密码在6到18位", trigger: 'blur' }],
  //age: [{ validator: checkAge, trigger: 'blur' }],
})



//注册
const registerUser = ref({
username: "",
password: "",
password2:"",
})
const validatePass2 = (rule: any, value: any, callback: any) => {
if (value === '') {
  callback(new Error('请再次输入密码'))
} else if (value !== registerUser.value.password) {
  callback(new Error("两次输入密码不一致！"))
} else {
  callback()
}
}

const registerRules = ref({
  username: [{ required: true, message: "用户名不能为空", trigger: 'blur' }],
  password: [{ required: true, message: "密码不能为空", trigger: 'blur' }, { min: 6, max: 18, message: "密码在6到18位", trigger: 'blur' }],
  password2: [{ required: true, message: "密码不能为空", trigger: 'blur' },
  { min: 6, max: 18, message: "密码在6到18位", trigger: 'blur' },
  {
  validator:validatePass2,trigger:'blur'
}],
//age: [{ validator: checkAge, trigger: 'blur' }],
})


function handleRegister(formEl: FormInstance | undefined) {    
    if (!formEl) return
formEl.validate((valid) => {
  if (valid) {
    http.post('http://localhost:8080/register', registerUser.value)
      .then(response => {
            console.log(response)
        if (response.status == 'success') {
              
                alert('注册成功');
                //router.push('/project');
            this.signUpMode = false;
              }
              else{
                  alert('注册失败，账号已存在');
              }
              console.log('服务器响应:', response);
              // 成功回调
          })
          .catch(error => {
              console.error('There was an error!', error);
              // 失败回调
          });
    console.log('submit!')
  } else {
    console.log('error submit!')
    return false
  }
})
}
</script>











<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh; /* 使容器高度占满整个视口高度 */
  padding: 20px;
  background-image: linear-gradient(-45deg, #c3fcd1 0%, #a4c6f8 100%);
}
/* .container {
position: relative;
width: 100%;
background-image: linear-gradient(-45deg, #c3fcd1 0%, #a4c6f8 100%);
min-height: 100vh;
overflow: hidden;
} */

.title {
  font-size: 48px; /* 根据需要调整字体大小 */
  font-weight: bold; /* 加粗字体 */
  background-image: linear-gradient(45deg, #493ee7 30%, #cb9486 50%, #9c68e4 70%, #b752d9);
  -webkit-background-clip: text;
  color: transparent;
  margin-bottom: 20px;
  font-family: 'Courier New', Courier, monospace; /* 使用等宽字体以增加艺术效果 */
  text-align: center;
  letter-spacing: 2px; /* 字间距 */
}

.input-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px; /* 输入框之间的间距 */
  margin-bottom: 20px;
}

.login-input {
  padding: 10px 20px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 16px;
  width: 80%; /* 使输入框宽度占满容器宽度 */
  text-align: center; /* 文字居中 */
}

.button-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px; /* 按钮之间的间距 */
  margin-bottom: 20px;
}

.login-button {
  font-weight: bold; /* 加粗字体 */
  padding: 10px 20px;
  border-radius: 5px;
  font-size: 16px;
  text-transform: uppercase; /* 使按钮文字大写 */
  width: 80%; /* 使按钮宽度占满容器宽度 */
}

.blue {
  background-color: #0099ff;
  color: white;
  border: 1px solid #ccc;
}

.white {
  background-color: white;
  color: black;
  border: 1px solid #ccc;
}

.agreement-container {
  font-size: 14px;
  display: flex;
  align-items: center;
  margin-bottom: 20px; /* 为勾选框添加底部间距 */
}

.agreement-container a {
  margin-left: 5px; /* 链接与文本之间的间距 */
  color: #0099ff; /* 链接颜色 */
  text-decoration: none; /* 去除下划线 */
}

.agreement-container a:hover {
  text-decoration: underline; /* 鼠标悬停时添加下划线 */
}

input[type="checkbox"] {
  margin-right: 5px;
}

.loginForm {
margin-top: 10px;
background-color: #fff;
padding: 50px 50px 20px 50px;
border-radius: 20px;
box-shadow: 0px 5px 10px #62dfb9cc;
}

/* register */
.registerForm {
margin-top: 20px;
background-color: #fff;
padding: 50px 50px 20px 50px;
border-radius: 20px;
box-shadow: 0px 5px 10px #54c9d1;
}

</style>