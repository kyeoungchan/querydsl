### Simple Paging Query 결과
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
- 0 page이기 때문에 offset에 대한 쿼리는 안 나가는 경우도 있다고 한다.
  - limit에 대한 쿼리만 나간다고 한다.
  - 나의 경우에는 offset이 나갔다.