<template>
  <div class="my-page-container">
    <div class="ticket-information">
      <h1>총 획득 티켓 : {{ totalTicketCount }}</h1>
      <h2>사용 티켓 : {{ useTicketCount }}</h2>
      <h2>유효 티켓 : {{ validTicketCount }}</h2>
      <h2>획득 물품 수 : {{ earnedProductCount }}</h2>
    </div>
    <div v-for="(t, idx) in state.tickets" :key="idx">
      <Ticket :ticket="t"/>
    </div>
    <br/>
    <div v-for="(ep, idx) in state.earnedProducts" :key="idx">
      <EarnedProduct :earnedProduct="ep"/>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import Ticket from "@/components/Ticket.vue";
import EarnedProduct from "@/components/EarnedProduct.vue";

const totalTicketCount = ref(0);
const useTicketCount = ref(0);
const validTicketCount = ref(0);
const earnedProductCount = ref(0);

const state = reactive({
  tickets: [],
  earnedProducts: [],
})

onMounted(async () => {
  const token = sessionStorage.getItem("token");
  axios.get("/api/v1/tickets", {headers: {"X-AUTH-TOKEN": token}}).then((res) => {
    totalTicketCount.value = res.data.data.totalTicketCount;
    useTicketCount.value = res.data.data.useTicketCount;
    validTicketCount.value = res.data.data.validTicketCount;
    state.tickets = res.data.data.tickets;
  }).catch((res) => {

  });
  axios.get("/api/v1/earned-products", {headers: {"X-AUTH-TOKEN": token}}).then((res) => {
    state.earnedProducts = res.data.data;
    earnedProductCount.value = state.earnedProducts.length;
  }).catch((res) => {

  });
})
</script>

<style scoped>

</style>