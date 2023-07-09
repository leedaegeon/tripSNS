<template>
  <div class="outer-box">
    <!-- 전체박스 -->

    <div class="inner-box">
      <div
        v-for="(article, idx) in list"
        :key="idx"
        :class="[moreContent === article.planId ? 'card-box-extension' : 'card-box']"
        @click="selected(article.planId)"
        :id="idx"
      >
        <div :class="[moreContent === article.planId ? 'top-area-extension' : 'top-area']">
          <div style="display: flex; flex-direction:row width:inherit;">
            <p style="display: flex; width: 15rem">
              {{ article.planTitle }}
            </p>
            <button
              v-on:click="likeToggle(article.planId, article.wish)"
              style="margin-left: 10rem; border: none; background-color: #ffffff"
            >
              <font-awesome-icon icon="fa-solid fa-heart fa-3x" v-if="article.wish" />
              <font-awesome-icon icon="fa-regular fa-heart fa-3x" v-else />
            </button>
            <!-- start 게시글수정,삭제-->
            <b-dropdown
              variant="Outline"
              size="small"
              v-show="userInfo != null && article.userId == userInfo.userId"
            >
              <b-dropdown-item @click="modifyPlan(article.planId)"> 수정 </b-dropdown-item>
              <b-dropdown-item @click="removePlan(article.planId)"> 삭제 </b-dropdown-item>
            </b-dropdown>
            <!-- end 게시글 수정, 삭제 -->
          </div>

          <hr style="margin: 0; margin-bottom: 1rem; border-top: 1px solid rgba(0, 0, 0, 0.4)" />

          <div
            :class="[
              moreContent === article.planId ? 'article-content-extension' : 'article-content',
            ]"
          >
            {{ article.planContent }}
          </div>
        </div>

        <div :class="[moreContent === article.planId ? 'content-box-extension' : 'content-box']">
          <div
            v-for="(item, index) in article.attrInfos"
            :key="index"
            class="wrap"
            @click="showDetailOnModal(item)"
          >
            <b-img-lazy class="vertsep-left" :src="item.smallImage" rounded alt=""></b-img-lazy>
            <div class="vertsep-right">
              <div class="scroll">
                <div class="horisep">{{ item.title }}</div>
                <hr style="margin: 0; border-top: 1px solid rgba(0, 0, 0, 0.4)" />
                <div class="horisep">{{ item.addr1 }}</div>
              </div>
            </div>
          </div>
        </div>
        <div :class="[moreContent === article.planId ? 'comment-wrap-extension' : 'comment-wrap']">
          <div
            :class="[
              moreContent === article.planId ? 'comment-content-extension' : 'comment-content',
            ]"
            id="each-comment"
            v-for="(comment, key) in article.comments"
            :key="key"
          >
            <div class="top">
              <div class="commentor-id">{{ comment.userId }}</div>
              <div class="comment-time">{{ comment.commentDate }}</div>
            </div>

            <div class="vertical">
              <div class="each-comment-content">{{ comment.commentContent }}</div>
              <!-- <div class="modify-menu" @click="modifyMenu(idx, (list.length+key), article.comments.length)" >...</div> -->

              <b-dropdown
                variant="Outline"
                size="small"
                :class="comment.userId"
                v-show="userInfo != null && comment.userId == userInfo.userId"
              >
                <b-dropdown-item @click="modify(comment.commentContent, comment.commentNo)">
                  수정
                </b-dropdown-item>
                <b-dropdown-item @click="rmComment(comment.commentNo)"> 삭제 </b-dropdown-item>
              </b-dropdown>
            </div>
          </div>
        </div>
        <div v-show="moreContent === article.planId" class="comment-input-area">
          <input
            v-model="newComment"
            class="comment-input"
            :id="idx"
            @keydown.enter="commentWrite(article.planId, idx)"
          />
          <button class="comment-btn" @click="commentWrite(article.planId, idx)">등록</button>
        </div>
        <div class="others">
          <div class="sub-info">
            <span style="margin-left: 1rem">{{ article.planDate }}</span>
            <span class="separator"></span>
          </div>
          <div class="sep">
            <a class="userinfo">{{ article.userId }}</a>
            <div class="likes">좋아요 {{ article.likeCnt }}</div>
          </div>
        </div>
      </div>

      <b-modal id="modal-scrollable" title="댓글 수정" @ok="modComment">
        <b-textarea v-model="modCommentText" class="mod-input">{{ modCommentText }}</b-textarea>
      </b-modal>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { getAttractionDetail } from "@/api/attraction";

const memberStore = "memberStore";

