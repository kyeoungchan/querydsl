# `application.yml`
- `spring: profiles: active: test`
    - 샘플 데이터를 추가할 때 테스트 케이스 실행에는 영향을 주지 않기 위해 포로파일을 분리하는 과정이다.
    - 따라서 main 디렉토리에는 `local`로 프로파일을 작성하였고, 여기에서는 `test`로 프로파일을 작성하여 분리하였다. 
    - [main으로 가보기](https://github.com/kyeoungchan/querydsl/tree/main/src/main/resources)