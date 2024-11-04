import { createRouter, createWebHistory } from 'vue-router'
import login from '@/views/Login/index.vue'
import userlogin from '@/views/UserLogin/index.vue'
import header from '@/views/Header/index.vue'
import mb from '@/views/Mobile/index.vue'
import nav from '@/views/Nav/index.vue'
import usernav from '@/views/UserNav/index.vue'
import center from '@/views/Center/index.vue'
import carsManager from '@/views/CarsManager/index.vue'
import parkingMap from '@/views/ParkingMap/index.vue'
import camera from '@/views/Camera/index.vue'
import usermap from '@/views/UserParkingMap/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
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
      path: '/mb',
      name: 'mb',
      component: mb,
      children: [
        {
          path: '/mb/usernav',
          component: usernav,
          children: [
            {
              path: '/mb/center',
              component: center,
            },
            {
              path: '/mb/usermap',
              component: usermap,
            },
          ]
        }
      ],
    },
  ]
})

export default router
