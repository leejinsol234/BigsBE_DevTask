# Bigs Back-End 개발자 실습 테스트
## 특정 지역의 단기 예보 확인을 위한 API 작성
### 작성자 : 이진솔

1. 기술 스택
  - JAVA
  - SpringBoot
  - MySQL
  - JPA
    
2. 구현 대상 API
   1) 단기 예보를 DB에 저장하게 하는 API
   - 실행 시 결과(POST 요청 시 공공데이터 API를 호출하여 DB에 적재)
    ![스크린샷 2024-05-08 113327](https://github.com/leejinsol234/BigsBE_DevTask/assets/140874690/b7ca3c74-6f68-4e24-8868-7c64adeed3d0)
    ![스크린샷 2024-05-08 113501](https://github.com/leejinsol234/BigsBE_DevTask/assets/140874690/8a1ee815-8a93-46f5-87a1-ff57b78c8f28)
    ![스크린샷 2024-05-08 112414](https://github.com/leejinsol234/BigsBE_DevTask/assets/140874690/f2f72f5a-b71c-4440-9093-50c9ba3aaca0)
   2) 단기 예보를 조회하는 API
   - 실행 시 결과(GET 요청 시 DB에 저장된 데이터를 조회)
    ![스크린샷 2024-05-08 113615](https://github.com/leejinsol234/BigsBE_DevTask/assets/140874690/95325b11-7641-42f1-b24d-5f06867df40a)
   - 실행 시 결과(데이터가 없을 경우 204 error 응답)
    ![스크린샷 2024-05-08 120104](https://github.com/leejinsol234/BigsBE_DevTask/assets/140874690/a13b54e3-0286-421a-8644-9911861b54a7)


    
