<template>
  <div class="login-container">
    <div class="login-content">
      <h1 class="login-title">로그인</h1>
      <div class="form-item">
        <input type="email" name="email" class="form-control" placeholder="이메일" v-model="state.form.email"
               required/>
      </div>
      <div class="form-item">
        <input type="password" name="password" class="form-control" placeholder="비밀번호" @keyup.enter="submit()"
               v-model="state.form.password" required/>
      </div>
      <div class="form-item">
        <button class="btn btn-block btn-primary" @click="submit()">로그인</button>
      </div>
      <span class="signup-link">처음 방문하신다면? <RouterLink to="/signup"> 회원가입 페이지로 이동</RouterLink></span>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

const state = reactive({
  form: {
    email: "",
    password: ""
  }
})

const submit = () => {
  axios.post("/api/v1/login", state.form).then((res) => {
    const token = res.data.data.token;
    store.commit("setToken", token);
    sessionStorage.setItem("token", token);
    router.push({path: "/"});
    window.alert("로그인하였습니다.");
  }).catch((res) => {
    window.alert("아이디와 비밀번호를 확인해주세요.");
  });
}
</script>

<style scoped>

</style>