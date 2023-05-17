<template>
  <div class="signup-container">
    <div class="signup-content">
      <h1 class="signup-title">회원가입</h1>
      <div class="form-item">
        <input type="email" name="email"
               class="form-control" placeholder="이메일"
               v-model="state.form.email" required/>
        <button class="btn" @click="checkDuplicateEmail()">이메일 중복검사</button>
      </div>
      <div class="form-item">
        <input type="text" name="nickname"
               class="form-control" placeholder="닉네임"
               v-model="state.form.nickname" required/>
        <button class="btn" @click="checkDuplicateNickname()">닉네임 중복검사</button>
      </div>

      <div class="form-item">
        <input type="password" name="password"
               class="form-control" placeholder="비밀번호"
               v-model="state.form.password" required/>
      </div>
      <div class="form-item">
        <button type="submit" class="btn btn-block btn-primary" @click="submit()">회원가입</button>
      </div>
      <span class="login-link">이미 계정이 있는 경우? <RouterLink to="/login">로그인 페이지로 이동</RouterLink></span>
    </div>
  </div>

</template>

<script setup>
import axios from "axios";
import {reactive} from "vue";
import router from "@/scripts/router";

let checkEmail = false;
let checkNickname = false;

const state = reactive({
  form: {
    email: "",
    nickname: "",
    password: "",
  }
})

const checkDuplicateEmail = () => {
  axios.get("/api/v1/members/email", {params: {email: state.form.email}}).then(() => {
    checkEmail = true;
    window.alert("사용 가능한 이메일입니다.");
  }).catch(() => {
    window.alert("이미 존재하는 이메일입니다.");
  });
}

const checkDuplicateNickname = () => {
  axios.get("/api/v1/members/nickname", {params: {nickname: state.form.nickname}}).then(() => {
    checkNickname = true;
    window.alert("사용 가능한 닉네임입니다.");
  }).catch(() => {
    window.alert("이미 존재하는 닉네임입니다.");
  });
}

const submit = () => {
  if (checkNickname && checkEmail) {
    axios.post("/api/v1/members", state.form).then((res) => {
      window.alert("회원가입에 성공했습니다.");
      router.push({path: "/login"});
    }).catch((res) => {
      console.log(JSON.stringify(res.data));
      window.alert("회원가입에 실패했습니다.");
    });
  } else {
    window.alert("이메일 혹은 닉네임 중복검사를 해주세요.");
  }
}
</script>

<style scoped>
.signup-container {
  text-align: center;
}

.signup-content {
  background: #fff;
  box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
  border-radius: 2px;
  width: 500px;
  display: inline-block;
  margin-top: 30px;
  vertical-align: middle;
  position: relative;
  padding: 35px;
}

.social-btn {
  margin-bottom: 15px;
  font-weight: 400;
  font-size: 16px;
}

.social-btn img {
  height: 32px;
  float: left;
  margin-top: 10px;
}

.social-btn.google {
  margin-top: 7px;
}

.social-btn.facebook img {
  height: 24px;
  margin-left: 3px;
}

.social-btn.github img {
  height: 24px;
  margin-left: 3px;
}

.login-link span {
  color: rgba(0, 0, 0, 0.65);
  font-size: 14px;
}

.signup-title {
  font-size: 1.5em;
  font-weight: 500;
  margin-top: 0;
  margin-bottom: 30px;
  color: rgba(0, 0, 0, 0.65);
}
</style>