import {createRouter, createWebHistory} from "vue-router";
import Home from '@/pages/Home.vue'
import Login from '@/pages/Login.vue'
import Health from "@/pages/Health.vue";
import Signup from "@/pages/Signup.vue";
import MyPage from "@/pages/MyPage.vue";

const routes = [
  {path: '/', component: Home},
  {path: '/login', component: Login},
  {path: '/signup', component: Signup},
  {path: '/health', component: Health},
  {path: '/my-page', component: MyPage},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;