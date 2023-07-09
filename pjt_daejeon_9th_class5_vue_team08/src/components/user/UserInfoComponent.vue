<template>
  <b-container
    v-if="userInfo"
    class="bv-example-row mt-5 rounded jumbotron"
    style="background-color: rgba(217, 217, 217, 1)"
  >
    <b-row>
      <b-col class="text-center">
        <h3>My Page</h3>
      </b-col>
    </b-row>
    <hr class="my-4" />
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="text-left mt-3 bg-transparent border-0"
          style="max-width: 80rem"
          align="left"
        >
          <b-container>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userId }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userName }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userEmail }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userJoinDate }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <!-- <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">사용자 타입</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userType }}</b-col>
              <b-col cols="2"></b-col>
            </b-row> -->
          </b-container>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
    <hr class="my-5" />
    <b-row class="mt-3">
      <b-col class="text-center">
        <b-button variant="primary" @click="moveModifyPage" class="mr-1">정보수정</b-button>
        <b-button variant="danger" @click="moveDeletePage">회원탈퇴</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserInfoComponent",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveModifyPage() {
      this.$router.push("/user/modify");
    },
    moveDeletePage() {
      this.$router.push("/user/delete");
    },
  },
};
</script>

<style></style>
