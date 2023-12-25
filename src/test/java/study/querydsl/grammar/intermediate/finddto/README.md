- `findDtoByJPQL()`
  - 쿼리문
    ```sql
    /* select
            new study.querydsl.dto.MemberDto(m.username, m.age) 
        from
            Member m */ select
                m1_0.username,
                m1_0.age 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    memberDto = MemberDto(username=member1, age=10)
    memberDto = MemberDto(username=member2, age=20)
    memberDto = MemberDto(username=member3, age=30)
    memberDto = MemberDto(username=member4, age=40)
    ```
- `findDtoBySetter()`
  - 쿼리문
    ```sql
    /* select
            member1.username,
            member1.age 
        from
            Member member1 */ select
                m1_0.username,
                m1_0.age 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    memberDto = MemberDto(username=member1, age=10)
    memberDto = MemberDto(username=member2, age=20)
    memberDto = MemberDto(username=member3, age=30)
    memberDto = MemberDto(username=member4, age=40)
    ```
- `findDtoByField()`
  - 쿼리문: 위의 쿼리문과 동일
  - 콘솔 출력: 위의 콘솔 출력과 동일
  - Getter Setter 없이 필드로 바로 꽂아버리는 방식이다.
    - 그렇다고 `MemberDto`에 Setter가 없다고 필드 방식에서만 동작하는 건 아니다. 위에 있는 `bean()`을 통한 Setter 방식에도 주입은 Setter 없이도 정상적으로 잘 동작한다.
- `findUserDtoByField()`
  - 쿼리문
    ```sql
    /* select
            member1.username as name,
            member1.age 
        from
            Member member1 */ select
                m1_0.username,
                m1_0.age 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    userDto = UserDto(name=member1, age=10)
    userDto = UserDto(name=member2, age=20)
    userDto = UserDto(name=member3, age=30)
    userDto = UserDto(name=member4, age=40)
    ```
- `findUserDtoAndSubQuery()`
  - 쿼리문
    ```sql
    /* select
            member1.username as name,
            (select
                max(memberSub.age) 
            from
                Member memberSub) as age 
        from
            Member member1 */ select
                m1_0.username,
                (select
                    max(m2_0.age) 
                from
                    member m2_0) 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    userDto = UserDto(name=member1, age=40)
    userDto = UserDto(name=member2, age=40)
    userDto = UserDto(name=member3, age=40)
    userDto = UserDto(name=member4, age=40)
    ```
- `findDtoByConstructor()`
  - 쿼리문
    ```sql
    /* select
            member1.username,
            member1.age 
        from
            Member member1 */ select
                m1_0.username,
                m1_0.age 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    memberDto = MemberDto(username=member1, age=10)
    memberDto = MemberDto(username=member2, age=20)
    memberDto = MemberDto(username=member3, age=30)
    memberDto = MemberDto(username=member4, age=40)
    ```
- `findUserDtoByConstructor()`
  - 쿼리문
    ```sql
    /* select
            member1.username,
            member1.age 
        from
            Member member1 */ select
                m1_0.username,
                m1_0.age 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    userDto = UserDto(name=member1, age=10)
    userDto = UserDto(name=member2, age=20)
    userDto = UserDto(name=member3, age=30)
    userDto = UserDto(name=member4, age=40)
    ```
- `findDtoByQueryProjection()`
  - 쿼리문
    ```sql
    /* select
            member1.username,
            member1.age 
        from
            Member member1 */ select
                m1_0.username,
                m1_0.age 
            from
                member m1_0
    ```
  - 콘솔 출력
    ```text
    memberDto = MemberDto(username=member1, age=10)
    memberDto = MemberDto(username=member2, age=20)
    memberDto = MemberDto(username=member3, age=30)
    memberDto = MemberDto(username=member4, age=40)
    ```