export default {
  name: "PlanListItemComponent",
  components: {},
  // components: {},
  props: {
    list: Array,
    renderKey: Number,
  },
  data() {
    return {
      planId: "",
      moreContent: "",
      newComment: "",
      modCommentText: "",
      modCommentNo: "",
      attrDesc: "",
    };
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
    // isMyComment(){

    // }
  },
  //댓글 실시간 반영에 시도했던건데 혹시나 해서

  methods: {
   

    selected(planId) {
      this.moreContent = planId;
    },

    likeToggle(planId, isWish) {
      if (this.userInfo === null) {
        var result = confirm(
          "로그인해야 찜하기 기능을 사용할 수 있습니다.\n로그인 페이지로 이동하시겠습니까?"
        );
        // next({ name: "login" });
        if (result) this.$router.push({ name: "login" });
      } else {
        let userId = this.userInfo.userId;
        let param = {
          planId: planId,
          userId: userId,
        };
        this.$emit("likeToggle", isWish, param);
      }
    },
    commentWrite(planId, idx) {
      if (this.userInfo === null) {
        var result = confirm(
          "로그인해야 댓글 작성을 할 수 있습니다.\n로그인 페이지로 이동하시겠습니까?"
        );
        // next({ name: "login" });
        if (result) this.$router.push({ name: "login" });
      } else {
        let comment = {
          userId: this.userInfo.userId,
          commentContent: this.newComment,
          planId: planId,
          index: idx,
        };
        this.newComment = "";
        this.$emit("commentWrite", comment);
      }
    },
    modify(commentContent, commentNo) {
      this.modCommentText = commentContent;
      this.modCommentNo = commentNo;
      // console.log(this.modCommentNo);
      this.$bvModal.show("modal-scrollable");
    },
    modComment() {
      // console.log(this.modCommentText);
      let commentObj = {
        commentContent: this.modCommentText,
        commentNo: this.modCommentNo,
      };
      // console.log(commentObj);
      this.$emit("modComment", commentObj);
      // modifyComment(commentObj, alert("수정완료"));
    },
    rmComment(commentNo) {
      let result = confirm("정말로 삭제 하시겠습니까?");
      if (result) {
        this.$emit("rmComment", commentNo);
      }
      // removeComment(commentNo, alert("삭제 완료됐습니다."));
    },
    modifyPlan(planId) {
      console.log(planId, "플랜 수정");
      this.$router.push({ path: `/plan/modify/${planId}` });
    },
    removePlan(planId) {
      console.log(planId, "플랜 삭제");
      let result = confirm("정말로 삭제 하시겠습니까?");
      if (result) {
        this.$emit("removePlan", planId);
      }
    },

    async showDetailOnModal(attraction) {
      // console.log(attraction);
      // 비동기통신으로 attraction_description에서 설명 얻어오기
      await getAttractionDetail(attraction.contentId, ({ data }) => {
        this.attrDesc = data;
      });
      // console.log(this.attrDesc);
      const h = this.$createElement;
      // Using HTML string
      const titleVNode = h("div", { domProps: { innerHTML: attraction.title } });
      // More complex structure
      const messageVNode = h("div", { class: ["foobar"] }, [
        h("p", { class: ["addr"] }, ["주소:", attraction.addr1]),
        h("p", { class: [""] }, [this.attrDesc]),

        h("b-img", {
          props: {
            src: attraction.image,
            thumbnail: true,
            // center: true,
            fluid: true,
            size: "sm",
          },
        }),
      ]);
      // We must pass the generated VNodes as arrays
      this.$bvModal.msgBoxOk([messageVNode], {
        title: [titleVNode],
        buttonSize: "sm",
        centered: true,
        size: "lg",
        scrollable: true,
      });
    },
  },
};
</script>

<style scoped>
.mod-input {
  border-radius: 8px;
}
.modify-menu {
  display: flex;
  z-index: 7;
}
.vertical {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
#each-comment {
  display: flex;
  flex-direction: column;

  width: 30rem;
  padding: 1rem;
}
.commentor-id {
  display: flex;
}
.comment-time {
  display: flex;
  /* margin-left: auto; */
}
.top {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 3px;
}
.each-comment-content {
  display: flex;
  padding-bottom: 10px;
}
.comment-content {
  width: 32rem;
  height: 15rem;
  padding: 5px;
  justify-content: first baseline;
}
.comment-content-extension {
  width: 32rem;
  /* height: 35rem; */
  overflow: scroll;
  padding: 5px;
}

.comment-input-area {
  width: 32rem;
  height: 4rem;
  padding: 1rem;
  margin-bottom: auto;
  display: flex;
}
.comment-input {
  width: 28rem;
  border-radius: 10px;
  margin-right: 1rem;
  border: solid #d9d9d9;
}
.comment-input-disabled {
  width: 28rem;
  border-radius: 10px;
  margin-right: 1rem;
  border: solid #d9d9d9;
}
.comment-btn {
  width: 4rem;
  border-radius: 10px;
  border: solid #1e56a0;
  background-color: #ffffff;
}
.sticky {
  position: fixed;
  margin-left: 80%;
  /* margin-bottom: */
  z-index: 7;
}
.row {
  display: flex;
  flex-direction: column;
  /* align-content: center; */
}
.outer-box {
  padding-top: 2rem;
  padding-bottom: 1rem;
  z-index: 5;
  /* position: relative; */
  padding-top: 4rem;
  background: var(--bg-page1);
  display: flex;

  justify-content: center;
  /* width:100rem; */
  /* box-shadow: rgba(0, 0, 0, 0.04) 0px -16px 16px; */
}
.outer-title {
  font-size: 1.25rem;
  margin-bottom: 2rem;
  /* text-align: center; */
  font-weight: 400;
  color: var(--text2);
  /* margin-left: 50rem; */
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}

