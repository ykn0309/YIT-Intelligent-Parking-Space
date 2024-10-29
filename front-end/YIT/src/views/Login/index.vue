<template>
  <div class="container" :class="{ 'sign-up-mode': signUpMode }">
    <!-- form表单容器 -->
    <div class="forms-container">
      <div class="signin-signup">
        <!-- 登录 -->

        <el-form
          ref="loginForm"
          :model="loginUser"
          :rules="rules"
          label-width="100px"
          class="loginForm sign-in-form"
          >
          <div class="app-name">易停</div>
          <el-form-item label="用户名" prop="username">
          <el-input v-model="loginUser.username" placeholder="输入用户名" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
          <el-input
              v-model="loginUser.password"
              type="password"
              placeholder="输入密码"
          />
          </el-form-item>
          <el-form-item>
          <!--<el-button @click="resetForm(ruleFormRef)">Reset</el-button>-->
          </el-form-item>
          
          <div class="btn-box">
              <el-button type="success" class="submit-btn" @click="handleLogin(loginForm)"
                  >登录</el-button
              >
              <el-button type="primary" @click="signUpMode = !signUpMode" class="submit-btn">
                注册
              </el-button>
          </div>
          <!-- <div class="tiparea">
              <p>忘记密码?</p>
          </div> -->
      </el-form>
      
        <!--<LoginForm :loginUser="loginUser" :rules="rules" />
         注册 -->
         <el-form
          ref="registerForm"
          :model="registerUser"
          :rules="registerRules"
          label-width="100px"
          class="registerForm sign-up-form"
          >
          <div class="app-name2">易停</div>
          <el-form-item label="用户名" prop="username">
          <el-input v-model="registerUser.username" placeholder="输入用户名" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
          <el-input
              v-model="registerUser.password"
              type="password"
              placeholder="输入密码"
          />
          </el-form-item>
          <el-form-item label="确认密码" prop="password2">
          <el-input
              v-model="registerUser.password2"
              type="password"
              placeholder="再次输入密码"
          />
          </el-form-item>
          <el-form-item>
          <!--<el-button @click="resetForm(ruleFormRef)">Reset</el-button>-->
          </el-form-item>
          <div class="btn-box">
            <el-button type="success" class="submit-btn" @click="handleRegister(registerForm)"
                >注册</el-button
            >
            <el-button type="primary" @click="signUpMode = !signUpMode" class="submit-btn">
              登录
            </el-button>
          </div>
      </el-form>


      </div>
    </div>
    <!-- 左右切换动画 -->
    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h1 style="display: inline-block; margin-right: 1vh; font-size: 5vh;">欢迎使用</h1>
          <h1 style="display: inline-block; font-size: 6vh;"> 易停</h1>
          <h3 style="font-size: 3vh; margin-top: 1vh;">智能停车空间</h3>
         <p></p>
        </div>
        <img src="../../assets/images/log.svg" class="image" alt="" />
      </div>

      <div class="panel right-panel">
        <div class="content">
         <h1 style="display: inline-block; margin-right: 20px; font-size: 5vh;">欢迎注册</h1>
         <h1 style="display: inline-block; margin-right: 100px; font-size: 6vh;"> 易停</h1>
        </div>
        <img src="../../assets/images/register.svg" class="image" alt="" />
      </div>
    </div>
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
const loginForm = ref<FormInstance>()
const registerForm= ref<FormInstance>()
const signUpMode = ref(false);
const loginUser = ref({
  username: "",
  password: "",
})
//import { ref } from 'vue';
//登陆界面校验规则
/*
onMounted(() => {
  api.getProject().then(res=>{
  console.log(res.data)
})
}
)*/

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
.btn-box {
width: 100%;
margin-top: 10px;
margin-left: calc(50% - 125px);
}
.app-name {
margin-left: calc(50% - 75px);
margin-bottom: 50px;
width: 150px;
text-align: center;
font-size: 40px;
font-weight:600;
}
.app-name2 {
margin-left: calc(50% - 75px);
margin-bottom: 50px;
width: 150px;
text-align: center;
font-size: 40px;
font-weight:600;
}
.container {
position: relative;
width: 100%;
background-image: linear-gradient(-45deg, #c3fcd1 0%, #a4c6f8 100%);
min-height: 100vh;
overflow: hidden;
}

.forms-container {
position: absolute;
width: 100%;
height: 100%;
top: 0;
left: 0;
}

.signin-signup {
position: absolute;
top: 70%;
transform: translate(-50%, -50%);
left: 75%;
width: 44%;
transition: 1s 0.7s ease-in-out;
display: grid;
grid-template-columns: 1fr;
z-index: 5;
}

/* 左右切换动画 */
.social-text {
padding: 0.7rem 0;
font-size: 1rem;
}

.social-media {
display: flex;
justify-content: center;
}

.social-icon {
height: 46px;
width: 46px;
display: flex;
justify-content: center;
align-items: center;
margin: 0 0.45rem;
color: #333;
border-radius: 50%;
border: 1px solid #333;
text-decoration: none;
font-size: 1.1rem;
transition: 0.3s;
}

.social-icon:hover {
color: #4481eb;
border-color: #4481eb;
}

.btn {
/* width: 50px; */
background-color: #5995fd;
border: none;
outline: none;
height: 49px;
border-radius: 49px;
color: #fff;
text-transform: uppercase;
font-weight: 600;
cursor: pointer;
transition: 0.5s;
}

.btn:hover {
background-color: #4d84e2;
}
.panels-container {
position: absolute;
height: 100%;
width: 100%;
top: 0;
left: 0;
display: grid;
grid-template-columns: repeat(2, 1fr);
}

.container:before {
content: "";
position: absolute;
height: 2000px;
width: 2000px;
top: -10%;
right: 48%;
transform: translateY(-50%);
background-image: linear-gradient(-45deg, #ffc8e3 0%, #033dfd 100%);
transition: 1.8s ease-in-out;
border-radius: 50%;
z-index: 6;
}

.image {
width: 100%;
transition: transform 1.1s ease-in-out;
transition-delay: 0.4s;
}

.panel {
display: flex;
flex-direction: column;
align-items: flex-end;
justify-content: space-around;
text-align: center;
z-index: 6;
}

.left-panel {
pointer-events: all;
padding: 3rem 17% 2rem 12%;
}

.right-panel {
pointer-events: none;
padding: 3rem 12% 2rem 17%;
}

.panel .content {
color: #fff;
transition: transform 0.9s ease-in-out;
transition-delay: 0.6s;
}

.panel h3 {
font-weight: 600;
line-height: 1;
font-size: 1.5rem;
}

.panel p {
font-size: 0.95rem;
padding: 0.7rem 0;
}

.btn.transparent {
margin: 0;
background: none;
border: 2px solid #fff;
width: 130px;
height: 41px;
font-weight: 600;
font-size: 0.8rem;
}

.right-panel .image,
.right-panel .content {
transform: translateX(50vw);
}

/* ANIMATION */

.container.sign-up-mode:before {
transform: translate(100%, -50%);
right: 52%;
}

.container.sign-up-mode .left-panel .image,
.container.sign-up-mode .left-panel .content {
transform: translateX(-50vw);
}

.container.sign-up-mode .signin-signup {
left: 25%;
top: 40%;
}

.container.sign-up-mode form.sign-up-form {
opacity: 1;
z-index: 2;
}

.container.sign-up-mode form.sign-in-form {
opacity: 0;
z-index: 1;
}

.container.sign-up-mode .right-panel .image,
.container.sign-up-mode .right-panel .content {
transform: translateX(0%);
}

.container.sign-up-mode .left-panel {
pointer-events: none;
}

.container.sign-up-mode .right-panel {
pointer-events: all;
}

@media (max-width: 870px) {
.container {
  min-height: 800px;
  height: 100vh;
}
.signin-signup {
  width: 100%;
  top: 95%;
  transform: translate(-50%, -100%);
  transition: 1s 0.8s ease-in-out;
}

.signin-signup,
.container.sign-up-mode .signin-signup {
  left: 50%;
}

.panels-container {
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 2fr 1fr;
}

.panel {
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  padding: 2.5rem 8%;
  grid-column: 1 / 2;
}

.right-panel {
  grid-row: 3 / 4;
}

.left-panel {
  grid-row: 1 / 2;
}

.image {
  width: 200px;
  transition: transform 0.9s ease-in-out;
  transition-delay: 0.6s;
}

.panel .content {
  padding-right: 15%;
  transition: transform 0.9s ease-in-out;
  transition-delay: 0.8s;
}

.panel h3 {
  font-size: 1.2rem;
}

.panel p {
  font-size: 0.7rem;
  padding: 0.5rem 0;
}

.btn.transparent {
  width: 110px;
  height: 35px;
  font-size: 0.7rem;
}

.container:before {
  width: 1500px;
  height: 1500px;
  transform: translateX(-50%);
  left: 30%;
  bottom: 68%;
  right: initial;
  top: initial;
  transition: 2s ease-in-out;
}

.container.sign-up-mode:before {
  transform: translate(-50%, 100%);
  bottom: 32%;
  right: initial;
}

.container.sign-up-mode .left-panel .image,
.container.sign-up-mode .left-panel .content {
  transform: translateY(-300px);
}

.container.sign-up-mode .right-panel .image,
.container.sign-up-mode .right-panel .content {
  transform: translateY(0px);
}

.right-panel .image,
.right-panel .content {
  transform: translateY(300px);
}

.container.sign-up-mode .signin-signup {
  top: 5%;
  transform: translate(-50%, 0);
}
}

@media (max-width: 570px) {
form {
  padding: 0 1.5rem;
}

.image {
  display: none;
}
.panel .content {
  padding: 0.5rem 1rem;
}
.container {
  padding: 1.5rem;
}

.container:before {
  bottom: 72%;
  left: 50%;
}

.container.sign-up-mode:before {
  bottom: 28%;
  left: 50%;
}
}

/* 控制login & register显示 */
form {
padding: 0rem 5rem;
transition: all 0.2s 0.7s;
overflow: hidden;
}

form.sign-in-form {
z-index: 2;
}

form.sign-up-form {
opacity: 0;
z-index: 1;
}

/* register */
.registerForm {
margin-top: 20px;
background-color: #fff;
padding: 40px 40px 20px 20px;
border-radius: 20px;
box-shadow: 0px 5px 10px #54c9d1;
}

.submit-btn {
width: 100px;
border-radius: 20px;
box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
margin-right: 50px;
}
.loginForm {
margin-top: 10px;
background-color: #fff;
padding: 40px 40px 20px 20px;
border-radius: 20px;
box-shadow: 0px 5px 10px #62dfb9cc;
}
.tiparea{
  text-align: right;
  font-size: 12px;
  color:#333;
}
.toparea p a{
  color:#409eff;
}
</style>>