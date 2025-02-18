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
    descriptionImgPath: String
  }
});

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
<!--            <router-link :to="`/order?itemId=${state.item.id}`" class="btn btn-primary py-3">주문하기</router-link>-->
            <button class="btn btn-light py-3" @click="put()">장바구니</button>
          </div>
        </div>
      </div>

      <hr>

      <!-- 상품 설명 -->
      <div class="item-description text-center py-5 px-5">
        <!-- 설명 이미지 -->
        <img v-if="state.item.descriptionImgPath" class="img-fluid description-img mb-3" :src="state.item.descriptionImgPath" :alt="state.item.name">

        <!-- 설명 글 -->
        <div class="description">
          <p class="description">{{ state.item.description }}</p>
        </div>
      </div>

      <hr>
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
</style>