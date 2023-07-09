<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="subject-group" label="제목" label-for="subject">
          <b-form-input
            id="subject"
            v-model="article.noticeTitle"
            type="text"
            required
            placeholder="제목을 입력하세요."
            style="border-color: rgba(30, 86, 160, 1)"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.noticeContent"
            placeholder="내용을 입력하세요."
            style="border-color: rgba(30, 86, 160, 1)"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <div class="btn-wrap">
          <b-button type="submit" class="write-btn" v-if="this.type === 'regist'"
            >글작성</b-button
          >
          <b-button type="submit" variant="primary" class="write-btn" v-else>글수정</b-button>
          <b-button type="reset" variant="danger" class="reset-btn">초기화</b-button>
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/notice";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "NoticeInputItemComponent",
  data() {
    return {
      article: {
        noticeNo: 0,
        writerId: "",
        noticeTitle: "",
        noticeContent: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.noticeNo;
      // console.log(param,"번 글 수정")
       getArticle(
        param,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      !this.article.noticeTitle &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.noticeTitle.focus());
      err &&
        !this.article.noticeContent &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.noticeContent.focus());

      if (!err) alert(msg);
      else this.type === "regist" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.noticeTitle = "";
      this.article.noticeContent = "";
      // this.moveList();
    },
   registArticle() {
      let param = {
        writerId: this.userInfo.userId,
        noticeTitle: this.article.noticeTitle,
        noticeContent: this.article.noticeContent,
      };
      // console.log("regist ", param);
      writeArticle(
        param,
        ({ data }) => {
          // console.log(data);
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
    modifyArticle() {
      let param = {
        noticeNo: this.article.noticeNo,
        noticeTitle: this.article.noticeTitle,
        noticeContent: this.article.noticeContent,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "notice" });
    },
  },
};
</script>

<style scoped>
.write-btn{
  background-color:#ffffff; 
  color:#3548da;
  margin-bottom: 7px;
  border-radius: 15px;
  border: solid 2px #3548da;
  display: flex;
  font-weight: bold;
  align-items: center;
  margin-right: 4px;
}
.write-btn:hover{
  background-color:#3548da; 
  color:#ffffff;
}
.reset-btn{
  background-color:#ffffff; 
  color:#c63131;
  margin-bottom: 7px;
  border-radius: 15px;
  border: solid 2px #c63131;
  display: flex;
  font-weight: bold;
  align-items: center;
}
.reset-btn:hover{

  background-color:#c63131; 
  color:#ffffff;
}
.btn-wrap{
  display: flex;
  width:inherit;
  height: 3rem;
  justify-content: flex-end;
  
}

</style>
