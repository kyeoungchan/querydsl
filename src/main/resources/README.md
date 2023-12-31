# `application.yml`
- `spring: profiles: active: local`
  - 샘플 데이터를 추가할 때 테스트 케이스 실행에는 영향을 주지 않기 위해 포로파일을 분리하는 과정이다.
  - 따라서 main 디렉토리뿐 아니라 test 디렉토리에서도 프로파일을 분리 생성해줘야한다.