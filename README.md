# 개집: 개인간 집거래
기간 : 2024.05.13 ~ 2024.05.24 (2주)  
인원 : 2명(BE 1명, FE 1명)

## 기술스택

### Backend
<div style="display: flex; align-items: center;">
  <img alt="java" src="https://img.shields.io/badge/java-4E7996?style=for-the-badge&logoColor=ffffff">
  <img alt="spring boot" src="https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge&logoColor=ffffff">
  <img alt="mysql" src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logoColor=ffffff">
  <img alt="redis" src="https://img.shields.io/badge/redis-FF0000?style=for-the-badge&logoColor=ffffff">
  <img alt="mybatis" src="https://img.shields.io/badge/mybatis-0E0D0D?style=for-the-badge&logoColor=ffffff">
</div>

### Frontend
<div style="display: flex; align-items: center;">
  <img alt="javascript" src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logoColor=ffffff" style="margin-right: 80px;">
  <img alt="vue.js" src="https://img.shields.io/badge/vue.js-%234FC08D?style=for-the-badge&logoColor=ffffff" style="margin-right: 8px;">
  <img alt="primevue" src="https://img.shields.io/badge/prime--vue-%234FC08D?style=for-the-badge&logoColor=ffffff" style="margin-right: 8px;">
  <img alt="tailwind css" src="https://img.shields.io/badge/tailwind_css-06B6D4?style=for-the-badge&logoColor=ffffff" style="margin-right: 8px;">
  <img alt="kakao map" src="https://img.shields.io/badge/kakao_map-FEE500?style=for-the-badge&logoColor=ffffff" style="margin-right: 8px;">
  <img alt="tanstack query" src="https://img.shields.io/badge/tanstack_query-F33F52?style=for-the-badge&logoColor=ffffff">
</div>

