package study.querydsl.basic;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class DynamicQueryTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

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
    void dynamicQuery_BooleanBuilder() {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo(usernameParam);
        assertThat(result.get(0).getAge()).isEqualTo(ageParam);
    }

    @Test
    @DisplayName("ageParam으로 null이 입력되면 동적쿼리 결과는 username 조건으로만 쿼리를 한다.")
    void dynamicQuery_BooleanBuilder_age_null() {
        String usernameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember1(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo(usernameParam);
        assertThat(result.get(0).getAge()).isEqualTo(10);
    }

    @Test
    @DisplayName("동적쿼리 조건으로 null을 허용하지 않고 싶은 경우에는 생성자로 주입하면 되고, null이 입력되면 컴파일 에러 및 IllegalArgumentException 발생")
    void dynamicQuery_BooleanBuilder_not_null() {
//        new BooleanBuilder(member.username.eq(null)); // 컴파일 에러
        String usernameParam = null;
        assertThatThrownBy(() -> new BooleanBuilder(member.username.eq(usernameParam)))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }
        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }
}
