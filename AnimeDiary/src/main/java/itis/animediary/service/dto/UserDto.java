package itis.animediary.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private String photoURL;
    private Short age;
    private Boolean isNonLocked;
    private Set<AuthorityDto> authorities;
}
