<template>
  <header class="app-header">
    <div class="container">
      <div class="app-branding">
        <RouterLink to="/" className="app-title">뽑기 연습</RouterLink>
      </div>
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
    </div>
  </header>
</template>

<script>
export default {
  name: "Header"
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