<template>
  <div>
    <!-- <b-modal id="modal-scrollable" scrollable title="Scrollable Content">
      <p class="my-4" v-for="i in 20" :key="i">
        test
      </p>
    </b-modal> -->

    <div class="top">
      <div class="search">
        <!-- <input class="input" v-model="word" @keydown.enter=""/> -->
        <select class="sidogugun" v-model="sido">
          <option value="null">시,도</option>
          <option v-for="item in sidos" :key="item.sidoCode" v-bind:value="item.sidoCode">
            {{ item.sidoName }}
          </option>
        </select>

        <select class="sidogugun" v-model="gugun">
          <option value="null">구,군</option>

          <option v-for="item in guguns" :key="item.gugunCode" v-bind:value="item.gugunCode">
            {{ item.gugunName }}
          </option>
        </select>

        <select class="sidogugun" v-model="type" style="width: 7rem">
          <option value="null">카테고리</option>
          <option v-for="item in types" :key="item.contentTypeId" v-bind:value="item.contentTypeId">
            {{ item.desc }}
          </option>
        </select>

        <input class="input" v-model="word" placeholder="검색어 입력" @keypress.enter="search" />

        <div class="imageBtnbox">
          <input type="button" class="imageBtn" @click="search" />
        </div>
      </div>
      <!-- 검색된 목록 -->
      <div class="wrap">
        <div class="comp side">
          <div
            class="attr compwrap"
            v-for="(item, idx) in attractions"
            :key="idx"
            @click="getLocation(item)"
          >
            <b-img-lazy
              v-bind="mainProps"
              class="vertsep-left"
              :src="item.smallImage"
              rounded
              alt=""
            ></b-img-lazy>
            <div class="vertsep-right">
              <div class="scroll">
                <div class="horisep">{{ item.title }}</div>
                <hr style="margin: 0; border-top: 1px solid rgba(0, 0, 0, 0.4)" />
                <div class="horisep">{{ item.addr1 }}</div>
              </div>
              <div class="align-right">
                <button class="select-btn" @click="selectAttraction(item, idx)">선택</button>
                <button class="detail-btn" @click="showDetailOnModal(item)">상세</button>
              </div>
            </div>
          </div>
          <infinite-loading
            @infinite="infiniteHandler"
            spinner="waveDots"
            :identifier="infiniteId"
          ></infinite-loading>
        </div>

        <div class="comp center">
          <div id="map" class="" style="width: 100%; height: 1000rem"></div>
        </div>
        <div class="content">
          <draggable :list="selected" class="comp side" id="plan">
            <div class="attr compwrap" v-for="(item, idx) in selected" :key="idx">
              <!-- {{item}} -->
              <b-img-lazy
                v-bind="mainProps"
                class="vertsep-left"
                :src="item.smallImage"
                rounded
                alt=""
              ></b-img-lazy>

              <div class="vertsep-right">
                <div class="scroll">
                  <div class="horisep">{{ item.title }}</div>
                  <hr style="margin: 0; border-top: 1px solid rgba(0, 0, 0, 0.4)" />
                  <div class="horisep">{{ item.addr1 }}</div>
                </div>
                <div class="align-right">
                  <button class="select-btn" @click="showDetailOnModal(item)">상세</button>
                  <button class="detail-btn" @click="removeAttraction(item, idx)">삭제</button>
                </div>
              </div>
            </div>
          </draggable>
          <div class="small-box">
            <b-input
              v-model="planTitle"
              type="text"
              style="border-radius: 20px; border: 2px solid #d9d9d9"
              placeholder="제목을 입력해주세요"
            ></b-input>
            <b-form-textarea
              class="text"
              placeholder="간단한 설명을 적어주세요"
              rows="4"
              max-rows="5"
              v-model="content"
            ></b-form-textarea>
            <button
              class="detail-btn"
              style="margin-right: 1rem; height: 2rem"
              v-show="this.inputType === 'regist'"
              @click="planWrite"
            >
              글등록
            </button>
            <button
              class="detail-btn"
              style="margin-right: 1rem; height: 2rem"
              v-show="this.inputType === 'modify'"
              @click="planModify"
            >
              글수정
            </button>
            <!-- <button class="detail-btn" style="margin-right:1rem; height:2rem;" >글등록</button> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { sidoList, gugunList } from "@/api/item/item";
