<script setup>
import {reactive} from "vue";
import Card from "@/components/Card.vue";
import {getItems} from "@/services/itemService.js";
import SearchBar from "@/components/SearchBar.vue";

// 반응형 상태
const state = reactive({
  items: [],
  searchKeyword: "",
  selectedDiscounts: [{ label: "전체", min: null, max: null }],
  selectedSort: "latest"
});

// 검색 이벤트 핸들러
const handleSearch = (keyword, discounts, sort) => {
  state.searchKeyword = keyword;
  state.selectedDiscounts = discounts;
  state.selectedSort = sort;

  console.log("검색어:", state.searchKeyword);
  console.log("선택된 할인율:", state.selectedDiscounts);
  console.log("정렬 기준:", state.selectedSort);

  fetchItems();
};

// 검색 기능
async function fetchItems() {
  const params = {
    searchKeyword: state.searchKeyword || null,
    discountPers: state.selectedDiscounts.filter(d => d.min !== null).map(d => ({ min: d.min, max: d.max })),
    sort: state.selectedSort
  };

  const res = await getItems(params);
  if (res.status === 200) {
    state.items = res.data;
  }
}

// 커스텀 생성 훅
(async function onCreated() {
  // 초기 데이터 불러오기
  fetchItems();
})();
</script>

<template>
  <div class="home">
    <SearchBar
        @search="handleSearch"
        :selectedDiscounts="state.selectedDiscounts"
        :selectedSort="state.selectedSort"
    />

    <div class="album py-2 bg-light">
      <div class="container">
        <div v-if="state.items.length > 0" class="row row-cols-1 row-cols-lg-2 row-cols-xl-3 g-3">
          <div class="col" v-for="item in state.items">
            <Card :item="item"/>
          </div>
        </div>
        <div v-else class="col d-flex justify-content-center align-items-center">
          <span>상품이 존재하지 않습니다.</span>
        </div>
      </div>
    </div>
  </div>
</template>
