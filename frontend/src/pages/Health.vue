<template>
  <div>
    <button @click="checkHealth()">Health Check</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Health",
  setup() {
    const checkHealth = () => {
      axios.get("/api/v1/health").then((res) => {
        window.alert("Hello world, result = " + res.data.data);
      }).catch(() => {
        window.alert("Please check server status");
      });
    }
    return {checkHealth}
  }
}

</script>

<style scoped>

</style>