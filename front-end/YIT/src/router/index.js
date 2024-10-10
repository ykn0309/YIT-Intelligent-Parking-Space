import { createRouter, createWebHistory } from 'vue-router'
import login from '@/views/Login/index.vue'
import header from '@/views/Header/index.vue'
import nav from '@/views/Nav/index.vue'
import carsManager from '@/views/CarsManager/index.vue'
import parkingMap from '@/views/ParkingMap/index.vue'

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
            }
          ]
        }
      ]
    }
  ]
})

export default router
