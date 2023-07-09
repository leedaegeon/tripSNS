<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="moveList">목록</b-button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-card
            :header-html="`<h3>${article.noticeTitle}</h3>
            <div><h6>${article.writerId}</div><div>${article.registerTime} 조회 ${article.hit}</h6></div>`"
            class="mb-2"
            border-variant="white"
            no-body
          >
            <b-card-body class="text-left">
              <div v-html="message"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <b-col class="text-left" v-if="isMyNotice">
        <b-button
          style="background-color: rgba(30, 86, 160, 1)"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button style="background-color: rgba(30, 86, 160, 1)" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-container>
  </div>
</template>

<script>
import { getArticle, deleteArticle } from "@/api/notice";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "NoticeDetailComponent",
  components: {},
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.noticeContent) return this.article.noticeContent.split("\n").join("<br>");
      return "";
    },
    isMyNotice() {
      if (this.article.writerId == this.userInfo.userId) {
        return true;
      } else {
        return false;
      }
    },
  },
  created() {
    let param = this.$route.params.noticeNo;
    getArticle(
      param,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "notice" });
    },
    moveModifyArticle() {
      // this.$router.replace({
      //   name: "nmodify",
      //   params: { noticeNo: this.article.noticeNo },
      // });
      this.$router.push({ path: `/notice/modify/${this.article.noticeNo}` });
    },
    deleteArticle() {
      if (confirm("삭제하시겠습니까?")) {
        deleteArticle(
          this.article.noticeNo,
          ({ data }) => {
            let msg = "글 삭제 시 문제가 발생했습니다.";
            if (data.message === "success") {
              msg = "글 삭제가 완료되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "nlist" });
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

<style scoped></style>
