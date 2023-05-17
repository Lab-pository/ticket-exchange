import {createStore} from "vuex"

const store = createStore({
  state() {
    return {
      account: {
        token: "",
      }
    }
  },
  mutations: {
    setAccount(state, payload) {
      state.account.token = payload;
    }
  }
})

export default store;