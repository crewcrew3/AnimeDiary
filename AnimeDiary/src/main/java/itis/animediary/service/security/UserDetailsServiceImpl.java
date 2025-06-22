package itis.animediary.service.security;

import itis.animediary.data.entity.UserEntity;
import itis.animediary.data.repository.UserRepository;
import itis.animediary.utils.properties.ExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException, DisabledException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.BAD_CREDENTIALS));
        if (!user.getIsNonLocked()) {
            throw new DisabledException(ExceptionMessages.DISABLED_USER_ERROR);
        }
        return new UserDetailsImpl(user);
    }
}
