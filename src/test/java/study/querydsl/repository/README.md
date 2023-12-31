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
