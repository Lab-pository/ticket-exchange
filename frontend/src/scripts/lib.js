export default {
  getNowDateWithFormat() {
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    if (month < 10) {
      return year + "-0" + month + "-" + day;
    }
    return year + "-" + month + "-" + day;
  },

  hasToken() {
    return sessionStorage.getItem("token") !== null;
  }
}