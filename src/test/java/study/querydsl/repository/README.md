### `extracting()`
- `assertThat()` 뒤에 `extracting()`을 활용하여 확인하고자하는 필드만 접근이 가능하다.
- 즉, `assertThat(result.get(0).getUsername()).isEqualTo(useranme)` 이런식으로 안 해도 된다는 뜻이다.

### `searchTest()`
- 쿼리문
    ```sql
    /* select
            member1.id as memberId,
            member1.username,
            member1.age,
            team.id as teamId,
            team.name as teamName 
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
### `searchTestOnlyTeamName()`
- 쿼리문
    ```sql
    /* select
            member1.id as memberId,
            member1.username,
            member1.age,
            team.id as teamId,
            team.name as teamName 
        from
            Member member1   
        left join
            member1.team as team 
        where
            team.name = ?1 */ select
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
    ```
