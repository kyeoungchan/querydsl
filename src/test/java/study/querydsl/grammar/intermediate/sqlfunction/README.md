SQL Function은  `org.hibernate.dialect.H2Dialect`에 설정된 함수를 꺼내서 사용할 수 있다.

- `sqlFunctionReplace()`
  - 쿼리문
    ```sql
    /* select
            function('replace', member1.username, ?1, ?2) 
        from
            Member member1 */select
                replace(m1_0.username, ?, ?) 
            from
                member m1_0
    ```
- `sqlFunctionLower()`
  - 쿼리문
  ```sql
   /* select
          member1.username 
      from
          Member member1 
      where
          member1.username = function('lower', member1.username) */
  select m1_0.username
  from member m1_0
  where m1_0.username = lower(m1_0.username)
  ```
- `sqlFunctionInnerUpper()`
  - 쿼리문
  ```sql
  /* select
          member1.username 
      from
          Member member1 
      where
          member1.username = upper(member1.username) */
  select m1_0.username
  from member m1_0
  where m1_0.username = upper(m1_0.username)
  ```
