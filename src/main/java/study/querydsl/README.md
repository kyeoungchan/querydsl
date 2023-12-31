# 실행했을 때
- main에서는 `local` 프로파일로 설정해두었으므로 아래와 같은 문구가 콘솔에 나와야한다.
  `2023-12-31T16:08:20.168+09:00  INFO 8466 --- [           main] study.querydsl.QuerydslApplication       : The following 1 profile is active: "local"`

# `InitMember`
- `init()` 메서드에서 바로 데이터를 세팅하지 않고 `InitMemberService`를 생성해서 넣는 이유
  - `@PostConstruct`와 `@Transactional`이 스프링 사이클 주기가 맞지 않으므로 같이 사용할 수 없다.
    - `@PostConstruct`를 사용할 경우 `@Transactional`과 같은 AOP 처리되지 않은 시점에 호출될 수 있기 때문에, 간혹 문제가 발생할 수 있다.
  - 참고로 `@EventListener(ApplicationReadyEvent.class)`를 사용하는 경우, AOP를 포함한 스프링 컨테이너가 완전히 초기화 된 이후에 호출되기 때문에 이런 문제가 발생하지 않는다.