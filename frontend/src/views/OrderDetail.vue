<script setup>
import {useRoute} from "vue-router";
import {reactive, watch} from "vue";
import {getOrder} from "@/services/orderService.js";
import {addReview} from "@/services/reviewService.js";

// 라우트 객체
const route = useRoute();

// 반응형 상태
const state = reactive({
  order: {
    id: -1,
    name: "",
    address: "",
    payment: "",
    amount: 0,
    createdAt: "",
    items: []
  },
  form: {
    currentReviewItem: null,
    orderId: -1,
    itemId: -1,
    rating: 0,
    comment: "",
    updatedAt: null
  },
  reviewModal: null,
  isReviewMode: false
});

// 리뷰 모달 열기
const openReviewModal = (item = { review: null }) => {
  state.form.currentReviewItem = item;
  state.form.orderId = state.order.id;
  state.form.itemId = item.id;

  // 안전한 기본값 처리
  state.form.rating = item.review?.rating ?? 0;
  state.form.comment = item.review?.comment ?? "";
  state.form.updatedAt = item.review?.updatedAt ?? null;
  if (state.form.updatedAt != null) { // null이 아니면 포맷 변경
    state.form.updatedAt = window.dateFns.format(new Date(item.review.updatedAt), "yyyy-MM-dd HH:mm:ss");
  }
  state.isReviewMode = !!item.review;

  const modalElement = document.getElementById("reviewModal");
  state.reviewModal = new window.bootstrap.Modal(modalElement);
  state.reviewModal.show();
};

// 별점 선택 함수
const setRating = (star) => {
  state.form.rating = star;
};

// 5000자 초과 방지
watch(() => state.form.comment, (newValue) => {
  if (newValue.length > 5000) {
    alert("최대 5,000자까지만 입력할 수 있습니다.");
    state.form.comment = newValue.slice(0, 5000);
  }
});

// 리뷰 제출
const submit = async () => {
  if (state.form.orderId < 0 || state.form.itemId < 0) {
    window.alert("시스템 오류. 관리자에게 문의하세요.");
    return;
  }
  if (state.form.rating === 0) {
    window.alert("별점을 선택해주세요.");
    return;
  }
  if (!state.form.comment.trim()) {
    window.alert("리뷰 내용을 입력해주세요.");
    document.getElementById("comment")?.focus();
    return;
  }

  const res = await addReview(state.form);

  if (res.status === 200) {
    window.alert("리뷰가 저장되었습니다.");
    state.reviewModal.hide();
    const res = await getOrder(route.params.id);
    if (res.status === 200) {
      state.order = res.data;
    }
  }
};

// 커스텀 생성 훅
(async function onCreated() {
  const id = route.params.id;
  const res = await getOrder(id);

  if (res.status === 200) {
    state.order = res.data;
  }
})();
</script>

<template>
  <div class="order-detail py-5">
    <div class="container">
      <div class="row">
        <div class="order col-lg-7">
          <div class="h5 mb-4">
            <b>주문 내용</b>
          </div>
          <table class="table table-bordered">
            <tbody>
            <tr>
              <th>주문 ID</th>
              <td>{{ state.order.id }}</td>
            </tr>
            <tr>
              <th>주문자명</th>
              <td>{{ state.order.name }}</td>
            </tr>
            <tr>
              <th>주소</th>
              <td>{{ state.order.address }}</td>
            </tr>
            <tr>
              <th>결제 금액</th>
              <td>{{ state.order.amount.toLocaleString() }}원</td>
            </tr>
            <tr>
              <th>결제 수단</th>
              <td>{{ state.order.payment === 'card' ? '카드' : '무통장입금(한국은행 123-456789-777)' }}</td>
            </tr>
            <tr>
              <th>결제 일시</th>
              <td>{{ state.order.createdAt.toLocaleString() }}</td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="items col-lg-5">
          <div class="h5 mb-4">
            <b>주문 상품</b>
          </div>
          <table class="table table-bordered">
            <thead>
            <tr>
              <th>번호</th>
              <th>상품명</th>
              <th>리뷰</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item, idx) in state.order.items">
              <td>{{ idx + 1 }}</td>
              <td><router-link :to="`/items/${item.id}`">[{{item.artist}}] {{ item.name }}</router-link></td>
              <td><button class="btn btn-sm btn-primary" @click="openReviewModal(item)">{{ item.review ? "리뷰 보기" : "리뷰 작성하기" }}</button></td>
            </tr>
            </tbody>
            <tfoot>
            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>

  <!-- 리뷰 작성 모달 -->
  <div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="reviewModalLabel">[{{state.form.currentReviewItem?.artist}}] {{ state.form.currentReviewItem?.name }}</h4>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <form @submit.prevent="submit">
          <div class="modal-body">
              <!-- 별점 선택 -->
              <div class="rating">
                  <span v-for="star in 5" :key="star" @click="!state.isReviewMode && setRating(star)"
                        :class="['star',
                                  star <= state.form.rating ? 'text-warning' : 'text-secondary',
                                  state.isReviewMode ? 'no-pointer' : '']">
                    ★
                  </span>
                <span v-if="state.form.rating > 0" class="rating-score">{{ state.form.rating }}점</span>
              </div>

              <!-- 리뷰 작성 -->
              <div class="mt-3 position-relative">
                <textarea id="comment" v-model="state.form.comment" class="form-control" rows="5" maxlength="5000"
                        placeholder="리뷰 내용을 입력해주세요." :readonly="state.isReviewMode"></textarea>
                <!-- 작성일시 및 글자 수 표시 -->
                <div v-if="state.isReviewMode" class="text-muted text-end mt-1">
                  {{ state.form.updatedAt }}
                </div>
                <div v-else class="text-muted text-end mt-1">
                  {{ state.form.comment.length }} / 5000
                </div>
              </div>
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            <button v-if="!state.isReviewMode" type="submit" class="btn btn-primary">리뷰 제출</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.order-detail {
  > .container {
    table { // ⑦
      th, td {
        padding: 15px 25px;
      }

      th {
        background: #f7f7f7;
      }
    }
  }
}

.modal-content {
  padding: 15px;
}

.rating .star {
  font-size: 24px;
  cursor: pointer;
}
.star {
  transition: color 0.3s ease;
}
.no-pointer {
  cursor: default !important;
}
</style>