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
    