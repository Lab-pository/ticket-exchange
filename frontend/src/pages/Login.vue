<template>
  <div>
    <h1>로그인</h1>
    <form @submit.prevent="submit">
      <div>
        <label for="email">이메일</label>
        <input type="email" id="email" @keyup.enter="submit()" v-model="state.form.email">
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input type="password" id="password" @keyup.enter="submit()" v-model="state.form.password">
      </div>
      <button type="submit" @click="submit()">로그인</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import {reactive} from "vue";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        email: "",
        password: ""
      }
    })

    const submit = () => {
      axios.post("/api/v1/login", state.form).then((res) => {
        store.commit("setAccount", res.data)
        sessionStorage.setItem("token", res.data)
        router.push({path: "/"})
        window.alert("로그인하였습니다.");
      }).catch(() => {
        window.alert("잘못된 정보 입니다.");
      });
    }
    return {state, submit}
  },
  name: "Login"
}
</script>

<style scoped>

</style>