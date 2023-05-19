<template>
  <header class="app-header">
    <div class="container">
      <div class="app-branding">
        <RouterLink to="/" className="app-title">뽑기 연습</RouterLink>
      </div>
      <template v-if="isLoggedIn">
        <div class="app-options">
          <nav class="app-nav">
            <ul>
              <li>
                <RouterLink to="/my-page">마이페이지</RouterLink>
              </li>
              <li>
                <a @click="logout">로그아웃</a>
              </li>
            </ul>
          </nav>
        </div>
      </template>
      <template v-else>
        <div class="app-options">
          <nav class="app-nav">
            <ul>
              <li>
                <RouterLink to="/login">로그인</RouterLink>
              </li>
              <li>
                <RouterLink to="/signup">회원가입</RouterLink>
              </li>
            </ul>
          </nav>
        </div>
      </template>
    </div>
  </header>
</template>

<script setup>
import {computed} from "vue";
import lib from "@/scripts/lib";
import store from "@/scripts/store";

const isLoggedIn = computed(() => {
  if (lib.hasToken() && store.state.member.token === "") {
    store.commit("setToken", sessionStorage.getItem("token"));
  }
  return store.state.member.token !== "";
});

const logout = () => {
  store.commit("setToken", "");
  sessionStorage.removeItem("token");
  window.alert("로그아웃 되었습니다.");
}
</script>

<style scoped>
.app-header {
  height: 60px;
  position: relative;
  z-index: 10;
}

.app-branding {
  float: left;
}

.app-options {
  float: right;
}

.app-nav ul {
  list-style-position: none;
  margin: 0;
  padding: 0;
}

.app-nav ul li {
  list-style-type: none;
  display: inline-block;
}

.app-nav ul li a {
  display: inline-block;
  line-height: 60px;
  vertical-align: middle;
  padding-left: 15px;
  padding-right: 15px;
  color: rgba(0, 0, 0, 0.65);
}

.app-nav ul li a:hover {
  color: #0f96f6;
}

.app-nav ul li a.active {
  color: #0f96f6;
}
</style>