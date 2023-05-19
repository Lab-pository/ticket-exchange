<template>
  <div class="ticket-container">
    <p>획득 방법 : {{ state.ticketId }} </p>
    <p>획득 방법 : {{ state.howToAcquire }} </p>
    <p>획득 날짜 : {{ state.acquireDate.replaceAll('-', '.') }} </p>
    <p>만료 날짜 : {{ state.expireDate.replaceAll('-', '.') }} </p>
    <template v-if="state.used">
      <span>사용함</span>
      <span>{{ state.howToUse }}</span>
      <span>{{ state.useDate }}</span>
    </template>
    <template v-else>
      <span>사용안함</span>
    </template>
  </div>
</template>

<script setup>
import {defineProps, reactive} from "vue";

const props = defineProps({
  ticket: Object,
});

const state = reactive({
  ticketId: props.ticket.ticketId,
  howToAcquire: props.ticket.howToAcquire,
  acquireDate: props.ticket.acquireDate,
  expireDate: props.ticket.expireDate,
  used: props.ticket.used,
  howToUse: props.ticket.howToUse,
  useDate: props.ticket.useDate,
});
</script>

<style scoped>

</style>