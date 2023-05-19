<template>
  <div class="earned-product-container">
    <p>획득 물품 : {{ state.name }} </p>
    <p>획득 시간 : {{ state.createdAt.replace('T', ' ').replaceAll('-', '.').slice(0, 16) }} </p>
  </div>
</template>

<script setup>
import {reactive, defineProps} from "vue";

const props = defineProps({
  earnedProduct: Object,
});

const state = reactive({
  earnedProductId: props.earnedProduct.earnedProductId,
  productId: props.earnedProduct.productId,
  name: props.earnedProduct.name,
  createdAt: props.earnedProduct.createdAt,
});
</script>

<style scoped>

</style>