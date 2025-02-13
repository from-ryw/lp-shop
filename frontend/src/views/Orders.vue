<script setup>
import {reactive} from "vue";
import {getOrders} from "@/services/orderService.js";

// 반응형 상태
const state = reactive({
  // HTTP 인수
  args: {
    page: 0,
    size: 20
  },
  // 페이지네이션
  page: {
    index: 0,
    totalPages: 0,
    totalElements: 0
  },
  orders: []
});

// 목록 번호 추출
const getListNum = (idx) => {
  // 전체 데이터 수 - 인덱스 - 페이지당 데이터 수 * 페이지 인덱스
  return state.page.totalElements - idx - state.args.size * state.page.index;
}

// 주문 목록 조회
const load = async (pageIdx) => {
  // 매개변수(페이지 인덱스)가 있으면  args 객체 업데이트
  if (pageIdx !== undefined) {
    state.args.page = pageIdx;
  }

  // HTTP 인수 전달
  const res = await getOrders(state.args);

  if (res.status === 200) {
    state.orders = res.data.content;
    state.page.index = res.data.number;
    state.page.totalPages = res.data.totalPages;
    state.page.totalElements = res.data.totalElements;
  }
};

// 커스텀 생성 훅
(async function onCreated() {
  await load();
})();
</script>

<template>
  <div class="order">
    <div class="container">
      <table class="table table-bordered my-4">
        <thead>
        <tr>
          <th class="text-center">번호</th>
          <th class="text-center">주문자명</th>
          <th class="text-center">결제 수단</th>
          <th class="text-center">결제 금액</th>
          <th class="text-center">결제일시</th>
          <th class="text-center">자세히 보기</th>
        </tr>
        </thead>
        <tbody>
        <tr v-if="state.orders.length === 0">
          <td colspan="6" class="text-center">주문 내역이 없습니다.</td>
        </tr>
        <tr v-for="(o, idx) in state.orders">
          <td class="text-center">{{ getListNum(idx) }}</td>
          <td>{{ o.name }}</td>
          <td>{{ o.payment === 'card' ? '카드' : '무통장입금'}}</td>
          <td class="text-end">{{ o.amount.toLocaleString() }}원</td>
          <td class="text-center">{{ o.createdAt.toLocaleString() }}</td>
          <td class="text-center">
            <router-link :to="`/orders/${o.id}`">자세히 보기</router-link>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="pagination d-flex justify-content-center pt-2">
        <div class="btn-group" role="group">
          <button class="btn py-2 px-3"
                  :class="[state.page.index === idx ? 'btn-primary' : 'btn-outline-secondary']"
                  v-for="(i, idx) in state.page.totalPages" @click="load(idx)">
            {{ i }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>