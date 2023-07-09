<template>
  <div id="app">
    <app-header :boxColor="boxColor"></app-header>
    <!-- <router-view ></router-view>
    <quick-menu-component v-show="this.showQuickMenu" class="fixed"></quick-menu-component> -->
  <!-- 큰 화면으로 보니까 정상으로 나옴 -->
    <b-row>
      <b-col :cols="side"></b-col>
      <b-col :cols="center">

        <router-view @page="page" />

      </b-col>
      <b-col :cols="side">
        <quick-menu-component v-show="this.showQuickMenu"></quick-menu-component>
      </b-col>
    </b-row>

    <app-footer ></app-footer>
  </div>
</template>

<script>
import AppHeader from "@/components/header/AppHeader.vue";
import AppFooter from "./components/footer/AppFooter.vue";
import QuickMenuComponent from "./components/quickmenu/QuickMenuComponent.vue";

export default {
  components: { AppHeader, AppFooter, QuickMenuComponent },
  // components: { AppHeader, AppFooter},
  data() {
    return {
      side: "2",
      center: "8",
      nowPath: "",
      showQuickMenu: true,
      boxColor:"plan",
      my:"my",
    };
  },
  computed: {
    route: function () {
      return this.$route.path;
    },
  },
  watch: {
    $route: function () {
      this.nowPath = this.$route.path;
      
      // console.log(this.nowPath);
      if (this.nowPath == "/plan/write" || this.$route.name == "pmodify") {
        // console.log("현재 플랜 페이지");
        this.center = "auto";
        this.showQuickMenu = false;
      } else {
        this.side = 2;
        this.center = 8;
        this.showQuickMenu = true;
      }
    },
  },
  methods:{
    page(page){
      this.boxColor = page;
      // console.log(this.boxColor);
    }
  }
};
</script>

<style>
.fixed{
  position: fixed;
  margin-left: 100rem;
  margin-bottom: 100rem;
  z-index: 7;
}
HamburgerSidebar #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
.row{
  display: flex;
  justify-content: center;
}

</style>
