<script setup>
import { ref } from "vue";

const emit = defineEmits(["search"]);
const props = defineProps({
  selectedDiscounts: Array,
  selectedSort: String
});

const searchKeyword = ref("");
const showFilters = ref(false);
const selectedDiscounts = ref([...props.selectedDiscounts]);
const selectedSort = ref(props.selectedSort);

// 할인율
const discountRanges = ref([
  { label: "전체", min: null, max: null },
  { label: "0~10%", min: 0, max: 10 },
  { label: "10~20%", min: 10, max: 20 },
  { label: "20~30%", min: 20, max: 30 },
  { label: "30~40%", min: 30, max: 40 },
  { label: "40~50%", min: 40, max: 50 },
  { label: "50~60%", min: 50, max: 60 },
  { label: "60~70%", min: 60, max: 70 },
  { label: "70~80%", min: 70, max: 80 },
  { label: "80~90%", min: 80, max: 90 },
  { label: "90~100%", min: 90, max: 100 }
]);

// 정렬
const sortOptions = [
  { label: "신상품", value: "latest" },    // 최신순
  { label: "할인율", value: "discount" },  // 할인율 높은 순
  { label: "낮은가격", value: "price_asc" }, // 낮은 가격 순
  { label: "높은가격", value: "price_desc" } // 높은 가격 순
];

// 검색
const emitSearch = () => {
  emit("search", searchKeyword.value, selectedDiscounts.value, selectedSort.value);
};

// 상세검색 토글
const toggleFilters = () => {
  showFilters.value = !showFilters.value;
};

// 할인율 토글
const toggleDiscount = (range) => {
  if (range.label === "전체") {
    selectedDiscounts.value = [{ label: "전체", min: null, max: null }];
  } else {
    selectedDiscounts.value = selectedDiscounts.value.filter((item) => item.label !== "전체");

    const index = selectedDiscounts.value.findIndex(
        (item) => item.min === range.min && item.max === range.max
    );

    if (index !== -1) {
      selectedDiscounts.value.splice(index, 1);
    } else {
      selectedDiscounts.value.push(range);
    }

    if (selectedDiscounts.value.length === 0) {
      selectedDiscounts.value = [{ label: "전체", min: null, max: null }];
    }
  }

  emitSearch();
};

// 정렬 토글
const selectSort = (value) => {
  selectedSort.value = value;
  emitSearch();
};


// 필터 초기화
const resetFilters = () => {
  searchKeyword.value = "";
  selectedDiscounts.value = [{ label: "전체", min: null, max: null }];
  selectedSort.value = "latest";
  emitSearch();
};
</script>

<template>
  <div class="search-bar">
    <div class="search-container pt-3 pb-2">
      <div class="container d-flex align-items-center">
        <!-- 검색 입력창 -->
        <div class="input-group search-container">
          <input
              type="text"
              class="form-control search-input"
              v-model="searchKeyword"
              placeholder="검색어를 입력하세요 (아티스트, 상품명)"
              @keyup.enter="emitSearch"
          />
          <button class="btn btn-outline-secondary search-btn" @click="emitSearch">
            <i class="bi bi-search"></i>
          </button>
        </div>

        <!-- 닫기 버튼 (검색창과 같은 높이) -->
        <button class="btn btn-dark ms-2 search-toggle-btn btn-sm" @click="toggleFilters">
          <i class="bi" :class="showFilters ? 'bi-x-lg' : 'bi-funnel'"></i>
          <span>{{ showFilters ? "닫기" : "상세검색" }}</span>
        </button>
      </div>

      <!-- 상세 검색 옵션 -->
      <div v-if="showFilters" class="search-filter-container">
        <div class="container py-3 my-2 bg-light rounded shadow">
          <div class="row">
            <!-- 할인 필터 -->
            <div class="col-md-6">
              <strong>할인</strong>
              <ul class="filter-list">
                <li v-for="range in discountRanges" :key="range.label"
                    @click="toggleDiscount(range)"
                    :class="selectedDiscounts.some(item => item.label === range.label) ? 'selected' : ''">
                  {{ range.label }}
                </li>
              </ul>
            </div>

            <!-- 정렬 필터 -->
            <div class="col-md-6">
              <strong>정렬</strong>
              <ul class="filter-list">
                <li v-for="sortOption in sortOptions" :key="sortOption.value"
                    @click="selectSort(sortOption.value)"
                    :class="selectedSort === sortOption.value ? 'selected' : ''">
                  {{ sortOption.label }}
                </li>
              </ul>
            </div>

            <!-- 초기화 버튼 -->
            <div class="filter-buttons mt-1 d-flex justify-content-end px-3">
              <button @click="resetFilters" class="btn btn-secondary btn-sm">초기화</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.search-container {
  width: 100%;
  background-color: #f8f9fa;
}

.search-input,
.search-btn,
.search-toggle-btn,
.filter-list li {
  height: 38px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  padding: 5px 10px;
}

/* 닫기 버튼 */
.search-toggle-btn {
  min-width: 110px;
  gap: 5px;
}

/* 필터 리스트 스타일 */
.filter-list {
  display: flex;
  flex-wrap: wrap;
  list-style: none;
  padding: 0;
  margin: 0;
}

.filter-list li {
  cursor: pointer;
  border: 1px solid #ddd;
  text-align: center;
  width: 24%;
  height: 31.5px;
  margin: 2px;
  background: white;
}

/* 선택된 필터 스타일 */
.selected {
  background-color: #007bff !important;
  color: white !important;
}
</style>