import { searchAttractions, getAttractionDetail } from "@/api/attraction";
import draggable from "vuedraggable";
import InfiniteLoading from "vue-infinite-loading";
import { mapState } from "vuex";
import { planDetail, planWrite, planModify } from "@/api/plan";

const memberStore = "memberStore";

export default {
  name: "PlanInputItemComponent",
  components: { draggable, InfiniteLoading },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    inputType: { type: String },
  },
  data() {
    return {
      default: 0,
      markers: [],
      ps: {},
      map: {},
      placeOverlay: {},
      contentNode: ``,
      id: "",
      word: "",
      searchObj: {},
      nowPage: 0,
      sidos: [],
      guguns: [],
      sido: "",
      gugun: "",
      type: "",
      types: [
        {
          contentTypeId: 12,
          desc: "관광지",
        },
        {
          contentTypeId: 14,
          desc: "문화시설",
        },
        {
          contentTypeId: 15,
          desc: "축제공연행사",
        },
        {
          contentTypeId: 25,
          desc: "여행코스",
        },
        {
          contentTypeId: 28,
          desc: "레포츠",
        },
        {
          contentTypeId: 32,
          desc: "숙박",
        },
        {
          contentTypeId: 38,
          desc: "쇼핑",
        },
        {
          contentTypeId: 39,
          desc: "음식점",
        },
      ],
      attractions: [],
      attrDesc: "",
      mainProps: { blank: true, width: 100, height: 70, class: "m1", rounded: "Rounded" },
      selected: [],
      tempMarkers: [],
      infiniteId: +new Date(),
      content: "",
      planTitle: "",
      planId: "",
    };
  },
  created() {
    sidoList(({ data }) => {
      this.sidos = [];
      console.log(data);
      this.sidos.push(...data);
    });

    // 플랜 수정할 때 기존 정보 불러오기
    if (this.inputType === "modify") {
      let param = this.$route.params.planId;
      console.log(param, "번 플랜 수정###############");
      // 플랜 데이터 불러오기
      planDetail(param, ({ data }) => {
        console.log("서버에서 받아온 플랜 DTO");
        console.log(data);

        this.planTitle = data.planTitle;
        this.content = data.planContent;
        this.planId = data.planId;
        this.selected = data.attrInfos;
      });
    }
  },
  watch: {
    sido() {
      gugunList(this.sido, ({ data }) => {
        this.guguns = [];
        console.log(data);
        this.guguns.push(...data);
      });
      this.gugun = "";
    },
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=3dbd83920f74ea758eb66e3a000277c3&libraries=services,clusterer,drawing&autoload=false";
      script.onload = () => window.kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    }
  },

  methods: {
    planWrite() {
      let contentIds = [];
      console.log(this.selected[0].contentId);
      for (let i = 0; i < this.selected.length; i++) {
        contentIds.push({ contentId: this.selected[i].contentId });
      }
      let planBoard = {
        planTitle: this.planTitle,
        planContent: this.content,
        userId: this.userInfo.userId,
        plans: contentIds,
      };
      console.log(planBoard);
      planWrite(planBoard, () => {
        alert("플랜 등록 완료");
        this.$router.push({ path: "/plan" });
      });
    },
    planModify() {
      let contentIds = [];
      console.log(this.selected[0].contentId);
      for (let i = 0; i < this.selected.length; i++) {
        contentIds.push({ contentId: this.selected[i].contentId });
      }
      let planBoard = {
        planId: this.planId,
        planTitle: this.planTitle,
        planContent: this.content,
        userId: this.userInfo.userId,
        plans: contentIds,
      };
      console.log(planBoard);
      planModify(planBoard, () => {
        alert("플랜 수정 완료");
        this.$router.push({ path: "/plan" });
      });
    },

    getLocation(item) {
      if (this.tempMarkers.length > 0) {
        for (let i = 0; i < this.tempMarkers.length; i++) {
          this.tempMarkers[i].setMap(null);
          this.tempMarkers.splice(i, 1);
        }
      }
      let location = new window.kakao.maps.LatLng(item.latitude, item.longitude);
      this.map.setCenter(location);

      this.searchPlaces(item, false);
    },

    removeAttraction(item, idx) {
      this.selected.splice(idx, 1);
      this.attractions.unshift(item);
      //마커 삭제
      this.markers[idx].setMap(null);
      this.markers.splice(idx, 1);
    },
    selectAttraction(item, idx) {
      console.log(item);
      this.selected.push(item);
      this.attractions.splice(idx, 1);
      // 선택한 장소로 지도 이동
      let location = new window.kakao.maps.LatLng(item.latitude, item.longitude);
      this.map.setCenter(location);

      this.searchPlaces(item, true);
    },

    //infinite scroll 초기화부분
    refreshInfiniteLoading() {
      this.infiniteId += 1;
      this.nowPage = 0;
      this.attractions = [];
    },
    async search() {
      this.refreshInfiniteLoading();

      this.searchObj = {
        contentTypeId: this.type,
        sido: this.sido,
        gugun: this.gugun,
        word: this.word,
        nowPage: this.nowPage,
      };
      await searchAttractions(this.searchObj, ({ data }) => {
        // console.log(data);
        this.nowPage += 9;
        if (data.length > 0) {
          this.attractions = data;
          let location = new window.kakao.maps.LatLng(
            this.attractions[0].latitude,
            this.attractions[0].longitude
          );
          this.map.setCenter(location);
        } else {
          alert("검색된 데이터가 없습니다.");
        }
      });
    },
    async infiniteHandler($state) {
      console.log(this.nowPage);
      this.searchObj = {
        contentTypeId: this.type,
        sido: this.sido,
        gugun: this.gugun,
        word: this.word,
        nowPage: this.nowPage,
      };
      await searchAttractions(this.searchObj, ({ data }) => {
        console.log(data);
        if (data.length > 0) {
          this.nowPage += 9;
          this.attractions.push(...data);
          $state.loaded();
        } else {
          $state.complete();
        }
      });
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

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new window.kakao.maps.Map(container, options);
      this.placeOverlay = new window.kakao.maps.CustomOverlay({ zIndex: 1 });

      this.contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다

      this.ps = new window.kakao.maps.services.Places(this.map);
      // 지도에 idle 이벤트를 등록합니다
      // window.kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = "placeinfo_wrap";
      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(this.contentNode, "mousedown", window.kakao.maps.event.preventMap);
      this.addEventHandle(this.contentNode, "touchstart", window.kakao.maps.event.preventMap);
      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);
    },

    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다 ok
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        // target.classList.add("once");
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces(item, flag) {
      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      this.displayPlaces(item, flag);
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(item, flag) {
      this.addMarker(new window.kakao.maps.LatLng(item.latitude, item.longitude), item, flag);
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, place, flag) {
      var imageSrc = "https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png",
        // var imageSrc = "@/assets/location.png",
        imageSize = new window.kakao.maps.Size(31, 35),
        imageOption = { offset: new window.kakao.maps.Point(11, 28) };
      var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        marker = new window.kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      window.kakao.maps.event.addListener(marker, "click", () => {
        this.displayPlaceInfo(place);
      });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      if (flag) {
        this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      } else {
        this.tempMarkers.push(marker);
      }
      return marker;
    },
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        "링크 걸 수 있음" +
        '" target="_blank" title="' +
        place.title +
        '">' +
        place.title +
        "</a>";

      if (place.addr1) {
        content += '    <span title="' + place.addr1 + '">' + place.addr1 + "</span>";
      }
      // console.log("display: ",place);

      this.contentNode.innerHTML = content;

      // console.log(this.contentNode);
      this.placeOverlay.setPosition(new window.kakao.maps.LatLng(place.latitude, place.longitude));
      this.placeOverlay.setMap(this.map);
    },
  },
};
</script>

