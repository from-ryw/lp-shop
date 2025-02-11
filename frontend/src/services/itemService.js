// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
// import axios from "axios";
import httpRequester from "@/libs/httpRequester.js";

// 상품 목록 조회
export const getItem = () => {
    // return axios.get("/v1/api/items").catch(e => e.response);
    return httpRequester.get("/v1/api/items").catch(e => e.response);
}