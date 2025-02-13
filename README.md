# LP SHOP

<br>

## 프로젝트 소개

도서 『Vue 3와 스프링 부트로 시작하는 웹 개발 철저 입문』에서 소개된 갤러리 쇼핑몰 프로젝트를 기반으로 개발한 **LP(레코드) 전문 쇼핑몰 프로젝트**입니다.

기존 프로젝트의 아키텍처와 핵심 기능을 활용하면서, LP 판매에 적합한 기능을 추가 및 확장하여 커스텀 하였습니다.

<br>
<br>

## 개발 목적

이 프로젝트는 **Vue 3, Spring Boot, JPA에 대한 학습 및 실전 활용을 목표로** 제작되었습니다.
기존 경험을 바탕으로 최신 기술을 익히고 실무에서의 활용도를 높이는 것이 핵심 목표입니다.

**개발을 진행한 주요 이유:**

1. **기술 스택 확장**
   - 기존에는 **Spring + Nexacro** 기반의 개발을 주로 진행했으나, 현재 널리 사용되는 **Spring Boot, JPA, Vue.js**에 대한 학습이 필요하다고 판단하여 진행하게 되었습니다.
2. **백엔드 환경 세팅 및 보안 처리 학습** - 실무에서는 주로 **이미 구축된 개발 환경**에서 작업했기 때문에 **Spring Boot 기반의 인증, 암호화 등 보안 처리 과정**을 직접 경험하고자 하였습니다.

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

       ![로그인 전 화면](https://private-user-images.githubusercontent.com/190806976/412922455-b4cec3df-d1e8-4f97-937c-41cdf5be2987.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTYzMjcsIm5iZiI6MTczOTQ1NjAyNywicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDU1LWI0Y2VjM2RmLWQxZTgtNGY5Ny05MzdjLTQxY2RmNWJlMjk4Ny5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDEzNDdaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05NjA5OTk3ZWJjNzI0OTk5NmM1M2NmMTVlOTQ0NjE1MTJkOTM3ZGZmMjI2Nzg4ZTc4Yzc4YzY2YTdiMzYwMzIyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.EJs3LnqIzY3wUtsZp4Gq3ROkQqjevZ4a7kEuXlfH8gY) 
     - 로그인 후 화면

       ![로그인 후 화면](https://private-user-images.githubusercontent.com/190806976/412922460-17b16d4f-50e4-43c1-a4e0-b601db6f939f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDYwLTE3YjE2ZDRmLTUwZTQtNDNjMS1hNGUwLWI2MDFkYjZmOTM5Zi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05OWM5YTcxNWRiOWRmYjI4YTViMDY1ODIxYTJmMjk3YTljNmVlZjkwZmIxNzljNThkMmI0MTliYzgxNDc1N2IyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.im6Wqm1t0takOAsygaT7wVLAiD-uca2AW7Gs7CpDEMM)

   ![홈(상품 목록) 페이지](https://private-user-images.githubusercontent.com/190806976/412922458-a4767c4d-4ff5-480b-bc24-154bf837f258.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDU4LWE0NzY3YzRkLTRmZjUtNDgwYi1iYzI0LTE1NGJmODM3ZjI1OC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1iNGMyMGZkMTU1OGE0MDFlMzYxNmIzNzk2M2VjNjQ5NTM0N2ZlOWRhYTJlNTQyYzVjMzAzZWQxZDIwMTA5MWIxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.LkryW7rqS16v2mFqEtLoCaEGNdx743xQeMrdIW2rQr0)

2. **회원가입 페이지**

   - 회원 정보 입력 후 가입 요청 \*\*\*\*(`POST /v1/api/account/join`)
   - 입력 필드: 이름, 이메일, 패스워드
   - 가입 완료 시 알림(alert) 표시 후 메인 페이지로 이동
     - 가입 완료: ‘로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?’
     - 이메일 중복: ‘이미 사용 중인 이메일입니다. 다른 값을 입력해주세요.’

   ![회원가입 페이지](https://private-user-images.githubusercontent.com/190806976/412922454-cadd0d98-3572-44c4-b762-fc3c38fb32db.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDU0LWNhZGQwZDk4LTM1NzItNDRjNC1iNzYyLWZjM2MzOGZiMzJkYi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xNGIzNzAxMWU1MTBkMDNkNjc0NGRlYTcyNzI1YjE0YjgxZDcxMmNmNDc0N2ZiNDc4MDAzMGNjYTY5YmRjN2ZiJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.IVFhnC8sU51NRnisBU_DxXqFRVU8CG0UPgLDhHfeZEM)

3. **로그인 페이지**

   - 로그인 정보 입력 후 로그인 요청 \*\*\*\*(`POST /v1/api/account/login`)
   - 입력 필드: 이메일, 패스워드
   - 로그인 완료 시 메인 페이지로 이동
     - 로그인 완료: 메인 페이지로 이동
     - 일치하는 정보가 없는 경우: ‘입력하신 정보와 일치하는 회원이 없습니다.’

   ![로그인 페이지](https://private-user-images.githubusercontent.com/190806976/412922457-08f4c4df-e2b8-4069-ba75-597487a1e3f0.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDU3LTA4ZjRjNGRmLWUyYjgtNDA2OS1iYTc1LTU5NzQ4N2ExZTNmMC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yYThjYTYzMDQ2MDk5MDMxZTZhMDA5NTc2MTZiYTdkYWRmYjVkNmM4MzI4YTNkMDFiNzlhOWUwYjdmMjlkZGNhJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.XKvwYREss7vPg8R2-QtofhfnfhCEHjPTj9azBf6gY_Q)

4. **장바구니 페이지**

   - 장바구니 전체 상품 조회 (`GET /v1/api/cart/items`)
   - 주문하기 바로가기
   - 장바구니 상품 삭제 기능
     - [x] 클릭 알림(alert) 표시. ‘선택하신 장바구니의 상품을 삭제했습니다.

   ![장바구니 페이지](https://private-user-images.githubusercontent.com/190806976/412922459-b9ac1dae-3a8c-4b0c-b717-877c1c9424d1.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDU5LWI5YWMxZGFlLTNhOGMtNGIwYy1iNzE3LTg3N2MxYzk0MjRkMS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT03ZDE5ZTYxN2FlNDE1YmI1NmVkNjc3MWExNmI2YjI2YjhmZjY0NWNlMTliMjJlZjBmYzE5ZjllMTU2OTQ5NGE3JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.joMrio_Fu7AWsOg3XOa7hUKDJVljqFKWF3uOTapFlvA)

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

   ![주문하기 페이지](https://private-user-images.githubusercontent.com/190806976/412922452-0e34d51f-b0e7-4899-a614-41e4b713658d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDUyLTBlMzRkNTFmLWIwZTctNDg5OS1hNjE0LTQxZTRiNzEzNjU4ZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01NDI3ZjZhOWM2MTBlZTZjODVkZWQ0ZjNhZmE4YjQ3MzMyNWQxYjllYmRmNDMxMTc5ZGNkZjQ4YzA0MjE2MTYzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.18eIkhbaVd5yhUvcILDTpy9kRNqsSOOq-MqbB0BQYZc)

6. **주문 내역 페이지**

   - 로그인한 회원의 주문 내역 조회 (`GET /v1/api/orders`)
   - 주문 내역 목록을 테이블 형식으로 표시
     - 주문자명, 결제 수단, 결제 금액, 결제 일시, 자세히 보기
   - 페이지네이션 적용
     - 한 페이지에 최대 20개까지 표시
     - 추가 주문 내역은 페이지네이션을 통해 조회 가능
   - [자세히 보기] 버튼 클릭 시 해당 주문의 상세 내역 페이지로 이동

   ![주문 내역 페이지](https://private-user-images.githubusercontent.com/190806976/412922456-388a631f-e0fe-4e0b-93dd-eea791572e7b.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDU2LTM4OGE2MzFmLWUwZmUtNGUwYi05M2RkLWVlYTc5MTU3MmU3Yi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1iMmU3YjZmNDgwODA2NmMwODMzNDc4OTUyMzUxY2YzODZiMTdmNTY2NDkzYzdjZmZjNTU0NWVjYmI3MjJjNDFkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.ggGcGW73d598M9LHL7uIz0GbWMhZGaoCMIIldHpe9nU)

7. **주문 상세 내역 페이지**

   - 해당 주문의 상세 내역 조회 (`GET /v1/api/orders/{id}`)
   - 주문 내용 및 주문 상품 목록 표시
     - 주문 내용: 주문 ID, 주문자명, 주소, 결제 금액, 결제 수단, 결제 일시
     - 주문 상품 목록: 상품명

   ![주문 상세 내역 페이지](https://private-user-images.githubusercontent.com/190806976/412922462-9426f6e5-1495-460d-8b2e-32c5644343f0.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDYyLTk0MjZmNmU1LTE0OTUtNDYwZC04YjJlLTMyYzU2NDQzNDNmMC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0wN2QzMzM3MWZkOWQ5Y2I1OGViZTBkMDE4NDNkZjE5ZTAyN2FlNTBlYzMwYjdiOGU4YjRjOTExMmRiNjQ2ODdlJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.0BMXW_U1lM_hhl5C3zAmmkr6lZd3zDjCarRaLZwqyzA)

<br>
<br>

## 엔티티 구조

![엔티티 구조](https://private-user-images.githubusercontent.com/190806976/412922453-c7830f17-bc75-43fe-a81e-b45f1609fc56.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk0NTY1ODksIm5iZiI6MTczOTQ1NjI4OSwicGF0aCI6Ii8xOTA4MDY5NzYvNDEyOTIyNDUzLWM3ODMwZjE3LWJjNzUtNDNmZS1hODFlLWI0NWYxNjA5ZmM1Ni5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxM1QxNDE4MDlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT00MzM5NmYzMTVhM2EzMjlhNjY1ZDI0MmM1OTI3YTBjMjg4MzA1NDRjZDQwMjcyN2E3OTMzYzBhOTQxODgyODJjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.WzpDfJ3YBP6w7hlShPnzBtuq6SHNqMAw8wmsu2U6LJw)

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
