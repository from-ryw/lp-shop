<script setup>
import {useRoute, useRouter} from "vue-router";
import {computed, onMounted, reactive} from "vue";
import {addOrder} from "@/services/orderService.js";
import {getItems} from "@/services/cartService.js";
import {getItem} from "@/services/itemService.js";

// 라우트 객체
const route = useRoute();

// 라우터 객체
const router = useRouter();

// 반응형 상태
const state = reactive({
  items: [],
  form: {
    name: "",
    address: "",
    payment: "card",
    cardNumber: "",
    orderType: "cart"
  }
});

// 최종 결제 금액
const computedTotalPrice = computed(() => {
  let result = 0;

  state.items.forEach((i) => {
    result += i.price - i.price * i.discountPer / 100;
  });

  return result;
});

// 주문 데이터 제출
const submit = async () => {
  if (!state.form.name?.trim()) {
    window.alert("이름을 입력해주세요.");
    document.getElementById("name")?.focus();
    return;
  } else if (!state.form.address?.trim()) {
    window.alert("주소를 입력해주세요.");
    document.getElementById("address")?.focus();
    return;
  } else if (state.form.payment === 'card') {
    if (!state.form.cardNumber?.trim()) {
      window.alert("카드 번호를 입력해주세요.");
      document.getElementById("cardNum")?.focus();
      return;
    } else if (state.form.cardNumber.length > 16 || parseInt(state.form.cardNumber).toString() !== state.form.cardNumber) {
      window.alert("카드 번호는 16자 이하의 숫자로만 입력해주세요.");
      document.getElementById("cardNum")?.focus();
      return;
    }
  }

  // 결제 수단이 카드가 아니면
  if (state.form.payment !== "card") {
    state.form.cardNumber = "";
  }

  state.form.itemIds = state.items.map(i => i.id);

  // 상품 상세 페이지에서 주문하기 버튼을 누른 경우
  const itemId = route.query.itemId;
  if (itemId) {
    state.form.orderType = "direct";
  }

  const res = await addOrder(state.form);

  if (res.status === 200) {
    const message = ["주문이 완료되었습니다."];

    if (state.form.payment === "bank") {
      const price = computedTotalPrice.value.toLocaleString();
      message.push(`한국은행 123-456789-777 계좌로 ${price}원을 입금해주시기 바랍니다.`);
    }

    window.alert(message.join("\n"));

    // 주문 id가 있다면 주문 상세 내역 페이지로 이동
    if (res.data.id) {
      await router.push(`/orders/${res.data.id}`);
    } else {
      await router.push("/");
    }
  }
};

// 도로명 주소 검색
const openAddressPopup = async () => {
  new daum.Postcode({
    oncomplete: (data) => {
      // 사용자가 주소를 선택했을 때 실행되는 콜백 함수
      let fullAddress = `(${data.zonecode}) ${data.roadAddress}`; // (우편번호) 도로명 주소
      if (data.buildingName) {
        fullAddress += ` (${data.buildingName})`; // 건물명이 있으면 추가
      }
      state.form.address = fullAddress; // 입력창에 주소 반영
    },
  }).open();
}

// 커스텀 생성 훅
onMounted(async () => {
  const itemId = route.query.itemId;

  const res = itemId ? await getItem(itemId) : await getItems();
  if (res.status === 200) {
    // data가 배열이 아닌 경우, 배열 처리
    state.items = Array.isArray(res.data) ? res.data : [res.data];
  }
});
</script>

<template>
  <form class="order-form" @submit.prevent="submit">
    <div class="container">
      <div class="py-5 text-center">
        <div class="h4">
          <p>주문하기</p>
        </div>
        <p class="h6 font-lg mt-3">주문 내역을 확인하시고 주문하기 버튼을 클릭해주세요.</p>
      </div>

      <div class="row g-5">
        <!-- 주문 상품 -->
        <div class="col-md-5 col-lg-4 order-md-last">
          <div class="mb-3">
            <span class="h5 mb-3 align-middle me-2">
              <b>주문 상품</b>
            </span>
            <span class="badge bg-primary rounded-pill align-middel">{{ state.items.length }}</span>
          </div>

          <ul class="items list-group mb-3">
            <li class="p-3 list-group-item d-flex justify-content-between" v-for="i in state.items">
              <div>
                <h6 class="my-0">{{ i.name }}</h6>
              </div>
              <span class="text-muted">
                {{ (i.price - i.price * i.discountPer / 100).toLocaleString() }}원
              </span>
            </li>
          </ul>

          <div class="border p-4 bg-light h5 rounded text-center total-price">
            <span>합계 </span>
            <b>{{ computedTotalPrice.toLocaleString() }}원</b>
          </div>

          <button type="submit" class="w-100 btn btn-primary py-4 mt-5">결제하기</button>
        </div>

        <!-- 주문자 정보 -->
        <div class="col-md-7 col-lg-8">
          <div class="h5 mb-3">
            <b>주문자 정보</b>
          </div>
          <div class="row g-3">
            <div class="col-12">
              <label for="name" class="form-label">이름</label>
              <input type="text" class="form-control p-3" id="name" v-model="state.form.name">
            </div>

            <div class="col-12 pt-1">
              <label for="address" class="form-label">주소</label>
              <div class="d-flex gap-2">
                <input type="text" class="form-control p-3 flex-grow-1 w-75" id="address" v-model="state.form.address">
                <span @click="openAddressPopup()" class="btn btn-light border d-flex align-items-center">검색</span>
              </div>
            </div>
          </div>

          <div class="h5 mt-5 mb-3">
            <b>결제 수단</b>
          </div>
          <div class="form-check">
            <input id="card" name="paymentMethod" type="radio" class="form-check-input" value="card" v-model="state.form.payment">
            <label class="form-check-label" for="card">신용카드</label>
          </div>
          <div class="form-check">
            <input id="bank" name="paymentMethod" type="radio" class="form-check-input" value="bank" v-model="state.form.payment">
            <label class="form-check-label" for="bank">무통장입금</label>
          </div>
          <div class="pt-1" v-if="state.form.payment === 'card'">
            <label for="cardNum" class="form-label">카드 번호</label>
            <input type="text" class="form-control p-3" id="cardNum" v-model="state.form.cardNumber">
          </div>
        </div>
      </div>
    </div>
  </form>
</template>