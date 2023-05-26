<template>
  <header class="app-header">
    <div class="container">
      <div class="app-branding">
        <RouterLink to="/" className="app-title">티켓 교환소</RouterLink>
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

</style>