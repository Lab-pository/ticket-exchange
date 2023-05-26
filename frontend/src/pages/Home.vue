<template>
  <div class="home">
    <div class="container">
      <div class="home-intro">
        <h1>티켓 교환소</h1>
      </div>
      <div class="products-container">
        <div v-for="(p, idx) in state.products" :key="idx">
          <Product :product="p"></Product>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import {onMounted, reactive} from "vue";
import lib from "@/scripts/lib";
import Product from "@/components/Product.vue";

const state = reactive({
  products: []
});

const now = lib.getNowDateWithFormat();
onMounted(async () => {
  await axios.get("/api/v1/products", {params: {now: now}}).then((res) => {
    state.products = res.data.data;
  })
});
</script>

<style scoped>

</style>