<script setup>
import {reactive} from "vue";
import Card from "@/components/Card.vue";
import {getItems} from "@/services/itemService.js";

// 반응형 상태
const state = reactive({
  items: []
});

// 커스텀 생성 훅
(async function onCreated() {
  const res = await getItems();

  if (res.status === 200) {
    state.items = res.data;
  }
})();
</script>

<template>
  <div class="home">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row row-cols-1 row-cols-lg-2 row-cols-xl-3 g-3">
          <div class="col" v-for="item in state.items">
            <Card :item="item"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
