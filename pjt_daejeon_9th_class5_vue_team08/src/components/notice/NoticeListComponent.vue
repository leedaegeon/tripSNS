<template>
  <div>
    <b-container class="bv-example-row mt-3" id="cont">
      <!-- <b-alert show><h3>재난 지역 알림</h3></b-alert> -->
      <b-row class="mb-1">
        <!-- <b-col class="text-left"> 총 {{ rows }}건의 글이 있습니다. </b-col> -->
        <b-col class="text-right"> </b-col>
      </b-row>

      <b-row>
        <b-col>
          <b-table
            hover
            id="my-table"
            :items="articles"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="fields"
            @row-clicked="viewArticle"
          >
            <template #cell(subject)="data">
              <router-link :to="{ name: 'ndetail', params: { noticeNo: data.item.noticeNo } }">
                {{ data.item.noticeTitle }}
              </router-link>
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>
    <div class="btn-wrap">
      <b-button class="write-btn" @click="moveWrite()">글쓰기</b-button>
    </div>
    <div class="btn-wrap" id="center">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </div>
    <!-- <b-table
      id="my-table"
      :items="articles"
      :per-page="perPage"
      :current-page="currentPage"
      small
    ></b-table> -->
  </div>
</template>

<script>
import { listArticle } from "@/api/notice";
export default {
  name: "NoticeListComponent",
  components: {},
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      articles: [],
      fields: [
        { key: "noticeNo", label: "글번호", tdClass: "tdClass" },
        { key: "noticeTitle", label: "제목", tdClass: "tdSubject" },
        { key: "writerId", label: "작성자", tdClass: "tdClass" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      ({ data }) => {
        this.articles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "nwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "ndetail",
        params: { noticeNo: article.noticeNo },
      });
    },
  },
};
</script>

<style scoped>
#cont {
  height: 20rem;
}
#center {
  justify-content: center;
}
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.write-btn {
  background-color: #ffffff;
  color: #3548da;
  border-color: #3548da;
  margin-bottom: 7px;
  border-radius: 15px;
  display: flex;
}
.write-btn:hover {
  background-color: #3548da;
  color: #ffffff;
}
.btn-wrap {
  display: flex;
  width: inherit;
  height: 3rem;
  justify-content: flex-end;
  margin-right: 1rem;
}
</style>
