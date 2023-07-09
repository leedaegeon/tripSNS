<template>
  <div>
    <b-navbar
      id="navbar"
      toggleable="md"
      type="light"
      variant="light"
      style="border-bottom: 3px solid #1e56a0c1"
    >
    <!-- {{boxColor}} -->
      <b-navbar-toggle target="collapse-area"></b-navbar-toggle>
      <b-collapse id="collapse-area" is-nav>
        <b-navbar-nav>
          <b-nav-item v-b-toggle.sidebar-backdrop><b-icon icon="list"></b-icon></b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="d-none d-md-block mx-auto">
          <b-nav-text>
            <router-link :to="{ name: 'main' }">
              <b-img
                :src="require('@/assets/main_logo.png')"
                id="logo"
                class="d-inline-block align-top ml"
                
                alt="logo"
                width="200px"
                height="40px"
              ></b-img>
            </router-link>
          </b-nav-text>
        </b-navbar-nav>
        <!-- 로그인 후 -->
        <b-navbar-nav v-if="userInfo" id="login-wrap">
          <b-nav-item style="margin-top:8px">
            <b-avatar variant="light" style="height:30px"></b-avatar>
            {{ userInfo.userName }}({{ userInfo.userId }})님 환영합니다!

          </b-nav-item>
          <b-nav-item><router-link :to="{ name: 'userinfo' }">내정보보기</router-link></b-nav-item>
          <b-nav-item @click.prevent="onClickLogout">로그아웃</b-nav-item>
        </b-navbar-nav>
        <!-- 로그인 전 -->
        <b-navbar-nav v-else>
          <b-nav-item><router-link to="/user">로그인</router-link></b-nav-item>
          <b-nav-item><router-link to="/user/regist">회원가입</router-link></b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <!-- <template v-if="boxColor!=my"> -->

        <div class="menu-bar-wrap">
          <div 
          :class="[boxColor === noticeType ? 'under-line' : 'no-deco']"
          class="menu-bar"
          >
              <router-link to="/notice"> 자유 게시판</router-link>
          </div>
          <div 
          :class="[boxColor === planType ? 'under-line' : 'no-deco']"
          class="menu-bar" 
          >
              <router-link to="/">플랜 게시판</router-link>
          </div>
          <div 
          :class="[boxColor === my ? 'under-line' : 'no-deco']"
          class="menu-bar"
          >
              <router-link to="/mypage">마이페이지</router-link>
          </div>
        </div>
      <!-- </template> -->
    <!-- 로그인 안된 상황 -->
    <b-sidebar
      id="sidebar-backdrop"
      title=""
      :backdrop-variant="variant"
      backdrop
      shadow
      :width="`25%`"
    >
      <b-container class="">
        <!-- 로그인 후 -->
        <b-container v-if="userInfo" >
          <b-row>
            <b-col class="align-self-center">
              <b-avatar variant="light"></b-avatar>
              {{ userInfo.userName }}({{ userInfo.userId }})님 환영합니다.
            </b-col>
          </b-row>
          <b-row></b-row>
          <b-row>
            <b-col><router-link :to="{ name: 'userinfo' }">내정보보기</router-link></b-col>
            <b-col @click.prevent="onClickLogout">로그아웃</b-col>
          </b-row>
        </b-container>
        <!-- 로그인 전 -->
        <b-container v-else>
          <b-row>
            <b-col
              ><router-link to="/user"><h5>로그인</h5></router-link></b-col
            >

            <b-col
              ><router-link to="/user/regist"><h5>회원가입</h5></router-link></b-col
            >
          </b-row>
        </b-container>
        <p></p>
        <b-row>
          <b-col><router-link to="/mypage">내 플랜</router-link></b-col>
        </b-row>
        <p></p>
        <b-row>
          <b-col> <router-link to="/plan/write">플랜 만들기</router-link></b-col>
        </b-row>
        <p></p>
        <b-row>
          <b-col><router-link to="/">플랜 게시판</router-link></b-col>
        </b-row>
        <p></p>
        
        <b-row>
          <b-col><router-link to="/notice">자유 게시판</router-link></b-col>
        </b-row>
        <p></p>
        
      </b-container>
    </b-sidebar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "AppHeader",
  components: {},
  data() {
    return {
      noticeType:"notice",
      planType:"plan",
      my:"my",
      variant: "dark",
      selected:"",
    };
  },
  props:{
    boxColor:String,
  },
  
  watch:{
    $route(){
      console.log(this.boxColor);
      // this.selected = this.boxColor;
    }
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
    
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userId);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userId).then(() => {
        sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
        sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
        // console.log(this.$route.path);
        if (this.$route.path != "/" && this.$route.path != "/plan/list") {
          this.$router.push({ name: "main" });
        } else {
          this.$router.go();
        }
      });
    },
    
  },
};
</script>

<style scoped>
li {
  list-style: none;
  padding-left: 0px;
}
.ml {
  margin-left: 22rem;
  /* margin-right:10rem; */
}
.menu-bar-wrap{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  height: 4rem;
  width: 100%;
  /* border: solid black; */
}
.menu-bar{
  display: flex;
  justify-content: center;
  align-content: center;
  height: 2rem;
  width: 10rem;
  margin-left: 2rem;
  margin-right: 2rem;
  margin-top:3rem;
  
  text-decoration: none;
}
a {
  text-decoration: none;
  color: rgb(29, 29, 29);
  size: 30pt;
  display: flex;
}
a:hover{
  text-decoration: none;
}

.under-line{
  border-bottom: 2px solid #1e56a0c1;
  
}
.no-deco{
  border-bottom: 2px solid #d9d9d9;
}
.no-deco:hover{
  border-bottom: 2px solid #1e56a0c1;

}
#login-wrap{
  display: flex;
  flex-direction: row;
  align-items: center;
  height: inherit;
}
</style>
