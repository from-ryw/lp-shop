package fromryw.lpshop.member.entity;

import fromryw.lpshop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String loginId;

    @Column(length = 64, nullable = false)
    private String loginPw;

    @Column(length = 16, nullable = false)
    private String loginPwSalt;

    public Member(String name, String loginId, String loginPw, String loginPwSalt) {
        this.name = name;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.loginPwSalt = loginPwSalt;
    }
}