.inner-box {
  padding-left: 1rem;
  padding-right: 1rem;
  padding-bottom: 3rem;
  margin-left: 10rem;
  margin-right: 10rem;
  margin-left: 4rem;
  margin-right: 4rem;
  margin-bottom: 4rem;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  max-height: 20%;
  justify-content: center;
}

.card-box {
  height: 40rem;
  width: 32rem;
  background: var(--bg-element1);
  border-radius: 20px;
  border: 2px solid #d9d9d9;
  box-shadow: rgba(0, 0, 0, 0.04) 0px 4px 16px 0px;
  transition: box-shadow 0.25s ease-in 0s, transform 0.25s ease-in 0s;
  margin: 1rem;
  /* overflow: scroll; */
  display: flex;

  flex-direction: column;
}
.card-box-extension {
  /* height: 50rem; */
  width: 32rem;
  background: var(--bg-element1);
  border-radius: 20px;
  border: 2px solid #d9d9d9;
  box-shadow: rgba(0, 0, 0, 0.04) 0px 4px 16px 0px;
  transition: box-shadow 0.25s ease-in 0s, transform 0.25s ease-in 0s;
  margin: 1rem;
  /* overflow: scroll; */
  display: flex;

  flex-direction: column;
}
.content-box {
  width: 32rem;
  height: 20rem;
  flex-wrap: wrap;
  overflow: scroll;
  padding: 1rem;
  display: flex;
  flex-direction: row;
}
.content-box-extension {
  width: 32rem;
  /* height: 20rem; */
  flex-wrap: wrap;
  /* overflow: scroll; */
  padding: 1rem;
  display: flex;
  flex-direction: row;
}
.content {
  display: block;
  color: inherit;
  text-decoration: none;
}
.content-box .sub-info {
  /* display: block; */
  font-size: 0.75rem;
  line-height: 1.5;
  color: var(--text3);
  display: flex;
}
.content-box .sub-info .separator {
  margin-left: 0.25rem;
  margin-right: 0.25rem;
}
.comment-wrap {
  width: 32rem;
  height: 8rem;

  display: flex;
  flex-wrap: wrap;
  overflow: hidden;
  justify-content: center;
  align-content: flex-start;
}
.comment-wrap-extension {
  width: 32rem;
  height: 20rem;
  display: flex;
  flex-wrap: wrap;
  overflow-y: scroll;
  justify-content: center;
}

.others {
  margin-bottom: auto;
  padding: 0.625rem 1rem;
  border-top: 1px solid var(--border4);
  display: flex;
  font-size: 0.75rem;
  line-height: 1.5;
  -webkit-box-pack: justify;
  flex-direction: column;
}

.sep {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 0.625rem 1rem;
}
.others .sep .userinfo {
  text-decoration: none;
  color: inherit;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}
.others .sep .likes {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}
.content-box a.content {
  /* margin: inherit; */
  width: inherit;
  padding-right: 2rem;
  /* display: flex; */
  flex-wrap: wrap;
  flex-direction: column;
}
* {
  box-sizing: inherit;
}
.wrap {
  width: 30rem;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  border: 1px solid #d9d9d9;
  border-radius: 20px;
  margin-bottom: 8px;
}
.vertsep-left {
  display: inline;
  width: 100px;
  height: 70px;
  margin-left: 15px;
  margin-right: 20px;
}
.vertsep-right {
  display: inline;
  width: 280px;
  height: 100px;
  margin-left: 10px;
  margin-bottom: 5px;
  margin-top: 5px;
}
.top-area {
  display: flex;
  flex-direction: column;
  width: inherit;
  height: 8rem;
  padding-right: 4px;
}
.top-area-extension {
  display: flex;
  flex-direction: column;
  width: inherit;
  height: 12rem;
  padding-right: 4px;
}
.top-area p {
  font-size: 16pt;
  font-weight: bold;
  margin-left: 2rem;
  margin-top: 1rem;
}
.top-area-extension p {
  font-size: 16pt;
  font-weight: bold;
  margin-left: 2rem;
  margin-top: 1rem;
}
.article-content {
  margin-left: 2rem;
  font-size: 15pt;
  height: 10rem;
  overflow-y: hidden;
  display: flex;
  flex-wrap: wrap;
}
.article-content-extension {
  margin-left: 2rem;
  font-size: 15pt;
  height: 20rem;
  overflow-y: hidden;
  display: flex;
  flex-wrap: wrap;
}
</style>
