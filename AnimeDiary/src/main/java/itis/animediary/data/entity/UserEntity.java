package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "\"users\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_nickname", nullable = false, length = 30)
    private String nickname;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Builder.Default
    @Column(name = "user_photo_url", nullable = false)
    private String photoURL = "default_avatar.jpg";

    @Column(name = "user_age")
    private Short age;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Builder.Default
    @Column(name = "is_non_locked", nullable = false)
    private Boolean isNonLocked = true;

    //fetch = FetchType.EAGER: Указывает, что связанные сущности должны загружаться немедленно (жадная загрузка) вместе с основной сущностью.
    //Это означает, что при загрузке пользователя будут загружены и все его роли.
    //MERGE - при обновлении пользователя обновляются и его роли
    //PERSIST - при сохранении пользователя сохраняются и новые роли
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "\"user\""),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();

    public void addAuthority(AuthorityEntity authority) {
        authorities.add(authority);
    }
}
