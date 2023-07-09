import { apiInstance } from "./index.js";

const api = apiInstance();

function addWish(wish, success) {
  api.post(`/planboard/wishlist`, JSON.stringify(wish)).then(success);
}

function removeWish(wish, success) {
  api.post(`/planboard/wishlist/delete`, wish).then(success);
}

export { addWish, removeWish };
