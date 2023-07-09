<template>
  <div>
    <!-- <b-nav pills justified align="center" id="outer">
      <b-nav-item :active="val1" @click="myplan"
        ><router-link to="/mypage/myplan" style="color: rgba(61, 59, 59, 1)"
          >작성플랜</router-link
        ></b-nav-item
      >
      <b-nav-item :active="val2" @click="mycomment"
        ><router-link to="/mypage/mycomment" style="color: rgba(61, 59, 59, 1)"
          >댓글 단 플랜</router-link
        ></b-nav-item
      >
      <b-nav-item :active="val3" @click="mywishlist"
        ><router-link to="/mypage/mywishlist" style="color: rgba(61, 59, 59, 1)"
          >찜 목록</router-link
        ></b-nav-item
      >
    </b-nav> -->

    <div class="menu-bar-wrap">
        <router-link to="/mypage/myplan" 
        tag="button"
        :class="[btnColor === write ? 'under-line' : 'no-deco']"
        class="menu-bar"
        >
        내가 작성한 플랜
        </router-link>
        <router-link to="/mypage/mycomment" 
        tag="button"
        :class="[btnColor === comment ? 'under-line' : 'no-deco']"
        class="menu-bar"
        >
        댓글을 작성한 플랜
        </router-link>
        <router-link to="/mypage/mywishlist" 
        tag="button"
        :class="[btnColor === like ? 'under-line' : 'no-deco']"
        class="menu-bar"
        >
        찜 목록
        </router-link>

    </div>

  </div>
</template>

<script>
export default {
  name: "MyPageHeader",
  components: {},
  data() {
    return {
      write:"write",
      comment:"comment",
      like:"like",
    };
  },
  props:{
    btnColor:String,
  },
  created() {},
  methods: {
   
  },
};
</script>

<style scoped>
.active {
  color: red;
  font-weight: bool;
}
.menu-bar-wrap{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  height: 4rem;
  width: 100%;
  margin-top: 3rem;
  /* border: solid black; */
}
.menu-bar{
  display: flex;
  align-items: center;
  justify-content: center;
  
  height: 3rem;
  width: 20rem;
  margin-left: 2rem;
  margin-right: 2rem;
  margin-top:1rem;
  font-size: 16pt;
  text-decoration: none;
  background-color: #ffffff;
  color: #1e56a0c1;
  border-radius: 15px;
}
.under-line{
  background-color: #1e56a0c1;
  color: #ffffff;
  border:none;

}
button:hover{
  background-color: #1e56a0c1;
  color: #ffffff;
  border:none;
}
.no-deco{
  background-color: #ffffff;
  border:2px solid #1e56a0c1;
  

}
</style>
