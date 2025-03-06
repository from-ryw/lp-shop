# LP SHOP

도서 『Vue 3와 스프링 부트로 시작하는 웹 개발 철저 입문』에서 소개된 갤러리 쇼핑몰 프로젝트를 기반으로 개발한 **LP(레코드) 전문 쇼핑몰 프로젝트**입니다.

기존 프로젝트의 아키텍처와 핵심 기능을 활용하면서, LP 판매에 적합한 기능을 추가 및 확장하여 커스텀 하였습니다.

<br>
<br>

## 목차

- [개발 목적](#개발-목적)
- [사용 기술](#사용-기술)
- [프로젝트 기능](#프로젝트-기능)
- [화면 구성 & 페이지별 기능](#화면-구성--페이지별-기능)
- [엔티티 구조](#엔티티-구조)
- [API](#api)
- [프로젝트 기능](#프로젝트-기능)
- [학습 내용](#학습-내용)
- [변경 이력](#변경-이력)

<br>
<br>

## 개발 목적

이 프로젝트는 **Vue 3, Spring Boot, JPA에 대한 학습 및 실전 활용을 목표로** 제작되었습니다.
기존 경험을 바탕으로 최신 기술을 익히고 실무에서의 활용도를 높이는 것이 핵심 목표입니다.

**개발을 진행한 주요 이유:**

1. **기술 스택 확장**
   - 기존에는 **Spring + Nexacro** 기반의 개발을 주로 진행했으나, 현재 널리 사용되는 **Spring Boot, JPA, Vue.js**에 대한 학습이 필요하다고 판단하여 진행하게 되었습니다.
2. **백엔드 환경 세팅 및 보안 처리 학습**
   - 실무에서는 주로 **이미 구축된 개발 환경**에서 작업했기 때문에 **Spring Boot 기반의 인증, 암호화 등 보안 처리 과정**을 직접 경험하고자 하였습니다.

<br>
<br>

## 사용 기술

- **프론트엔드**

  - **Vue 3**: 컴포넌트 기반 프레임워크로 SPA (Single Page Application) 개발
  - **Vue Router**: 페이지 이동 및 네비게이션 관리
  - **Pinia**: 글로벌 상태 관리 라이브러리
  - **Axios**: API 요청을 위한 HTTP 클라이언트 라이브러리
  - **Bootstrap / SCSS**: 스타일링 및 반응형 UI 구현

- **백엔드**

  - **Spring Boot**: REST API 개발 및 서버 관리
  - **JPA (Java Persistence API)**: ORM(Object Relational Mapping) 기술
  - **Spring Security + JWT**: 사용자 인증 및 권한 관리

- **데이터베이스**

  - **MariaDB**: MySQL 기반의 관계형 데이터베이스

- **인증 & 보안**
  - **JWT (Json Web Token)**: 사용자 인증 및 액세스 토큰 관리
  - **Spring Security**: API 요청 보안 처리

<br>
<br>

## 프로젝트 기능

현재 LP SHOP은 기본적인 쇼핑몰 기능을 구현한 상태이며, 다음과 같은 기능을 추가할 예정입니다.

1. **사용자 기능**
   - **회원 관리**: 회원 가입, 로그인, 로그아웃, 로그인 상태 확인 (JWT 기반 인증)
   - **상품 관리**: LP 상품 목록 조회, 상품 상세 페이지
   - **장바구니 기능**: 로그인한 회원 장바구니에 상품 추가 및 삭제 가능
   - **주문 관리**: 주문 내역 조회 및 신규 주문 추가
2. **개선 및 추가 기능 계획**
   - **사용자 기능 강화**
     - **검색 및 필터 기능 추가** – 아티스트 검색 및 필터링 기능 구현<br>
     - **리뷰 및 평점 시스템 도입** – 사용자가 상품 리뷰 및 평점을 작성할 수 있도록 기능 추가<br>
   - **관리자 기능 추가**
     - **관리자 기능 확장** – 상품 및 주문을 관리할 수 있는 기능 추가
     - **관리자 대시보드 추가** – 매출 데이터 및 사용자 관리 기능 제공
   - **보안 및 서비스 안정성 개선**
     - **예외 및 유효성 처리 보강** – 현재 미흡한 예외 및 유효성 로직을 개선

<br>
<br>

## 화면 구성 & 페이지별 기능

1. **홈(상품 목록) 페이지**

   - 전체 상품 조회 (`GET /api/items`)
   - 로그인 바로가기
   - 회원가입 바로가기
   - 주문 내역 바로가기
   - 장바구니 바로가기
   - 로그아웃 기능 (`POST /api/accounts/logout`)
   - 상품 상세 페이지로 이동 (`GET /api/items/${itemId}`)
   - 장바구니 담기 기능 (`POST /api/carts`)
     - [장바구니 담기] 버튼 클릭 알림(alert) 표시
       - 로그인 전: ‘로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?’
       - 로그인 후: ‘장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?’
   - 로그인 여부에 따라 상단 네비게이션 바 변경 (`accountStore.loggedIn` 활용)

     - 로그인 전 화면

       ![로그인 전 화면](https://postfiles.pstatic.net/MjAyNTAyMTVfMTAz/MDAxNzM5NTQ2NDk2NDkz.RxMJbbXvqr5lGy-9f91fXqMSGbv-etpWsPRO3G14cMUg.68ZVM99oyjKabAkhdp0mh4wd-KfE_WUtlAWGGSsK2Xog.PNG/%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%A0%84.png?type=w3840)

     - 로그인 후 화면

       ![로그인 후 화면](https://postfiles.pstatic.net/MjAyNTAyMTVfOTIg/MDAxNzM5NTQ2NDk2NDky.6SUqQofSzryYmrQLDvkfVsOBHMbviKNNeQYxiApLV8wg.Czptt30aJ5snvO9ATg7KErPvXkEdQ_7tileGkd_gK0Ig.PNG/%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%9B%84.png?type=w966)

   ![홈(상품 목록) 페이지](https://postfiles.pstatic.net/MjAyNTAyMTlfMjQ1/MDAxNzM5OTY3MzQ5MTA2.3Y1tBevr_OY7Shc05eJGNjUqs6Jc9T0DzRuez6U9ivgg.JhVujr8SL9tZch3Ih-e3VVtHwFjasMwD1US8VTiwncEg.PNG/%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%80.png?type=w966)

2. **회원가입 페이지**

   - 회원 정보 입력 후 가입 요청 (`POST /api/accounts/join`)
   - 입력 필드: 이름, 이메일, 패스워드
   - 가입 완료 시 알림(alert) 표시 후 메인 페이지로 이동
     - 가입 완료: ‘로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?’
     - 이메일 중복: ‘이미 사용 중인 이메일입니다. 다른 값을 입력해주세요.’

   ![회원가입 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMjMx/MDAxNzM5NTQ2NDk2NzYw.d3x-btSIg0AdXNFVsQVFsCkV4Y6hDVzkIQlCu25-sAcg.lXzNzFcLO5iLwBjt6203-aQQaFVoFlJIo_eUWGSUVk8g.PNG/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.png?type=w966)

3. **로그인 페이지**

   - 로그인 정보 입력 후 로그인 요청 (`POST /api/accounts/login`)
   - 입력 필드: 이메일, 패스워드
   - 로그인 완료 시 메인 페이지로 이동
     - 로그인 완료: 메인 페이지로 이동
     - 일치하는 정보가 없는 경우: ‘입력하신 정보와 일치하는 회원이 없습니다.’

   ![로그인 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMTc0/MDAxNzM5NTQ2NDk2NDk2.lAKBsIU71s3QG4ycEmqRXj3yTqXsw6hRlOHUmWdMgDMg.tAKtKk52niMo4GfAVLB9lsbOdmgGZDVfkvyS813RDPIg.PNG/%EB%A1%9C%EA%B7%B8%EC%9D%B8.png?type=w966)

4. **장바구니 페이지**

   - 장바구니 전체 상품 조회 (`GET /api/carts/items`)
   - 주문하기 바로가기
   - 장바구니 상품 삭제 기능
     - [ x ] 클릭 알림(alert) 표시. ‘선택하신 장바구니의 상품을 삭제했습니다.

   ![장바구니 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfNTQg/MDAxNzM5NTQ2NDk2NTI0.AknqgYs7KTI_AxDMIJhZjp_Z9azoulq1QtSy9hDBrnkg.js8s5Ez94iJDWdi0xVAt_cVHFw9PphAeVgf0iNeTJqEg.PNG/%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88.png?type=w966)

5. **상품 상세 페이지**

   - 해당 상품 조회 (`GET /api/items/${itemId}`)
   - 상품 설명 및 이미지 표시
   - [주문하기] 버튼 클릭 시 주문하기 페이지로 이동
   - [장바구니] 버튼 클릭 시 장바구니 담기 및 알림(alert) 표시
     - ’장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?’

   ![상품 상세 페이지](https://postfiles.pstatic.net/MjAyNTAyMTlfMjEg/MDAxNzM5OTY3MzQ4OTg0.V2Ac0bVerKMP_MEXW02scFYzyawRrTjZkBQ6PlwbcNEg.wcsutJev2njHgBkISKIi0DcHDyAmqWrk1HOOvzrZ-9Ug.PNG/%EC%83%81%EC%84%B8%ED%8E%98%EC%9D%B4%EC%A7%80.png?type=w966)

6. **주문하기 페이지**

   - 주문 정보 입력 후 주문 요청 (`POST /api/orders`)
   - 입력 필드:
     - 주문자 정보: 이름, 주소
     - 결제 수단: 신용카드 / 무통장입금 선택
     - 카드 결제 선택 시 카드 번호 입력 필드 활성화
   - 주소의 [검색] 버튼 클릭 시 도로명 주소 검색 팝업 생성([Daum 우편번호 서비스 API](https://postcode.map.daum.net/guide) 사용)
     - 팝업에서 주소 선택 시 `(우편번호) 도로명 주소` 형식으로 주소 칸에 입력됨
      ![도로명 주소 검색 팝업](https://postfiles.pstatic.net/MjAyNTAyMTlfMTUg/MDAxNzM5OTY3NjU4OTU0.NCIZ4FOlcGF-B3FIq1F4x4kkrdphjVhyGkH3qU-rMvIg.KOwwmvBbFKQZu34X7SDgcdtBf9ReM2PApaE6Nx4GPb8g.PNG/%EB%8F%84%EB%A1%9C%EB%AA%85%EC%A3%BC%EC%86%8C_%EA%B2%80%EC%83%89_%ED%8C%9D%EC%97%85.png?type=w966)
   - 주문 상품 목록 표시: 선택한 상품과 총 결제 금액을 보여줌
     - 주문 상품 목록 조회 (`GET /api/carts/items`)
   - [결제하기] 버튼 클릭 시 주문 완료 및 알림(alert) 표시 후 메인 페이지로 이동
     - 결제 수단 ‘신용카드’ 선택 시
       - ’주문이 완료되었습니다.’
     - 결제 수단 ‘무통장입금’ 선택 시
       - ‘주문이 완료되었습니다. 한국은행 123-456789-777 계좌로 ${price}원을 입금해주시기 바랍니다.’ 문구 추가

   ![주문하기 페이지](https://postfiles.pstatic.net/MjAyNTAyMTlfMTA3/MDAxNzM5OTY3NjU4OTU1.K_49ZyBZB4FUH8Jw2Zb0BWAr9GmYjxcPyMq5y8LLvCog.Ru9UQctBfoGnkWUOLy7RViaowyKzZ3ONxGaDDVBU1Fkg.PNG/%EC%A3%BC%EB%AC%B8%ED%95%98%EA%B8%B0.png?type=w966)

7. **주문 내역 페이지**

   - 로그인한 회원의 주문 내역 조회 (`GET /api/orders`)
   - 주문 내역 목록을 테이블 형식으로 표시
     - 주문자명, 결제 수단, 결제 금액, 결제 일시, 자세히 보기
   - 페이지네이션 적용
     - 한 페이지에 최대 20개까지 표시
     - 추가 주문 내역은 페이지네이션을 통해 조회 가능
   - [자세히 보기] 버튼 클릭 시 해당 주문의 상세 내역 페이지로 이동

   ![주문 내역 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMTIx/MDAxNzM5NTQ2NDk2Njk0.8g1jsiOBv3K9MYXukw_NwWcAV2p9HB3BSVmhm2L4lBYg.vL16sgUvK0Y3LxEL1eJVfxG7sSioBsrAv9X4GLRZVHwg.PNG/%EC%A3%BC%EB%AC%B8%EB%82%B4%EC%97%AD.png?type=w3840)

8. **주문 상세 내역 페이지**

   - 해당 주문의 상세 내역 조회 (`GET /api/orders/{id}`)
   - 주문 내용 및 주문 상품 목록 표시
     - 주문 내용: 주문 ID, 주문자명, 주소, 결제 금액, 결제 수단, 결제 일시
     - 주문 상품 목록: 상품명

   ![주문 상세 내역 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMTMz/MDAxNzM5NTQ2NDk2NTE5.EuTwbY8y1KGhDEerPfUYDAWCuajBRX3hHqkNz01l4N4g.KwLzIAxcBaD5iqcAfoizHbl2ohSHSPfyN7KoxUL7aFkg.PNG/%EC%A3%BC%EB%AC%B8%EC%83%81%EC%84%B8%EB%82%B4%EC%97%AD.png?type=w966)

<br>
<br>

## 엔티티 구조

![엔티티 구조](https://postfiles.pstatic.net/MjAyNTAyMThfMyAg/MDAxNzM5ODUxMTIyNTIy.h1t6y6JyVu_r-wKORwyYcsjJhS8rscjQf_81XRMC3jMg.cavFALr_WHj0qYsTf2osg6SoaC5qfP8ZrXr0ffO2Jzcg.PNG/%EC%97%94%ED%8B%B0%ED%8B%B0%EA%B5%AC%EC%A1%B0.png?type=w966)

<br>
<br>

## API

### **회원(member) & 계정(account)**

1. **회원 가입 API (`POST /api/accounts/join`)**

   - **사용자가 회원 가입을 진행하는 API**
   - **Method:** `POST`
   - **URL:** `/api/accounts/join`
   - **인증 필요:** X (회원 가입이므로 인증 불필요)
   - **요청 (Request)**

     - **Headers:**
       | Key | Value | 설명 |
       | -------------- | ------------------ | ------------------------- |
       | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
       | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
     - **Body (JSON):**
       ```json
       {
         "name": "RYW",
         "loginId": "from.ryw@example.com",
         "loginPw": "fromryw2025!"
       }
       ```
       | 필드명    | 타입     | 필수 여부 | 설명                                  |
       | --------- | -------- | --------- | ------------------------------------- |
       | `name`    | `string` | Y         | 회원                                  |
       | `loginId` | `string` | Y         | 로그인 아이디 (중복 불가)             |
       | `loginPw` | `string` | Y         | 로그인 패스워드 (솔트 및 해시 처리됨) |

   - **응답 (Response)**
     - **200 OK**
       - 회원 가입 성공
       - 비밀번호(`loginPw`)는 솔트(`Salt`)와 해시(`Hash`)를 적용하여 저장됨
     - **400 Bad Request**
       - 요청 필수 값 누락
       - `name`, `loginId`, `loginPw` 중 하나라도 입력되지 않으면 발생
     - **409 Conflict** - 중복된 `loginId` 가 존재하면 발생

---

<br>

2. **로그인 API (`POST /api/accounts/login`)**

   - **사용자가 아이디와 비밀번호를 입력하여 로그인하는 API**
   - **Method:** `POST`
   - **URL:** `/api/accounts/login`
   - **인증 필요:** X (로그인 시에는 인증 불필요)
   - **요청 (Request)**

     - **Headers:**
       | Key | Value | 설명 |
       | -------------- | ------------------ | --------------------- |
       | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
     - **Body (JSON):**
       ```json
       {
         "loginId": "from.ryw@example.com",
         "loginPw": "fromryw2025!"
       }
       ```
       | 필드명    | 타입     | 필수 여부 | 설명                                    |
       | --------- | -------- | --------- | --------------------------------------- |
       | `loginId` | `string` | Y         | 로그인 아이디                           |
       | `loginPw` | `string` | Y         | 로그인 패스워드 (솔트 적용 후 해시화됨) |

   - **응답 (Response)**
     - **200 OK**
       - 로그인 성공
       - 액세스 토큰 및 리프레시 토큰 발급 후 쿠키에 저장
       - 비밀번호(`loginPw`)는 저장 시 해시 및 솔트가 적용되어 검증됨
     - **400 Bad Request**
       - 요청 필수 값 누락
       - `loginId`, `loginPw` 중 하나라도 입력되지 않으면 발생
     - **404 Not Found**
       - 로그인 실패
       - 입력한 `loginId` 또는 `loginPw` 가 올바르지 않으면 발생

---

<br>

3. **로그인 여부 조회(`GET /api/accounts/check`)**

   - **사용자의 로그인 여부를 확인하는 API**
   - **Method:** `GET`
   - **URL:** `/api/accounts/check`
   - **인증 필요:** O (액세스 토큰 또는 리프레시 토큰 필요)
   - **요청 (Request)**

     - **Headers:**
       | Key | Value | 설명 |
       | --------------- | ----------------------------- | ------------------------- |
       | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
       | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
       | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
       | `Cookie` | `refreshToken={refreshToken}` | 리프레시 토큰 (JWT) |

   - **응답 (Response)**
     - **200 OK**
       - 로그인 상태 확인 성공
       - `isLoggedIn` 반환
     - **401 Unauthorized**
       - 토큰이 유효하지 않음
       - 액세스 토큰과 리프레시 토큰이 없거나, 만료되었거나 유효하지 않은 경우 발생

---

<br>

4. **로그아웃(`POST /api/accounts/logout`)**

   - **사용자의 로그아웃을 수행하는 API**
   - **Method:** `POST`
   - **URL:**`/api/accounts/logout`
   - **요청 (Request)**

     - **Headers:**
       | Key | Value | 설명 |
       | -------------- | ----------------------------- | ------------------------- |
       | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
       | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
       | `Cookie` | `refreshToken={refreshToken}` | 리프레시 토큰 (JWT) |

   - **응답 (Response)**
     - **200 OK**
       - 로그아웃 성공
       - 리프레시 토큰이 있다면 쿠키에서 삭제
       - 토큰 차단 테이블에 리프레시 토큰이 있는지 확인 후, 없으면 추가

---

<br>

5. **액세스 토큰 재발급(`GET /api/accounts/token`)**

   - **유효한 리프레시 토큰을 이용해 새로운 액세스 토큰을 발급하는 API**
   - **Method:** `GET`
   - **URL:** `/api/accounts/token`
   - **요청 (Request)**

     - **Headers:**
       | Key | Value | 설명 |
       | -------------- | ----------------------------- | ------------------------- |
       | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
       | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
       | `Cookie` | `refreshToken={refreshToken}` | 리프레시 토큰 (JWT) |

   - **응답 (Response)**
     - **200 OK**
       - `accessToken` 반환
       - 리프레시 토큰이 유효하면 새로운 액세스 토큰을 발급

---

<br>

### **상품(item)**

1.  **전체 상품 목록 조회(`GET /api/items`)**

    - **모든 상품 목록을 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/api/items`
    - **요청 (Request)**

      - **Headers:**
        | Key | Value | 설명 |
        | -------------- | ------------------ | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |

    - **응답 (Response)**
      - **200 OK**
        ```json
        [
          {
            "id": 1,
            "name": "CHAPTER OF YOUTH",
            "artist": "LUCY",
            "imgPath": "/img/LUCY___CHAPTER_OF_YOUTH.jpg",
            "price": 300000,
            "discountPer": 15,
            "description": "LUCY 상세 설명",
            "descriptionImgPath": "/img/LUCY___CHAPTER_OF_YOUTH_2.jpg"
          },
          {
            "id": 2,
            "name": "Colorful Express",
            "artist": "페퍼톤스",
            "imgPath": "/img/PEPPERTONES___Colorful_Express.jpg",
            "price": 400000,
            "discountPer": 20,
            "description": "페퍼톤스 상세 설명",
            "descriptionImgPath": "/img/PEPPERTONES___Colorful_Express_2.jpg"
          },
          {
            "id": 3,
            "name": "Share",
            "artist": "소란",
            "imgPath": "/img/SORAN___Share.jpg",
            "price": 500000,
            "discountPer": 25,
            "description": "소란 상세 설명",
            "descriptionImgPath": "/img/SORAN___Share_2.jpg"
          }
        ]
        ```
        | 필드명               | 타입      | 설명                |
        | -------------------- | --------- | ------------------- |
        | `id`                 | `integer` | 상품 아이디         |
        | `name`               | `string`  | 상품 이름           |
        | `artist`             | `string`  | 상품 아티스트       |
        | `imgPath`            | `string`  | 상품 사진 경로      |
        | `price`              | `integer` | 상품 가격           |
        | `discountPer`        | `integer` | 상품 할인율         |
        | `description`        | `string`  | 상품 설명           |
        | `descriptionImgPath` | `string`  | 상품 설명 사진 경로 |
        - 상품 목록 조회 성공
        - List 형태로 반환

---

<br>

2.  **특정 상품 조회(`GET /api/items/${itemId}`)**

    - **특정 상품 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/api/items/${itemId}`
    - **요청 (Request)**

      - **Headers:**
        | Key | Value | 설명 |
        | -------------- | ------------------ | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
      - **Path Variable:**
        ```json
        {
          "itemId": 1
        }
        ```
        | 필드명   | 타입      | 필수 여부 | 설명               |
        | -------- | --------- | --------- | ------------------ |
        | `itemId` | `integer` | Y         | 조회할 상품 아이디 |

    - **응답 (Response)**
      - **200 OK**
        ```json
        {
          "id": 14,
          "name": "선물",
          "artist": "백예린",
          "imgPath": "/img/YerinBaek___Present.jpg",
          "price": 700000,
          "discountPer": 35,
          "description": "백예린 리메이크 앨범 [선물]의 바이닐입니다. 싱글 커버에 가사지 1장이 포함되어 있습니다.",
          "descriptionImgPath": "/img/YerinBaek___Present_2.jpg"
        }
        ```
        | 필드명               | 타입      | 설명                |
        | -------------------- | --------- | ------------------- |
        | `id`                 | `integer` | 상품 아이디         |
        | `name`               | `string`  | 상품 이름           |
        | `artist`             | `string`  | 상품 아티스트       |
        | `imgPath`            | `string`  | 상품 사진 경로      |
        | `price`              | `integer` | 상품 가격           |
        | `discountPer`        | `integer` | 상품 할인율         |
        | `description`        | `string`  | 상품 설명           |
        | `descriptionImgPath` | `string`  | 상품 설명 사진 경로 |
        - 상품 목록 조회 성공
      - **404 Not Found**
        - 해당 상품이 존재하지 않는 경우 발생

---

<br>

### **장바구니(cart)**

1.  **로그인 회원의 장바구니 목록 조회(`GET /api/carts/items`)**

    - **로그인한 회원의 장바구니에 담긴 모든 상품 목록을 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/api/carts/items`
    - **인증 필요:** O (액세스 토큰 필요)
      - 로그인한 사용자의 장바구니 목록을 가져오기 위해 인증 필요
    - **요청 (Request)**
      - **Headers:**
        | Key | Value | 설명 |
        | --------------- | ---------------------- | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
        | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
    - **응답 (Response)**
      - **200 OK**
        ```json
        [
          {
            "id": 1,
            "name": "CHAPTER OF YOUTH",
            "artist": "LUCY",
            "imgPath": "/img/LUCY___CHAPTER_OF_YOUTH.jpg",
            "price": 300000,
            "discountPer": 15
          },
          {
            "id": 2,
            "name": "Share",
            "artist": "소란",
            "imgPath": "/img/SORAN___Share.jpg",
            "price": 500000,
            "discountPer": 25
          }
        ]
        ```
        | 필드명        | 타입      | 설명           |
        | ------------- | --------- | -------------- |
        | `id`          | `integer` | 상품 아이디    |
        | `name`        | `string`  | 상품 이름      |
        | `artist`      | `string`  | 상품 아티스트  |
        | `imgPath`     | `string`  | 상품 사진 경로 |
        | `price`       | `integer` | 상품 가격      |
        | `discountPer` | `integer` | 상품 할인율    |
        - 장바구니에 담긴 모든 상품 목록 조회 성공

---

<br>
 
2.  **로그인 회원의 장바구니에 상품 추가(`POST /api/carts`)**

    - **로그인한 회원의 장바구니에 상품을 추가하는 API**
    - **Method:** `POST`
    - **URL:** `/api/carts`
    - **인증 필요:** O (액세스 토큰 필요)
      - 로그인한 사용자의 장바구니에 추가하기 위해 인증 필요
    - **요청 (Request)**
      - **Headers:**
        | Key | Value | 설명 |
        | --------------- | ---------------------- | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
        | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
      - **Body (JSON):**
        ```json
        {
          "itemId": 1
        }
        ```
        | 필드명   | 타입      | 필수 여부 | 설명                          |
        | -------- | --------- | --------- | ----------------------------- |
        | `itemId` | `integer` | Y         | 장바구니에 추가할 상품 아이디 |

    - **응답 (Response)**
      - **200 OK**
      - 상품 추가 성공
      - 상품이 장바구니에 없으면 추가하고, 이미 존재하면 그대로 유지됨

---

<br>

3.  **로그인 회원의 장바구니에서 상품 삭제(`DELETE /api/carts/items/{itemId}`)**

    - **로그인한 회원의 장바구니에서 특정 상품을 삭제하는 API**
    - **Method:** `DELETE`
    - **URL:** `/api/carts/items/{itemId}`
    - **인증 필요:** O (액세스 토큰 필요)
      - 로그인한 사용자의 장바구니에서 삭제하기 위해 인증 필요
    - **요청 (Request)**

      - **Headers:**
        | Key | Value | 설명 |
        | --------------- | ---------------------- | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
        | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
      - **Path Variable:**
        ```json
        {
          "itemId": 1
        }
        ```
        | 필드명   | 타입      | 필수 여부 | 설명                            |
        | -------- | --------- | --------- | ------------------------------- |
        | `itemId` | `integer` | Y         | 장바구니에서 삭제할 상품 아이디 |

    - **응답 (Response)**
      - **200 OK**
        - 상품 삭제 성공

---

<br>

### **주문(order)**

1.  **로그인 회원의 전체 주문 내역 조회(`GET /api/orders`)**

    - **로그인한 회원의 전체 주문 목록을 페이지네이션하여 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/api/orders`
    - **인증 필요:** O (액세스 토큰 필요)
      - 로그인한 사용자의 주문 목록을 가져오기 위해 인증 필요
    - **페이징 지원**:
      - `page`, `size` 파라미터 사용
    - **요청 (Request)**

      - **Headers:**
        | Key | Value | 설명 |
        | --------------- | ---------------------- | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
        | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
      - **Query Parameters (페이징 지원):**
        | 필드명 | 타입 | 기본값 | 설명 |
        | ------ | --------- | ------ | ---------------------------- |
        | `page` | `integer` | `0` | 페이지 번호 (0부터 시작) |
        | `size` | `integer` | `20` | 한 페이지당 조회할 주문 개수 |

    - **응답 (Response)**
      - **200 OK**
        ```json
        {
          "content": [
            {
              "id": 2,
              "name": "SGI",
              "address": "서울특별시 **구",
              "payment": "bank",
              "createdAt": "2025-02-11T20:41:51",
              "amount": 630000
            },
            {
              "id": 1,
              "name": "RYW",
              "address": "경기도 화성시 **동",
              "payment": "card",
              "createdAt": "2025-02-11T20:41:51",
              "amount": 27500000
            }
          ],
          "totalPages": 2,
          "totalElements": 30,
          "size": 20,
          "number": 0,
          "first": true,
          "last": false
        }
        ```
        | 필드명          | 타입              | 설명                                |
        | --------------- | ----------------- | ----------------------------------- |
        | `content`       | `List<OrderRead>` | 현재 페이지의 주문 목록 (List 형태) |
        | `id`            | `integer`         | 주문 아이디                         |
        | `name`          | `string`          | 주문자명                            |
        | `address`       | `string`          | 배송 주소                           |
        | `payment`       | `string`          | 결제 수단 (예: `bank`, `card`)      |
        | `createdAt`     | `string`          | 주문 생성 일시                      |
        | `amount`        | `integer`         | 최종 결제 금액                      |
        | `totalPages`    | `integer`         | 전체 페이지 수                      |
        | `totalElements` | `integer`         | 전체 주문 개수                      |
        | `size`          | `integer`         | 현재 페이지의 최대 아이템 개수      |
        | `number`        | `integer`         | 현재 페이지 번호 (`0`부터 시작)     |
        | `first`         | `boolean`         | 첫 번째 페이지 여부                 |
        | `last`          | `boolean`         | 마지막 페이지 여부                  |
        - 주문 목록 조회 성공
        - 페이징 정보 포함 (`totalPages`, `totalElements`, `size`, `number`, `first`, `last`)

---

<br>

2.  **로그인 회원의 특정 주문 내역 조회(`GET /api/orders/{id}`)**

    - **로그인한 회원이 특정 주문 내역을 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/api/orders/{id}`
    - **인증 필요:** O (액세스 토큰 필요)
      - 로그인한 사용자의 특정 주문 내역을 가져오기 위해 인증 필요
    - **요청 (Request)**

      - **Headers:**
        | Key | Value | 설명 |
        | --------------- | ---------------------- | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
        | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
      - **Path Variable:**

        ```json
        {
          "itemId": 1
        }
        ```

        | 필드명   | 타입      | 필수 여부 | 설명               |
        | -------- | --------- | --------- | ------------------ |
        | `itemId` | `integer` | Y         | 조회할 주문 아이디 |

      - **응답 (Response)**
        - **200 OK**
          ```json
          {
            "id": 1,
            "name": "RYW",
            "address": "경기도 화성시 **동",
            "payment": "card",
            "createdAt": "2025-02-12T22:40:38",
            "amount": 630000,
            "items": [
              {
                "id": 1,
                "name": "CHAPTER OF YOUTH",
                "artist": "LUCY",
                "imgPath": "/img/LUCY___CHAPTER_OF_YOUTH.jpg",
                "price": 300000,
                "discountPer": 15
              },
              {
                "id": 2,
                "name": "Share",
                "artist": "소란",
                "imgPath": "/img/SORAN___Share.jpg",
                "price": 500000,
                "discountPer": 25
              }
            ]
          }
          ```
          | 필드명      | 타입             | 설명                           |
          | ----------- | ---------------- | ------------------------------ |
          | `id`        | `integer`        | 주문 아이디                    |
          | `name`      | `string`         | 주문자명                       |
          | `address`   | `string`         | 배송 주소                      |
          | `payment`   | `string`         | 결제 수단 (예: `bank`, `card`) |
          | `createdAt` | `string`         | 주문 생성 일시                 |
          | `amount`    | `integer`        | 최종 결제 금액                 |
          | `items`     | `List<ItemRead>` | 주문한 상품 목록               |
          - 특정 주문 내역 조회 성공
          - 주문한 상품 목록 포함
        - **404 Not Found**
          - 해당 사용자의 요청한 주문 아이디가 존재하지 않는 경우 발생

---

<br>

3.  **로그인 회원의 주문 추가(`POST /api/orders`)**

    - **로그인한 회원이 주문 생성하는 API**
    - **Method:** `POST`
    - **URL:** `/api/orders`
    - **인증 필요:** O (액세스 토큰 필요)
      - 로그인한 사용자의 주문을 생성하기 위해 인증 필요
    - **요청 (Request)**

      - **Headers:**
        | Key | Value | 설명 |
        | --------------- | ---------------------- | ------------------------- |
        | `Content-Type` | `application/json` | 요청 본문 타입 (JSON) |
        | `Accept` | `application/json` | 서버가 응답할 데이터 타입 |
        | `Authorization` | `Bearer {accessToken}` | 인증용 액세스 토큰 (JWT) |
      - **Body (JSON):**
        ```json
        {
          "name": "RYW",
          "address": "경기도 화성시 **동",
          "payment": "card",
          "cardNumber": "1234567890123456",
          "itemIds": [1, 2]
        }
        ```
        | 필드명       | 타입            | 필수 여부                          | 설명                                 |
        | ------------ | --------------- | ---------------------------------- | ------------------------------------ |
        | `name`       | `string`        | Y                                  | 주문자명                             |
        | `address`    | `string`        | Y                                  | 배송 주소                            |
        | `payment`    | `string`        | Y                                  | 결제 수단 (예: `card`, `bank`)       |
        | `cardNumber` | `string`        | N (`payment`가 `card`인 경우 필수) | 카드 결제 시 카드 번호 (암호화 저장) |
        | `itemIds`    | `List<integer>` | Y                                  | 주문할 상품 아이디 목록              |

    - **응답 (Response)**
      - **200 OK**
        ```json
        {
          "id": 123
        }
        ```
        | 필드명 | 타입      | 설명           |
        | ------ | --------- | -------------- |
        | `id`   | `integer` | 생성된 주문 ID |
        - 주문 생성 성공

<br>
<br>

## 학습 내용

학습 내용은 노션으로 정리하였습니다. 하단 개념을 클릭하면 이동합니다.

- [인터셉터](https://cerulean-log-e29.notion.site/19ae0e4e088f80fab036dec84a18e970)
- [토큰](https://cerulean-log-e29.notion.site/19ae0e4e088f8039b2dfd5cccbfb0e5b)
- [암호화 및 복호화 / 해싱](https://cerulean-log-e29.notion.site/19ae0e4e088f8003be09d9a8e634bb09?pvs=73)
- [페이지네이션](https://cerulean-log-e29.notion.site/19ae0e4e088f80ae922dfc892250a29c?pvs=73)
- [JPA Auditing](https://cerulean-log-e29.notion.site/JPA-Auditing-1ace0e4e088f80ecba05ef101134d3c6)

<br>
<br>

## 변경 이력

### [2025-02-15] - URL 구조 개선 및 상품 목록 수정

- 불필요한 `v1` 제거하여 API URL 단순화 (`/v1` → `/api/items`)
- 클래스 단위의 `@RequestMapping` 적용 (각 도메인 Controller에서 공통 URL 매핑)
- 개별 메서드의 `@GetMapping`에서 중복된 URL 제거
- 상품 목록에서 할인율이 0%일 경우, 할인율을 표시하지 않도록 수정

### [2025-02-18] - 상품 상세 페이지 추가 및 페이지 이동 기능 개선

- 상품 상세 페이지 추가
- items 테이블에 description(상품 설명), description_img_path(상품 설명 이미지) 컬럼 추가
- 특정 상품 조회 API 추가 (`GET /api/items/{itemId}`)
- 홈페이지 상품 목록에서 상품 상세 페이지로 이동하는 기능 추가
- 로그인 및 로그아웃 시 리디렉트 처리 추가
- 장바구니 페이지에서 주문 상품 클릭 시 해당 상품 상세 페이지로 이동하도록 수정
- 주문 완료 후, 해당 주문의 상세 페이지로 이동하도록 변경
- 주문 상세 페이지에서 주문 상품 클릭 시 해당 상품의 상세 페이지로 이동하도록 수정

### [2025-02-19] - 도로명 주소 API 연동

- [Daum 우편번호 서비스](https://postcode.map.daum.net/guide) 를 이용하여 도로명 주소 API 연동
- 주문하기 페이지에서 [검색] 버튼 클릭 시 도로명 주소 검색 팝업 생성
- 팝업에서 주소 선택 시 `(우편번호) 도로명 주소` 형식으로 주소 칸에 입력됨

### [2025-03-04] - JPA Auditing을 활용한 엔티티 자동 반영 기능 추가

- BaseEntity에 @PrePersist, @PreUpdate 로직 추가
- createdBy, updatedBy가 자동으로 반영되도록 변경

### [2025-03-06] - 리뷰 기능 구현 및 관련 추가 작업

- CDN 추가
	- 모달 사용을 위한 `Bootstrap JS` 추가
	- 날짜 형식 변환을 위한 `date-fns` 추가
- 리뷰 기능 구현
	- `reviews` 테이블 생성
	- 리뷰 저장 API (`POST /api/reviews`)
	- 리뷰 저장 후, 해당 `id`를 `order_item` 테이블의 `review_id`로 저장
	- 리뷰 조회 API 구현
	- 특정 상품의 전체 리뷰 조회 기능 추가
- 특정 상품 조회 API 개선 (`POST /api/items/{id}`)
	- 기존 상품 정보만 조회하던 구조에서 상품 정보 + 리뷰를 함께 조회하도록 개선
- 상품 상세 화면 수정
	- 탭 추가 (상세 정보, 리뷰)
	- 리뷰 목록 표시 기능 추가
- 주문 상세 화면 수정
	- 리뷰 모달 추가
	- 리뷰 조회 및 저장 기능 추가
