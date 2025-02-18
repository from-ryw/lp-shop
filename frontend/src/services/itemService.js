// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
import httpRequester from "@/libs/httpRequester.js";

// 상품 목록 조회
export const getItems = () => {
    return httpRequester.get("/api/items").catch(e => e.response);
}

// 상품 목록 조회
export const getItem = (itemId) => {
    return httpRequester.get(`/api/items/${itemId}`).catch(e => e.response);
}