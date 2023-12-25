- `subQuery()` 쿼리문
  ```sql
    /* select
        member1
    from
        Member member1
    where
        member1.age = (
            select
                max(memberSub.age)
            from
                Member memberSub
        ) */
    select
        m1_0.member_id,
        m1_0.age,
        m1_0.team_id,
        m1_0.username
    from
        member m1_0
    where
        m1_0.age=(
            select
                max(m2_0.age)
            from
                member m2_0
        )
    ```
- `subQueryGoe()` 쿼리문
  ```sql
  /* select
          member1 
      from
          Member member1 
      where
          member1.age >= (
              select
                  avg(memberSub.age) 
              from
                  Member memberSub
          ) */ select
              m1_0.member_id,
              m1_0.age,
              m1_0.team_id,
              m1_0.username 
          from
              member m1_0 
          where
              m1_0.age>=(
                  select
                      avg(cast(m2_0.age as float(53))) 
                  from
                      member m2_0
              )
  ```
- `subQueryIn()` 쿼리문
  ```sql
      /* select
          member1 
      from
          Member member1 
      where
          member1.age in (select
              memberSub.age 
          from
              Member memberSub 
          where
              memberSub.age > ?1) */ select
              m1_0.member_id,
              m1_0.age,
              m1_0.team_id,
              m1_0.username 
          from
              member m1_0 
          where
              m1_0.age in (select
                  m2_0.age 
              from
                  member m2_0 
              where
                  m2_0.age>?)
  ```
- `selectSubQuery()`
  - 쿼리문
    ```sql
    /* select
            member1.username,
            (select
                avg(memberSub.age) 
            from
                Member memberSub) 
        from
            Member member1 */ select
                m1_0.username,
                (select
                    avg(cast(m2_0.age as float(53))) 
                from
                    member m2_0) 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    username = member1
    age = 25.0
    username = member2
    age = 25.0
    username = member3
    age = 25.0
    username = member4
    age = 25.0
    ```
- from 절의 서브쿼리
  - JPA JPQL 서브쿼리의 한계점으로 from 절의 서브쿼리는 지원하지 않는다.
  - 해결 방안
    1. 서브쿼리를 join으로 변경한다. (가능한 상황도 있고, 불가능한 상황도 있다.)
    2. 애플리케이션에서 쿼리를 2번 분리해서 실행한다.
    3. nativeSQL을 사용한다.