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
- `selectSubQuery()` 쿼리문
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