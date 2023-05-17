import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import router from '@/scripts/router'
import store from '@/scripts/store'

createApp(App).use(router).use(store).mount('#app')
