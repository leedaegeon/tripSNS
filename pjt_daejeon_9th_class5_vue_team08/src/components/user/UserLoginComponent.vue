<template>
  <b-container
    class="bv-example-row mt-5 rounded jumbotron"
    style="background-color: rgba(217, 217, 217, 1)"
  >
    <b-row>
      <b-col class="text-center">
        <h3>로그인</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="text-center mt-3 bg-transparent border-0"
          style="max-width: 40rem"
          align="left"
        >
          <b-form class="text-left">
            <!-- <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            > -->
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userId"
                required
                placeholder="아이디 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userPw"
                required
                placeholder="비밀번호 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>

            <b-row>
              <b-col class="text-center">
                <b-button
                  type="button"
                  style="background-color: rgba(30, 86, 160, 1)"
                  class="m-1"
                  @click="confirm"
                  >로그인</b-button
                >
                <b-button
                  type="button"
                  style="background-color: rgba(30, 86, 160, 1)"
                  class="m-1"
                  @click="movePage"
                  >회원가입</b-button
                >
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLoginComponent",
  components: {},
  data() {
    return {
      user: {
        userId: null,
        userPw: null,
      },
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      // console.log(this.user.userId + this.user.userPw + " 로그인 요청");
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        // console.log("isLogin: " + this.isLogin);
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        // this.$router.push({ name: "main" });
        this.$router.go(-1);
      }
    },
    movePage() {
      this.$router.push("/user/regist");
    },
  },
};
</script>

<style scoped></style>
