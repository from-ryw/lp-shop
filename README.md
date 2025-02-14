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
   - **상품 관리**: LP 상품 목록 조회, ~~상품 상세 페이지 (추후 추가 예정)~~
   - **장바구니 기능**: 로그인한 회원 장바구니에 상품 추가 및 삭제 가능
   - **주문 관리**: 주문 내역 조회 및 신규 주문 추가
2.  개선 및 추가 기능 계획
   - **사용자 기능 강화**
     - **상품 상세 페이지 추가** – 상품의 상세 정보 페이지 구현 (리뷰 및 평점 기능 연계)<br>
     - **검색 및 필터 기능 추가** – 아티스트 검색 및 필터링 기능 구현<br>
     - **리뷰 및 평점 시스템 도입** – 사용자가 상품 리뷰 및 평점을 작성할 수 있도록 기능 추가<br>
   - **관리자 기능 추가**
     - **관리자 기능 확장** – 상품 및 주문을 관리할 수 있는 기능 추가
     - **관리자 대시보드 추가** – 매출 데이터 및 사용자 관리 기능 제공
   - **보안 및 서비스 안정성 개선**
     - **생성자 및 수정자 필드 추가** – 생성자(createdBy) 및 수정자(updatedBy) 저장 기능 구현
     - **예외 및 유효성 처리 보강** – 현재 미흡한 예외 및 유효성 로직을 개선
     - **주소 API 연동** – 행정안전부 도로명 주소 API를 활용한 주소 자동 입력 기능 추가

<br>
<br>

## 화면 구성 & 페이지별 기능

