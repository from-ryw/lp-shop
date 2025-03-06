<script setup>
// 프로퍼티 객체
import {reactive} from "vue";
import {getItem} from "@/services/itemService.js";
import {useRoute, useRouter} from "vue-router";
import {addItem} from "@/services/cartService.js";
import {useAccountStore} from "@/stores/account.js";

// 라우트 객체
const route = useRoute();

// 라우터 객체
const router = useRouter();

// 계정 스토어
const accountStore = useAccountStore();

const state = reactive({
  item: {
    id: Number,
    name: String,
    artist: String,
    imgPath: String,
    price: Number,
    discountPer: Number,
    description: String,
    descriptionImgPath: String,
    reviews: []
  },
  activeTab: "description", // 기본 선택 탭(상품 설명)
  rating: 0, // 사용자가 선택한 별점
  comment: "", // 입력한 리뷰 코멘트
  reviews: [] // 작성된 리뷰 목록
});

// 탭 변경
const changeTab = (tab) => {
  state.activeTab = tab;
};

// 날짜 변환 함수
const formatDate = (date) => {
  return window.dateFns.format(new Date(date), "yyyy-MM-dd HH:mm:ss");
};

// 해당 상품 구매하기
const order = async () => {
  if (!accountStore.loggedIn) {
    if (window.confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?")) {
      await router.push("/login");
    }

    return;
  }

  await router.push(`/order?itemId=${state.item.id}`);
};

// 장바구니에 상품 담기
const put = async () => {
  if (!accountStore.loggedIn) {
    if (window.confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?")) {
      await router.push("/login");
    }

    return;
  }

  const res = await addItem(state.item.id);

  if (res.status === 200 && window.confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')) {
    await router.push("/cart");
  }
};

// 아이디 마스킹

// 커스텀 생성 훅
(async function onCreated() {
  const id = route.params.id;
  const res = await getItem(id);

  if (res.status === 200) {
    state.item = res.data;
  }
})();
</script>

<template>
  <div class="order-detail py-5">
    <div class="container">
      <div class="row align-items-end">
        <!-- 상품 이미지 -->
        <div class="col-lg-6">
          <img class="img-fluid item-img" :src="state.item.imgPath" :alt="state.item.name">
        </div>

        <!-- 상품 정보 -->
        <div class="col-lg-6 border border-secondary rounded p-3 mt-3 shadow-sm d-flex flex-column justify-content-end">
          <!-- 상품 아티스트 및 이름 -->
          <div class="h5 mb-3">
            <b>[{{ state.item.artist }}] {{ state.item.name }}</b>
          </div>

          <!-- 상품 가격 -->
          <template v-if="state.item.discountPer > 0">
            <div class="price">
              <span class="discountPer badge bg-danger me-3">{{ state.item.discountPer }}%</span>
              <span class="real text-muted text-decoration-line-through me-3">{{ state.item.price.toLocaleString() }}원</span>
              <span class="discounted text-danger"><b>{{ (state.item.price - state.item.price * state.item.discountPer / 100).toLocaleString() }}원</b></span>
            </div>
          </template>
          <template v-else>
            <div class="price">
              <span class="real">{{ state.item.price.toLocaleString() }}원</span>
            </div>
          </template>

          <!-- 버튼(구매하기, 장바구니)-->
          <div class="d-flex flex-column gap-2 mt-3">
            <button class="btn btn-primary py-3" @click="order()">구매하기</button>
            <button class="btn btn-light py-3" @click="put()">장바구니</button>
          </div>
        </div>
      </div>

      <hr>

      <!-- 상품 설명 탭 (부트스트랩 적용) -->
      <div class="item-tabs">
        <ul class="nav nav-tabs nav-justified border-bottom-0">
          <li class="nav-item">
            <button class="nav-link py-3" :class="{ active: state.activeTab === 'description' }" @click="changeTab('description')">
              상품 설명
            </button>
          </li>
          <li class="nav-item">
            <button class="nav-link py-3" :class="{ active: state.activeTab === 'reviews' }" @click="changeTab('reviews')">
              리뷰
            </button>
          </li>
        </ul>

        <div class="tab-content border p-4 bg-light">
          <!-- 상품 설명 -->
          <div v-if="state.activeTab === 'description'" :class="['tab-pane', 'fade', 'd-flex', 'flex-column', 'align-items-center', { 'show active': state.activeTab === 'description' }]">
            <img v-if="state.item.descriptionImgPath" class="img-fluid description-img mb-3" :src="state.item.descriptionImgPath" :alt="state.item.name">
            <p class="description">{{ state.item.description }}</p>
          </div>

          <!-- 리뷰 -->
          <div v-if="state.activeTab === 'reviews'" :class="['tab-pane', 'fade', { 'show active': state.activeTab === 'reviews' }]">
            <!-- 리뷰 리스트 -->
            <div v-if="state.item.reviews.length === 0" class="text-muted">아직 리뷰가 없습니다.</div>
            <div v-else>
              <ul class="list-group mt-2 border-top">
                <li v-for="(review, index) in state.item.reviews" :key="index" class="list-group-item border-bottom pb-3">

                  <!-- 별점 + 사용자 정보 -->
                  <div class="d-flex flex-column">
                    <!-- 별점 표시 -->
                    <div class="d-flex">
                      <span v-for="star in review.rating" :key="'filled' + star" class="text-warning fs-5 fw-bold">★</span>
                      <span v-for="star in (5 - review.rating)" :key="'empty' + star" class="text-secondary fs-5">★</span>
                    </div>

                    <!-- 사용자 정보 (이름 & 날짜) -->
                    <div class="d-flex align-items-center mt-1 text-secondary">
                      <small class="fw-bold">{{ review.memberName }}</small>
                      <small class="ms-2">· {{ formatDate(review.updatedAt) }}</small>
                    </div>
                  </div>

                  <!-- 리뷰 내용 -->
                  <p class="mb-0 mt-2 fs-6 text-dark" style="white-space: pre-line;">{{ review.comment }}</p>
                </li>
              </ul>
            </div>
          </div>

        </div>
      </div>

    </div>
  </div>

</template>

<style scoped lang="scss">
.item-img {
  width: 100%;
  height: 605px;
  object-fit: cover;
}
.description {
  white-space: pre-line;
}

/* 탭 스타일 */
.nav-tabs .nav-link.active {
  background-color: #f8f9fa;
  font-weight: 700;
  border-bottom: 1px solid #f8f9fa; /* 기존 테두리 유지 */
}

/* 탭 콘텐츠 (tab-content) */
.tab-content {
  border: 1px solid #dee2e6; /* 기존 테두리 유지 */
  border-top: 3px solid #f8f9fa; /* 상단 테두리만 bg-light 색상 */
  border-radius: 0 0 5px 5px; /* 아래쪽만 둥글게 */
}

/* 별점 스타일 */
.rating {
  font-size: 24px;
  cursor: pointer;
}
.star {
  transition: color 0.3s ease;
}

/* 리뷰 리스트 스타일 */
.list-group-item {
  border: none;
  background-color: #f8f9fa;
}
</style>