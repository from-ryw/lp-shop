<script setup>
import {computed} from "vue";
import {addItem} from "@/services/cartService.js";
import {useRouter} from "vue-router";
import {useAccountStore} from "@/stores/account.js";

// 프로퍼티 객체
const props = defineProps({
  item: {
    id: Number,
    name: String,
    artist: String,
    imgPath: String,
    price: Number,
    discountPer: Number
  }
});

// 상품 할인가
const computedItemDiscountPrice = computed(() => {
  return (props.item.price - (props.item.price * props.item.discountPer / 100)).toLocaleString() + '원';
});

// 라우터 객체
const router = useRouter();

// 계정 스토어
const accountStore = useAccountStore();

// 특정 상품 상세페이지로 이동
const goToDetail = () => {
  router.push(`/items/${props.item.id}`);
};

// 장바구니에 상품 담기
const put = async () => {
  if (!accountStore.loggedIn) {
    if (window.confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?")) {
      await router.push("/login");
    }

    return;
  }

  const res = await addItem(props.item.id);

  if (res.status === 200 && window.confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')) {
    await router.push("/cart");
  }
};
</script>

<template>
  <div class="card shadow-sm" @click="goToDetail">
    <!-- 상품 사진 -->
    <span class="img" :style="{backgroundImage: `url(${props.item.imgPath})`}" :aria-label="`상품 사진(${props.item.name})`"></span>

    <div class="card-body">
      <p class="card-text">
        <!-- 상품 아티스트 및 이름 -->
        <span class="me-2">
          <b>[{{ props.item.artist }}]</b> {{ props.item.name }}
        </span>
        <!-- 상품 할인율 -->
        <span v-if="props.item.discountPer > 0" class="discount badge bg-danger">{{ props.item.discountPer }}%</span>
      </p>

      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary btn-sm" @click.stop="put()">장바구니 담기</button>
        <!-- 상품 정가(숫자 데이터에 3자리마다 쉼표 표기) -->
        <small v-if="props.item.discountPer > 0" class="price text-muted">{{ props.item.price.toLocaleString() }}원</small>
        <!-- 상품 할인가 -->
        <small class="real text-danger">{{ computedItemDiscountPrice }}</small>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.card {
  cursor: pointer;

  .img {
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
  }

  .card-body .price {
    text-decoration: line-through;
  }
}
</style>