package study.querydsl.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
// JPA 를 사용하기 위해서는 기본 생성자가 무조건 필요하다. 하지만 JPA 에서 생성자의 가시성을 protected 는 허용한다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// ToString 을 활용할 때는 연관관계 필드는 안 넣는 게 좋다. 서로 호출을 반복하면서 메모리 초과가 발생할 수 있기 때문이다.
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public  void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
