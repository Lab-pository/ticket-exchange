import {createRouter, createWebHistory} from "vue-router";
import Home from '@/pages/Home.vue'
import Login from '@/pages/Login.vue'
import Health from "@/pages/Health.vue";

const routes = [
  {path: '/', component: Home},
  {path: '/login', component: Login},
  {path: '/health', component: Health},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;