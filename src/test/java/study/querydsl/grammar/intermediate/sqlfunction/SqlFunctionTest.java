package study.querydsl.grammar.intermediate.sqlfunction;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class SqlFunctionTest {
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;
    private final List<String> memberNames = List.of("member1", "member2", "member3", "member4");

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    @DisplayName("SQL Dialect에 설정된 replace 함수를 사용할 수 있다.")
    void sqlFunctionReplace() {
        List<String> result = queryFactory
                .select(Expressions.stringTemplate("function('replace', {0}, {1}, {2})",
                        member.username, "member", "M"))
                .from(member)
                .fetch();
        List<String> replacedNames = List.of("M1", "M2", "M3", "M4");
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i)).isEqualTo(replacedNames.get(i));
            System.out.println("replacedNames.get(i) = " + replacedNames.get(i));
        }
        assertThat(result).containsExactlyElementsOf(replacedNames);
    }

    @Test
    @DisplayName("SQL Dialect에 설정된 lower 함수를 사용할 수 있다.")
    void sqlFunctionLower() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(Expressions.stringTemplate("function('lower', {0})", member.username)))
                .fetch();
        assertThat(result).containsExactlyElementsOf(memberNames);
    }

    @Test
    @DisplayName("lower처럼 ANSI 표준 함수들은 querydsl이 상당부분 내장하고 있다.")
    void sqlFunctionInnerLower() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(member.username.lower()))
                .fetch();
        assertThat(result).containsExactlyElementsOf(memberNames);
    }

    @Test
    @DisplayName("upper도 querydsl이 내장하고 있고, 대문자로 이루어진 문자열이 없으므로 결과는 빈 리스트가 반환된다.")
    void sqlFunctionInnerUpper() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(member.username.upper()))
                .fetch();
        assertThat(result).isEmpty();
    }
}
