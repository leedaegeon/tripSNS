import { apiInstance } from "../index.js";

const api = apiInstance();

function sidoList(success) {
  api.get(`/map/sido`).then(success);
}

function gugunList(sido, success) {
  api.get(`/map/gugun`, { params: { sido: sido } }).then(success);
}

export { sidoList, gugunList };
