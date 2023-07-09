<template>
  <b-container class="bv-example-row rounded jumbotron" style="background-color: rgba(217, 217, 217, 1)">
    <b-row>

      <b-col  align="center">
        <b-card
          class="text-center bg-transparent border-0"
          style="max-width: 40rem"
          align="left"
        >
          <b-form class="text-left" @submit="onSubmit" @reset="onReset">
            <!-- <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            > -->
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                type="text"
                id="userid"
                v-model="user.userId"
                required
                placeholder="아이디 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userPw"
                required
                placeholder="비밀번호 입력"
              >
              </b-form-input>
            </b-form-group>
            <b-form-group label="이메일" label-for="useremail">
              <b-form-input
                type="email"
                id="useremail"
                v-model="user.userEmail"
                required
                placeholder="이메일 입력"
              >
              </b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="username">
              <b-form-input
                type="text"
                id="username"
                v-model="user.userName"
                required
                placeholder="이름 입력"
              >
              </b-form-input>
            </b-form-group>
            <b-form-group label="나이" label-for="userage">
              <b-form-input
                type="number"
                id="userage"
                v-model="user.userAge"
                required
                placeholder="나이 입력"
                @keyup.enter="confirm"
              >
              </b-form-input>
            </b-form-group>

            <b-row>
              <b-col class="text-center mt-5">
                <b-button
                  type="submit"
                  style="background-color: rgba(30, 86, 160, 1)"
                  class="m-1"
                  v-if="this.type === 'regist'"
                  >회원가입</b-button
                >
                <b-button
                  type="submit"
                  style="background-color: rgba(30, 86, 160, 1)"
                  class="m-1"
                  v-else
                  >회원정보수정</b-button
                >
                <b-button type="reset" variant="danger" class="m-1" v-if="this.type === 'regist'">초기화</b-button>
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { registUser, modifyUser } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "UserInputItemComponent",
  components: {},
  data() {
    return {
      user: {
        userId: null,
        userPw: null,
        userName: null,
        userEmail: null,
        userAge: null,
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    // 회원 정보 수정
    if (this.type === "modify") {
      this.user = this.userInfo;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.user.userId &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.userid.focus());
        err &&
        !this.user.userPw &&
        ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.userid.focus());
        err &&
        !this.user.userName &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.userid.focus());
        err &&
        !this.user.userEmail &&
        ((msg = "이메일을 입력해주세요"), (err = false), this.$refs.userid.focus());
        err &&
        !this.user.userAge &&
        ((msg = "나이를 입력해주세요"), (err = false), this.$refs.userid.focus());

      if (!err) alert(msg);
      else this.type === "regist" ? this.registUser() : this.modifyUser();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userId = "";
      this.user.userPw = "";
      this.user.userName = "";
      this.user.userEmail = "";
      this.user.userAge = "";
      //   this.moveList();
    },
    registUser() {
      let param = {
        userId: this.user.userId,
        userPw: this.user.userPw,
        userName: this.user.userName,
        userEmail: this.user.userEmail,
        userAge: this.user.userAge,
      };
      registUser(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyUser() {
      let param = {
        userId: this.user.userId,
        userPw: this.user.userPw,
        userName: this.user.userName,
        userEmail: this.user.userEmail,
        userAge: this.user.userAge,
      };
      modifyUser(
        param,
        ({ data }) => {
          console.log("data", data)
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped></style>
