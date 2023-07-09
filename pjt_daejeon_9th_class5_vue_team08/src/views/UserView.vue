<template>
  <div>
    <!-- <router-link to="/user">로그인</router-link> |
    <router-link to="/user/regist">회원가입</router-link> |
    <router-link to="/user/modify">회원정보 수정</router-link> |
    <router-link to="/user/list">회원 리스트(관리자모드)</router-link> | -->
    <router-view @login="login" @modify-user="modifyUser" @delete-user="deleteUser" />
  </div>
</template>

<script>
export default {
  name: "UserView",
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {
    login() {
      this.$router.push("/user");
    },
    modifyUser() {
      this.$router.push("/user/modify");
    },
    deleteUser() {
      this.$router.push("/user/delete");
    },
  },
};
</script>

<style scoped></style>