1. **홈(상품 목록) 페이지**

   - 전체 상품 조회 (`GET /v1/api/items`)
   - 로그인 바로가기
   - 회원가입 바로가기
   - 주문 내역 바로가기
   - 장바구니 바로가기
   - 로그아웃 기능 (`POST /api/account/logout`)
   - 장바구니 담기 기능 (`POST /v1/api/carts`)
     - [장바구니 담기] 버튼 클릭 알림(alert) 표시
       - 로그인 전: ‘로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?’
       - 로그인 후: ‘장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?’
   - 로그인 여부에 따라 상단 네비게이션 바 변경 (`accountStore.loggedIn` 활용)
     - 로그인 전 화면

       ![로그인 전 화면](https://postfiles.pstatic.net/MjAyNTAyMTVfMTAz/MDAxNzM5NTQ2NDk2NDkz.RxMJbbXvqr5lGy-9f91fXqMSGbv-etpWsPRO3G14cMUg.68ZVM99oyjKabAkhdp0mh4wd-KfE_WUtlAWGGSsK2Xog.PNG/%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%A0%84.png?type=w3840) 
     - 로그인 후 화면

       ![로그인 후 화면](https://postfiles.pstatic.net/MjAyNTAyMTVfOTIg/MDAxNzM5NTQ2NDk2NDky.6SUqQofSzryYmrQLDvkfVsOBHMbviKNNeQYxiApLV8wg.Czptt30aJ5snvO9ATg7KErPvXkEdQ_7tileGkd_gK0Ig.PNG/%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%9B%84.png?type=w966)

   ![홈(상품 목록) 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMjMy/MDAxNzM5NTQ2NDk2ODgz.spF6PnbYrbC0s0Xir3z1FSq0bUsT4O8yc5hVtk3Bl30g.L8hkx_DTrYe4JL5H117JxXC1monrtaJ_9ZOgITzjuvQg.PNG/%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%80.png?type=w966)

2. **회원가입 페이지**

   - 회원 정보 입력 후 가입 요청 (`POST /v1/api/account/join`)
   - 입력 필드: 이름, 이메일, 패스워드
   - 가입 완료 시 알림(alert) 표시 후 메인 페이지로 이동
     - 가입 완료: ‘로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?’
     - 이메일 중복: ‘이미 사용 중인 이메일입니다. 다른 값을 입력해주세요.’

   ![회원가입 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMjMx/MDAxNzM5NTQ2NDk2NzYw.d3x-btSIg0AdXNFVsQVFsCkV4Y6hDVzkIQlCu25-sAcg.lXzNzFcLO5iLwBjt6203-aQQaFVoFlJIo_eUWGSUVk8g.PNG/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.png?type=w966)

3. **로그인 페이지**

   - 로그인 정보 입력 후 로그인 요청 (`POST /v1/api/account/login`)
   - 입력 필드: 이메일, 패스워드
   - 로그인 완료 시 메인 페이지로 이동
     - 로그인 완료: 메인 페이지로 이동
     - 일치하는 정보가 없는 경우: ‘입력하신 정보와 일치하는 회원이 없습니다.’

   ![로그인 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMTc0/MDAxNzM5NTQ2NDk2NDk2.lAKBsIU71s3QG4ycEmqRXj3yTqXsw6hRlOHUmWdMgDMg.tAKtKk52niMo4GfAVLB9lsbOdmgGZDVfkvyS813RDPIg.PNG/%EB%A1%9C%EA%B7%B8%EC%9D%B8.png?type=w966)

4. **장바구니 페이지**

   - 장바구니 전체 상품 조회 (`GET /v1/api/cart/items`)
   - 주문하기 바로가기
   - 장바구니 상품 삭제 기능
     - [x] 클릭 알림(alert) 표시. ‘선택하신 장바구니의 상품을 삭제했습니다.

   ![장바구니 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfNTQg/MDAxNzM5NTQ2NDk2NTI0.AknqgYs7KTI_AxDMIJhZjp_Z9azoulq1QtSy9hDBrnkg.js8s5Ez94iJDWdi0xVAt_cVHFw9PphAeVgf0iNeTJqEg.PNG/%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88.png?type=w966)

5. **주문하기 페이지**

   - 주문 정보 입력 후 주문 요청 (`POST /v1/api/orders`)
   - 입력 필드:
     - 주문자 정보: 이름, 주소
     - 결제 수단: 신용카드 / 무통장입금 선택
     - 카드 결제 선택 시 카드 번호 입력 필드 활성화
   - 주문 상품 목록 표시: 선택한 상품과 총 결제 금액을 보여줌
     - 주문 상품 목록 조회 (`GET /v1/api/cart/items`)
   - [결제하기] 버튼 클릭 시 주문 완료 및 알림(alert) 표시 후 메인 페이지로 이동
     - 결제 수단 ‘신용카드’ 선택 시
       - ’주문이 완료되었습니다.’
     - 결제 수단 ‘무통장입금’ 선택 시
       - ‘주문이 완료되었습니다. 한국은행 123-456789-777 계좌로 ${price}원을 입금해주시기 바랍니다.’ 문구 추가

   ![주문하기 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfOTQg/MDAxNzM5NTQ2NDk2Njkx.N-VFrcFWZjrv3B635eEN5LkWshaphW1H5urEZYhxDeUg._sBjG94b8lfMzXAPAg6ZyR1gZZu8ryWl_wbXEfg1PJ0g.PNG/%EC%A3%BC%EB%AC%B8%ED%95%98%EA%B8%B0.png?type=w966)

6. **주문 내역 페이지**

   - 로그인한 회원의 주문 내역 조회 (`GET /v1/api/orders`)
   - 주문 내역 목록을 테이블 형식으로 표시
     - 주문자명, 결제 수단, 결제 금액, 결제 일시, 자세히 보기
   - 페이지네이션 적용
     - 한 페이지에 최대 20개까지 표시
     - 추가 주문 내역은 페이지네이션을 통해 조회 가능
   - [자세히 보기] 버튼 클릭 시 해당 주문의 상세 내역 페이지로 이동

   ![주문 내역 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMTIx/MDAxNzM5NTQ2NDk2Njk0.8g1jsiOBv3K9MYXukw_NwWcAV2p9HB3BSVmhm2L4lBYg.vL16sgUvK0Y3LxEL1eJVfxG7sSioBsrAv9X4GLRZVHwg.PNG/%EC%A3%BC%EB%AC%B8%EB%82%B4%EC%97%AD.png?type=w3840)

7. **주문 상세 내역 페이지**

   - 해당 주문의 상세 내역 조회 (`GET /v1/api/orders/{id}`)
   - 주문 내용 및 주문 상품 목록 표시
     - 주문 내용: 주문 ID, 주문자명, 주소, 결제 금액, 결제 수단, 결제 일시
     - 주문 상품 목록: 상품명

   ![주문 상세 내역 페이지](https://postfiles.pstatic.net/MjAyNTAyMTVfMTMz/MDAxNzM5NTQ2NDk2NTE5.EuTwbY8y1KGhDEerPfUYDAWCuajBRX3hHqkNz01l4N4g.KwLzIAxcBaD5iqcAfoizHbl2ohSHSPfyN7KoxUL7aFkg.PNG/%EC%A3%BC%EB%AC%B8%EC%83%81%EC%84%B8%EB%82%B4%EC%97%AD.png?type=w966)

<br>
<br>

## 엔티티 구조

![엔티티 구조](https://postfiles.pstatic.net/MjAyNTAyMTVfMjAx/MDAxNzM5NTQ2NDk2NTMy.qIedZng260pQOGrS0Zuc9WI6jCyYJGG7-NbZXSoTPtsg.RVDoySboWOFa7ISumBnG2i8XivKpl1WkU0u0eq_Prx8g.PNG/%EC%97%94%ED%8B%B0%ED%8B%B0%EA%B5%AC%EC%A1%B0.png?type=w966)

<br>
<br>

## API

### **회원(member) & 계정(account)**

1. **회원 가입 API (`POST /v1/api/account/join`)**

   - **사용자가 회원 가입을 진행하는 API**
   - **Method:** `POST`
   - **URL:** `/v1/api/account/join`
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


2. **로그인 API (`POST /v1/api/account/login`)**

   - **사용자가 아이디와 비밀번호를 입력하여 로그인하는 API**
   - **Method:** `POST`
   - **URL:** `/v1/api/account/login`
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

3. **로그인 여부 조회(`GET /v1/api/account/check`)**

   - **사용자의 로그인 여부를 확인하는 API**
   - **Method:** `GET`
   - **URL:** `/v1/api/account/check`
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

4. **로그아웃(`POST /v1/api/account/logout`)**

   - **사용자의 로그아웃을 수행하는 API**
   - **Method:** `POST`
   - **URL:**`/v1/api/account/logout`
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

5. **액세스 토큰 재발급(`GET /v1/api/account/token`)**

   - **유효한 리프레시 토큰을 이용해 새로운 액세스 토큰을 발급하는 API**
   - **Method:** `GET`
   - **URL:** `/v1/api/account/token`
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

1.  **전체 상품 목록 조회(`GET /v1/api/items`)**

    - **모든 상품 목록을 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/v1/api/items`
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
            "discountPer": 15
          },
          {
            "id": 2,
            "name": "Colorful Express",
            "artist": "페퍼톤스",
            "imgPath": "/img/PEPPERTONES___Colorful_Express.jpg",
            "price": 400000,
            "discountPer": 20
          },
          {
            "id": 3,
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
        - 상품 목록 조회 성공
        - List 형태로 반환

---

<br>

### **장바구니(cart)**

1.  **로그인 회원의 장바구니 목록 조회(`GET /v1/api/cart/items`)**

    - **로그인한 회원의 장바구니에 담긴 모든 상품 목록을 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/v1/api/cart/items`
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
 
2.  **로그인 회원의 장바구니에 상품 추가(`POST /v1/api/carts`)**

    - **로그인한 회원의 장바구니에 상품을 추가하는 API**
    - **Method:** `POST`
    - **URL:** `/v1/api/carts`
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

3.  **로그인 회원의 장바구니에서 상품 삭제(`DELETE /v1/api/cart/items/{itemId}`)**

    - **로그인한 회원의 장바구니에서 특정 상품을 삭제하는 API**
    - **Method:** `DELETE`
    - **URL:** `/v1/api/cart/items/{itemId}`
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

1.  **로그인 회원의 전체 주문 내역 조회(`GET /v1/api/orders`)**

    - **로그인한 회원의 전체 주문 목록을 페이지네이션하여 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/v1/api/orders`
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

2.  **로그인 회원의 특정 주문 내역 조회(`GET /v1/api/orders/{id}`)**

    - **로그인한 회원이 특정 주문 내역을 조회하는 API**
    - **Method:** `GET`
    - **URL:** `/v1/api/orders/{id}`
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

3.  **로그인 회원의 주문 추가(`POST /v1/api/orders`)**

    - **로그인한 회원이 주문 생성하는 API**
    - **Method:** `POST`
    - **URL:** `/v1/api/orders`
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
        - 주문 생성 성공

<br>
<br>

## 학습 내용

학습 내용은 노션으로 정리하였습니다. 하단 개념을 클릭하면 이동합니다.

- [인터셉터](https://cerulean-log-e29.notion.site/199e0e4e088f80a38624f6072a626bd2?pvs=73)
- [토큰](https://cerulean-log-e29.notion.site/199e0e4e088f8079902df10db200050c?pvs=73)
- [암호화 및 복호화 / 해싱](https://cerulean-log-e29.notion.site/199e0e4e088f80b38a16cf8be5eb9e07?pvs=73)
- [페이지네이션션](https://cerulean-log-e29.notion.site/199e0e4e088f80c3a4dbc0caf3d70446?pvs=73)
