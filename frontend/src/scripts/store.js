import {createStore} from "vuex"

const store = createStore({
  state() {
    return {
      member: {
        token: "",
      }
    }
  },
  mutations: {
    setToken(state, payload) {
      state.member.token = payload;
    }
  }
})

export default store;