<script setup>
import {useAccountStore} from "@/stores/account.js";
import {useRouter} from "vue-router";
import {logout} from "@/services/accountService.js";

// 계정 스토어
const accountStore = useAccountStore();

// 라우터 객체
const router = useRouter();

// 로그아웃
const logoutAccount = async () => {
  const res = await logout();

  if (res.status === 200) {
    accountStore.setAccessToken(""); // 계정 스토어의 액세스 토큰 값 초기화
    accountStore.setLoggedIn(false);

    // redirect 처리
    if (window.history.length > 1) {
      router.go(-1);
    } else {
      await router.push("/");
    }
  }
}
</script>

<template>
  <header>
    <div class="navbar navbar-dark bg-dark text-white shadow-sm">
      <div class="container">
        <router-link to="/" class="navbar-brand">
          <strong>LP SHOP</strong>
        </router-link>
        <div class="menus d-flex gap-3">
          <template v-if="!accountStore.loggedIn">
            <router-link to="/login">로그인</router-link>
            <router-link to="/join">회원가입</router-link>
          </template>
          <template v-else>
            <a @click="logoutAccount()">로그아웃</a>
            <router-link to="/orders">주문 내역</router-link>
            <router-link to="/cart">장바구니</router-link>
          </template>
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="scss">
header {
  .menus {
    a {
      cursor: pointer;
      color: #fff;
      text-decoration: none;
    }
  }
}
</style>