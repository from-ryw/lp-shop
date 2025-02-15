// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
import httpRequester from "@/libs/httpRequester.js";

// 장바구니 상품 목록 조회
export const getItems = () => {
    return httpRequester.get("/api/carts/items").catch(e => e.response);
}

// 장바구니 상품 추가
export const addItem = (itemId) => {
    return httpRequester.post("/api/carts", { itemId }).catch(e => e.response);
}

// 장바구니에서 상품 삭제
export const removeItem = (itemId) => {
    return httpRequester.delete(`/api/carts/items/${itemId}`).catch(e => e.response);
}