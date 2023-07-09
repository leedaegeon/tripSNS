import { apiInstance } from "./index.js";
const api = apiInstance();

async function searchAttractions(searchObj, success) {
  await api
    .get("/attraction", {
      params: { contentTypeId:searchObj.contentTypeId, sidoCode: searchObj.sido, gugunCode: searchObj.gugun, keyword: searchObj.word, nowPage: searchObj.nowPage },
    })
    .then(success);
}
async function getAttractionDetail(contentId, success) {
  // console.log("/attraction/"+contentId);
  await api.get("/attraction/" + contentId).then(success);
}

export {searchAttractions, getAttractionDetail};