<style>
.top {
  margin-top: 2rem;
}

.select-btn {
  border-radius: 15px;
  border: 2px solid #1e56a0;
  background-color: #ffffff;
  color: dimgrey;
  margin: 3px;
}
.detail-btn {
  border-radius: 15px;
  /* border: 2px solid dimgrey; */
  border: none;
  background-color: dimgray;
  color: #ffffff;
  margin: 3px;
}
input.imageBtn {
  background: url("@/assets/search.png") no-repeat;
  border: none;
  width: 50px;
  height: 40px;
  box-shadow: #d9d9d9;
}
.imageBtnbox {
  border-radius: 20px;
  border: 3px solid #1e56a0;
  margin: 1rem;
  height: 3rem;
  width: 5rem;
  padding-left: 23px;
  padding-top: 7px;
}
.addr {
  font-weight: bold;
}
.wrap .compwrap {
  border: 2px solid #d9d9d9;

  display: flex;
  width: 100%;
  height: inherit;
  margin-top: 10px;
  overflow: hidden;
  align-items: center;
  /* align-content: center; */
}

.wrap .compwrap .vertsep-left {
  display: inline;
  width: 100px;
  height: 70px;
  margin-left: 15px;
  margin-right: 20px;
  margin-top: 18px;

  /* overflow:scroll; */
}
.wrap .compwrap .vertsep-right {
  display: inline;
  width: 280px;
  height: 100px;
  margin-left: 10px;
  margin-bottom: 5px;
}
.wrap .compwrap .vertsep-right .align-right {
  margin-left: 150px;
}
.wrap .compwrap .vertsep-right .scroll {
  width: 240px;
  height: 70px;
  overflow: scroll;
  margin-bottom: 5px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}

