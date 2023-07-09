import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/api/fontAwesomeIcon.js"; // fontAwesomeIcon.js 불러옴
import "@/api/vueBootstrap.js";

var infiniteScroll = require("vue-infinite-scroll");

Vue.use(infiniteScroll);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  directives: { infiniteScroll },
  render: (h) => h(App),
}).$mount("#app");
