# `MemberJpaRepository`
### `JPAQueryFactory` 주입에 관해서
- 일단 먼저 `EntityManager`는 스프링에서 주입을 해주기 때문에 바로 주입 처리만 해주면 된다.
- `JPAQueryFactory`는 `EntityManager`를 생성자로 전달하면서 생성한다.
- 하지만 다른 방법이 있는데, `JPAQueryFactory`를 아예 스프링 빈으로 등록하는 방법이다.
  - `QuerydslApplication.java`나 새로운 `Configuration` 관련 객체를 만들어서 아래처럼 등록을 해준다.
    ```java
    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
    ```
  - 그리고 리포지토리에서는 `@RequiredArgsConstructor`를 활용해서 생성자 주입 코드를 줄일 수 있다.
    ```java
    @Repository
    @RequiredArgsConstructor
    public class MemberJpaRepository {
    
        private final EntityManager em;
        private final JPAQueryFactory queryFactory;
    
    //    public MemberJpaRepository(EntityManager em) {
    //        this.em = em;
    //        this.queryFactory = new JPAQueryFactory(em);
    //    }
    
    ```
  - 어느 방식이든 장단점이 있으므로 편한 방식대로 쓰자.