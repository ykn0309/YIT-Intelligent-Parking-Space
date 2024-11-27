import { createRouter, createWebHistory } from 'vue-router'
import login from '@/views/Login/index.vue'
import userlogin from '@/views/UserLogin/index.vue'
import header from '@/views/Header/index.vue'
import mobile from '@/views/Mobile/index.vue'
import nav from '@/views/Nav/index.vue'
import usernav from '@/views/UserNav/index.vue'
import center from '@/views/Center/index.vue'
import carsManager from '@/views/CarsManager/index.vue'
import parkingMap from '@/views/ParkingMap/index.vue'
import camera from '@/views/Camera/index.vue'
import usermap from '@/views/UserParkingMap/index.vue'
import usercenter from '@/views/UserCenter/index.vue'
import userinformation from '@/views/UserInfor/index.vue'
import userwallet from '@/views/UserWallet/index.vue'
import usercars from '@/views/UserCars/index.vue'
import usercenteroption from '@/views/UsercenterOption/index.vue'
////


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  base: '/',
  routes: [
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/',
      name: 'header',
      component: header,
      children: [
        {
          path: '',
          component: nav,
          children: [
            {
              path: '',
              component: carsManager
            },
            {
              path: 'map',
              component: parkingMap
            },
            {
              path: 'camera',
              component: camera
            }
          ]
        }
      ],
    },
    {
      path: '/userlogin',
      name: 'userlogin',
      component: userlogin,
    },
    {
      path: '/mobile',
      name: 'mobile',
      component: mobile,
      children: [
        {
          path: '',
          component: usernav,
          children:[
            {
              path: 'center',
              component: center,
            },
            {
              path: 'usermap/:pageid',
              
              component: usermap,
            },
           
            {
              path: 'usercenter',
              component: usercenter,
              children:
              [
                {
                  path: '',
                  component: usercenteroption,
                },
                {
                  path: 'userinformation',
                  component: userinformation,
                },
                {
                  path: 'userwallet',
                  component: userwallet,
                },
                {
                  path: 'usercars',
                  component: usercars,
                },
                
              ]
            },
            
          ]
        },
      ],
    },
  ]
})

export default router
