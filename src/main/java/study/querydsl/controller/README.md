# `MemberController`
- 간단히 실행 결과를 확인하기 위해서 postman을 활용하였다.
- request: `http://localhost:8080/v1/members?teamName=teamB&ageGoe=31&ageLoe=35`
  - postman 조회 결과
    ```json
    [
        {
            "memberId": 32,
            "username": "member31",
            "age": 31,
            "teamId": 2,
            "teamName": "teamB"
        },
        {
            "memberId": 34,
            "username": "member33",
            "age": 33,
            "teamId": 2,
            "teamName": "teamB"
        },
        {
            "memberId": 36,
            "username": "member35",
            "age": 35,
            "teamId": 2,
            "teamName": "teamB"
        }
    ]
    ```
  - 쿼리문
      ```sql
      /* select
              member1.id,
              member1.username,
              member1.age,
              team.id,
              team.name 
          from
              Member member1   
          left join
              member1.team as team 
          where
              team.name = ?1 
              and member1.age >= ?2 
              and member1.age <= ?3 */ select
                  m1_0.member_id,
                  m1_0.username,
                  m1_0.age,
                  m1_0.team_id,
                  t1_0.name 
              from
                  member m1_0 
              left join
                  team t1_0 
                      on t1_0.team_id=m1_0.team_id 
              where
                  t1_0.name=? 
                  and m1_0.age>=? 
                  and m1_0.age<=?
      ```
- request: `http://localhost:8080/v1/members?teamName=teamB&ageGoe=31&ageLoe=35&username=member31`
  - postman 조회 결과
    ```json
    [
      {
          "memberId": 32,
          "username": "member31",
          "age": 31,
          "teamId": 2,
          "teamName": "teamB"
      }
    ]
    ```
  - 쿼리문
    ```sql
    /* select
            member1.id,
            member1.username,
            member1.age,
            team.id,
            team.name 
        from
            Member member1   
        left join
            member1.team as team 
        where
            member1.username = ?1 
            and team.name = ?2 
            and member1.age >= ?3 
            and member1.age <= ?4 */ select
                m1_0.member_id,
                m1_0.username,
                m1_0.age,
                m1_0.team_id,
                t1_0.name 
            from
                member m1_0 
            left join
                team t1_0 
                    on t1_0.team_id=m1_0.team_id 
            where
                m1_0.username=? 
                and t1_0.name=? 
                and m1_0.age>=? 
                and m1_0.age<=?
    ```
- request: `http://localhost:8080/v2/members?size=5&page=2
  - postman 조회 결과
    ```json
    {
        "content": [
            {
                "memberId": 11,
                "username": "member10",
                "age": 10,
                "teamId": 1,
                "teamName": "teamA"
            },
            {
                "memberId": 12,
                "username": "member11",
                "age": 11,
                "teamId": 2,
                "teamName": "teamB"
            },
            {
                "memberId": 13,
                "username": "member12",
                "age": 12,
                "teamId": 1,
                "teamName": "teamA"
            },
            {
                "memberId": 14,
                "username": "member13",
                "age": 13,
                "teamId": 2,
                "teamName": "teamB"
            },
            {
                "memberId": 15,
                "username": "member14",
                "age": 14,
                "teamId": 1,
                "teamName": "teamA"
            }
        ],
        "pageable": {
            "pageNumber": 2,
            "pageSize": 5,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 10,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalPages": 20,
        "totalElements": 100,
        "first": false,
        "size": 5,
        "number": 2,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "numberOfElements": 5,
        "empty": false
    }
    ```
  - 쿼리 결과
  ```sql
  /* select
          count(member1) 
      from
          Member member1   
      left join
          member1.team as team */ select
              count(m1_0.member_id) 
          from
              member m1_0
  ```
  ```sql
  /* select
          member1.id,
          member1.username,
          member1.age,
          team.id,
          team.name 
      from
          Member member1   
      left join
          member1.team as team */ select
              m1_0.member_id,
              m1_0.username,
              m1_0.age,
              m1_0.team_id,
              t1_0.name 
          from
              member m1_0 
          left join
              team t1_0 
                  on t1_0.team_id=m1_0.team_id 
          offset
              ? rows 
          fetch
              first ? rows only
  ```
  - 5개씩 조회가 되면서 페이지 기능이 작동되는 것을 확인할 수 있다.
- request: `http://localhost:8080/v3/members?size=110&page=0`
  - 이렇게 데이터가 총 100개인데 100개보다 더 많은 페이징 쿼리를 날리게 된다면, 스프링이 알아서 카운트 쿼리는 안 날리게 해준다.