.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}

.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}

.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}

.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: rgb(123, 185, 255);
  background: rgb(123, 185, 255)
    url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png") no-repeat right
    14px center;
  display: flex;
  overflow: wrap;
}

.placeinfo .tel {
  color: #0f7833;
}

.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
.comp {
  display: flex;
  flex-direction: column;
}
.side {
  border-radius: 20px;
  border: 2px solid #d9d9d9;
  padding: 27px;

  width: 30rem;
  margin: 1rem;
  height: 60rem;

  overflow: scroll;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: baseline;
}

.center {
  width: 50rem;
  height: 60rem;

  margin: 1rem;
}
#map {
  border-radius: 20px;
  z-index: "inherit";
}

.wrap {
  display: flex;
  justify-content: center;
  flex-direction: row;
}
.search {
  display: flex;
  justify-content: center;
  margin-bottom: 1rem;
  flex-direction: row;
}
.search .sidogugun {
  border-radius: 20px;
  border: 2px solid #1e56a0;
  margin: 1rem;
  height: 3rem;
  width: 8rem;
  padding: 7px;
}
.search .input {
  border-radius: 20px;
  border: 2px solid #1e56a0;
  margin: 1rem;
  height: 3rem;
  padding: 10px;
  width: 30rem;
}
.side .attr {
  border-radius: 20px;
  border: 2px solid #d9d9d9;
  /* width: 100rem; */
  padding: 10px;
  height: 8rem;
}
.search .side {
  border-radius: 20px;
  /* background: #d9d9d9; */
  border: 2px solid #d9d9d9;
  width: 25rem;
  height: 3rem;
  margin: 1rem;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  align-content: center;
}
#plan {
  height: 45rem;
}
.wrap .content {
  width: 35rem;
  height: 60rem;
  /* border:solid black; */
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  border: none;
  margin: none;
  padding: none;
}
.small-box {
  display: flex;
  width: 30rem;
  height: 15rem;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: right;
  margin: 1rem;
}
.text {
  border: 2px solid #d9d9d9;
  width: 30rem;

  border-radius: 20px;
}
</style>