## 시연 연상
[![개집: 개인간 집거래 시연](https://github.com/user-attachments/assets/06316ee7-077f-4732-809b-9f23f07fba95)](https://www.youtube.com/watch?v=w6BeNysFg-g)
## 기능 목록
<table>
  <thead>
    <tr>
      <th>요구사항 그룹</th>
      <th>기능명</th>
      <th>상세설명</th>
      <th>필수 정보</th>
      <th>선택 정보</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="4">지역</td>
      <td>시도 조회</td>
      <td>시,도를 조회한다.</td>
      <td>-</td>
      <td>-</td>
    </tr>
    <tr>
      <td>시군구 조회</td>
      <td>시,도 하위에 존재하는 시,군,구를 조회한다.</td>
      <td>시도 명</td>
      <td>-</td>
    </tr>
    <tr>
      <td>읍면동리 조회</td>
      <td>시도, 시군구 하위에 읍,면,동,리를 조회한다.</td>
      <td>시도 명, 시군구 명</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 조회</td>
      <td>시도, 시군구, 읍면동리 선택 후 나오는 아파트를 조회한다.</td>
      <td>시도 명, 시군구 명, 읍면동리 명</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="9">아파트</td>
      <td>아파트 조회</td>
      <td>위도, 경도 범위안에 있는 아파트 목록을 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 상세 조회</td>
      <td>아파트에 대한 상세정보를 조회한다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 거래내역 조회</td>
      <td>아파트의 거래 내역을 조회한다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 주변 카페 조회</td>
      <td>아파트 반경 1km 내의 카페를 조회한다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 주변 음식점 조회</td>
      <td>아파트 반경 1km 내의 음식점을 조회한다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 주변 버스정류장 조회</td>
      <td>아파트 반경 1km 내의 버스정류장을 조회한다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>아파트 가격 필터링</td>
      <td>위도, 경도 범위 내의 아파트의 가격 범위를 정해서 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>가격 최솟값, 가격 최댓값</td>
    </tr>
    <tr>
      <td>아파트 면적 필터링</td>
      <td>위도, 경도 범위 내의 아파트의 면적 범위를 정해서 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>면적 최솟값, 면적 최댓값</td>
    </tr>
    <tr>
      <td>아파트별 관심 등록된 개수</td>
      <td>회원이 관심 아파트 등록시 개수가 +1 된다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="3">상권</td>
      <td>카페 조회</td>
      <td>위도, 경도 범위 내의 카페를 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>-</td>
    </tr>
    <tr>
      <td>음식점 조회</td>
      <td>위도, 경도 범위 내의 음식정을 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>-</td>
    </tr>
    <tr>
      <td>버스 정류장 조회</td>
      <td>위도, 경도 범위 내의 버스 정류장을 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="8">매물</td>
      <td>매물 등록</td>
      <td>회원은 직접 매물을 등록한다.</td>
      <td>매물 정보 (전용면적, 방 개수, 욕실 수, 주소 등)</td>
      <td>사진(여러 개 가능)</td>
    </tr>
    <tr>
      <td>매물 수정</td>
      <td>회원은 자신이 등록한 매물을 수정한다.</td>
      <td>매물ID, 회원ID</td>
      <td>수정 정보</td>
    </tr>
    <tr>
      <td>매물 삭제</td>
      <td>회원은 자신이 등록한 매물을 삭제한다.</td>
      <td>매물ID, 회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>매물 조회</td>
      <td>위도, 경도 범위 내의 매물을 조회한다.</td>
      <td>시작위도, 종료위도, 시작경도, 종료경도</td>
      <td>-</td>
    </tr>
    <tr>
      <td>매물 상세 조회</td>
      <td>DB에 저장돼 있는 매물의 모든 정보를 확인한다.</td>
      <td>매물ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>나의 매물 목록 조회</td>
      <td>회원 자신이 등록한 매물 목록을 조회한다.</td>
      <td>회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>매물 거래상태 변경</td>
      <td>등록한 매물의 거래상태를 변경한다.</td>
      <td>매물ID, 회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>매물별 관심 등록된 개수</td>
      <td>회원이 관심 매물 등록시 개수가 +1 된다.</td>
      <td>매물ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="4">관심 아파트 관리</td>
      <td>관심 아파트 추가</td>
      <td>회원의 관심 목록에 아파트를 추가한다.</td>
      <td>아파트ID, 회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>관심 아파트 제거</td>
      <td>회원의 관심 목록에서 아파트를 제거한다.</td>
      <td>아파트ID, 회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>관심 아파트 목록 조회</td>
      <td>회원이 관심 목록에 추가한 아파트 목록을 조회한다.</td>
      <td>회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>관심 아파트 상세 조회</td>
      <td>관심 아파트 목록에서 선택한 특정 아파트를 상세 조회한다.</td>
      <td>아파트ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="4">관심 매물 관리</td>
      <td>관심 매물 추가</td>
      <td>회원의 관심 목록에 매물을 추가한다.</td>
      <td>매물ID, 회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>관심 매물 제거</td>
      <td>회원의 관심 목록에서 매물을 제거한다.</td>
      <td>매물ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>관심 매물 목록 조회</td>
      <td>회원의 관심 목록에 추가한 매물을 조회한다.</td>
      <td>매물ID, 회원ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>관심 매물 상세 조회</td>
      <td>관심 매물 목록에서 선택한 특정 매물을 상세조회한다.</td>
      <td>매물ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="3">회원 관리</td>
      <td>회원가입</td>
      <td>회원 가입으로 가입시 사용자로 가입된다.</td>
      <td>이름, 아이디, 비밀번호, 전화번호, 이메일</td>
      <td>-</td>
    </tr>
    <tr>
      <td>내 정보 조회</td>
      <td>세션을 사용해 로그인한 회원의 정보를 조회한다.</td>
      <td>세션</td>
      <td>-</td>
    </tr>
    <tr>
      <td>내 정보 수정</td>
      <td>로그인한 회원의 정보를 조회한다.</td>
      <td>회원ID</td>
      <td>이름, 비밀번호, 전화번호, 이메일</td>
    </tr>
    <tr>
      <td rowspan="3">로그인 관리</td>
      <td>로그인</td>
      <td>아이디, 비밀번호가 일치하면 로그인한다.</td>
      <td>아이디, 비밀번호</td>
      <td>-</td>
    </tr>
    <tr>
      <td>로그아웃</td>
      <td>로그인 중인 세션이 확인되면 로그아웃한다.</td>
      <td>세션</td>
      <td>-</td>
    </tr>
    <tr>
      <td>비밀번호 찾기</td>
      <td>아이디와 이름을 입력받아 사용자 정보에 있는 이메일로 비밀번호를 전송한다.</td>
      <td>이름, 아이디</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="5">공지사항 관리</td>
      <td>공지사항 작성</td>
      <td>관리자는 공지사항을 등록한다.</td>
      <td>제목, 내용</td>
      <td>-</td>
    </tr>
    <tr>
      <td>공지사항 수정</td>
      <td>관리자는 등록된 공지사항을 수정한다.</td>
      <td>제목, 내용</td>
      <td>-</td>
    </tr>
    <tr>
      <td>공지사항 삭제</td>
      <td>관리자는 공지사항을 삭제한다.</td>
      <td>공지사항ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td>공지사항 목록 조회</td>
      <td>등록된 공지사항 목록을 조회한다.</td>
      <td>-</td>
      <td>-</td>
    </tr>
    <tr>
      <td>공지사항 상세 조회</td>
      <td>특정 공지사항을 상세조회한다.</td>
      <td>공지사항ID</td>
      <td>-</td>
    </tr>
    <tr>
      <td rowspan="4">데이터 수집</td>
      <td>아파트 실거래가 정보 수집</td>
      <td>공공데이터 포털: 국토교통부_아파트매매 실거래 상세 자료 API 활용</td>
      <td>-</td>
      <td>-</td>
    </tr>
    <tr>
      <td>구미시 음식점 정보 수집</td>
      <td>공공데이터 포털: 경상북도 구미시_일반 음식점 CSV 활용</td>
      <td>-</td>
      <td>-</td>
    </tr>
    <tr>
      <td>구미시 카페 정보 수집</td>
      <td>공공데이터 포털: 경상북도 구미시_관내 카페 현황 CSV 활용</td>
      <td>-</td>
      <td>-</td>
    </tr>
    <tr>
      <td>구미시 버스정류장 정보 수집</td>
      <td>공공데이터 포털: 경상북도 구미시_버스 정류장 CSV 활용</td>
      <td>-</td>
      <td>-</td>
    </tr>
  </tbody>
</table>
