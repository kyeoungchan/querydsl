package study.querydsl.basic;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslBulkOperatorTest {
    @PersistenceContext
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
//    @Commit
    @DisplayName("벌크 연산을 수행하면 꼭 영속성 콘텍스트를 초기화시켜야 한다.")
    void bulkUpdate() {
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();
        em.flush();
        em.clear();
        assertThat(count).isEqualTo(2);

        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();
        String notMemberName = "비회원";
        assertThat(result.get(0).getUsername()).isEqualTo(notMemberName);
        assertThat(result.get(1).getUsername()).isEqualTo(notMemberName);

    }

    @Test
//    @Commit
    @DisplayName("벌크 연산은 영속성 콘텍스트를 거치지 않고 DB에 반영되므로 flush를 하지 않으면 반영이 안 된다.")
    void bulkUpdate_not_flush() {
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();
        assertThat(count).isEqualTo(2);

        // 영속성 콘텍스트의 내용이 DB보다 우선권을 갖는다.
        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getUsername()).isEqualTo("member" + (i + 1));
        }
    }

    @Test
    void bulkAdd() {
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
        assertThat(count).isEqualTo(4);

        em.clear();
        em.flush();

        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getAge()).isEqualTo(10 * (i + 1) + 1);
        }
    }

    @Test
    void bulkMultiply() {
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.multiply(2))
                .execute();
        assertThat(count).isEqualTo(4);

        em.clear();
        em.flush();

        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getAge()).isEqualTo(20 * (i + 1));
        }
    }

    @Test
    void bulkDelete() {
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();

        assertThat(count).isEqualTo(3);

        em.clear();
        em.flush();

        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("member1");
    }
}
