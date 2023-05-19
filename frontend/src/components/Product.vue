<template>
  <div class="product-container">
    <span>{{ getExpiration() }}</span>
    <p>{{ state.name }}</p>
    <p>{{ counting() }}</p>
    <button @click="applyProduct(state.productId, state.remainQuantity)">
      필요 티켓 {{ state.needTicketCount }}개 | 응모하기
    </button>
  </div>
</template>

<script setup>
import axios from "axios";
import {reactive, defineProps} from "vue";
import lib from "@/scripts/lib";

// props = readOnly
const props = defineProps({
  product: Object,
})

const state = reactive({
  productId: props.product.productId,
  name: props.product.name,
  validStartDate: props.product.validStartDate,
  validEndDate: props.product.validEndDate,
  totalQuantity: props.product.totalQuantity,
  remainQuantity: props.product.remainQuantity,
  needTicketCount: props.product.needTicketCount,
})

const applyProduct = (productId, remainQuantity) => {
  if (lib.hasToken() && remainQuantity > 0) {
    const now = lib.getNowDateWithFormat();
    const token = sessionStorage.getItem("token");
    axios.post(`/api/v1/products/${productId}`, null, {
      params: {now: now},
      headers: {"X-AUTH-TOKEN": token}
    }).then((res) => {
      const result = res.data.data.result;
      if (result) {
        state.remainQuantity = res.data.data.product.remainQuantity;
        window.alert("당첨되었습니다!!");
      } else {
        window.alert("경품 응모에 당첨되지 않았습니다.");
      }
    }).catch((res) => {

    });
  } else {
    window.alert("로그인을 해주세요.");
  }
}

const getExpiration = () => {
  const startArr = state.validStartDate.split("-");
  const endArr = state.validEndDate.split("-");
  return `${startArr[1]}/${startArr[2]} ~ ${endArr[1]}/${endArr[2]}`;
}

const counting = () => {
  return `${state.remainQuantity}개 남음 / 총 ${state.totalQuantity}개`;
}
</script>

<style scoped>

</style>