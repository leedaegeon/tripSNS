<template>
  <b-container
    class="bv-example-row mt-5 rounded jumbotron"
    style="background-color: rgba(217, 217, 217, 1)"
  >
    <b-row>
      <b-col class="text-center">
        <h3>탈퇴하기</h3>
      </b-col>
    </b-row>
    <hr class="my-4" />
    <b-row>
      <b-col></b-col>
      <b-col cols="8" align="center">
        <b-card
          class="text-center mt-3 bg-transparent border-0"
          style="max-width: 40rem"
          align="center"
        >
          <b-row>
            <b-col class="text-center">
              <b>bebetour 탈퇴 전 확인하세요.</b> <br /><br />
              <b-alert show variant="danger">
                탈퇴하시면 프로필 등 모든 정보가 삭제되며, <br />
                모든 데이터는 복구가 불가능합니다.
              </b-alert>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
    <hr class="my-5" />
    <b-row class="mt-3">
      <b-col class="text-center">
        <b-button variant="danger" @click="deleteUser">탈퇴하기</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteUser } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
  name: "UserDeleteComponent",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    deleteUser() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        deleteUser(
          this.userInfo.userId,
          ({ data }) => {
            let msg = "탈퇴 처리시 문제가 발생했습니다.";
            if (data.message === "success") {
              msg = "회원 탈퇴가 완료되었습니다.";
              this.userLogout(this.userInfo.userId);
              sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
              sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기

            }
            alert(msg);
            if (this.$route.path != "/" && this.$route.path != "/plan/list")
                this.$router.push({ name: "main" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style></style>
