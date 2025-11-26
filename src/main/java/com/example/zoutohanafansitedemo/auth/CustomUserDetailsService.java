package com.example.zoutohanafansitedemo.auth;

import com.example.zoutohanafansitedemo.entity.enums.UserRole;
import com.example.zoutohanafansitedemo.entity.enums.UserStatus;
import com.example.zoutohanafansitedemo.entity.user.User;
import com.example.zoutohanafansitedemo.repository.AdminUserRepository;
import com.example.zoutohanafansitedemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminUserRepository adminUserRepository;

    public CustomUserDetailsService(UserRepository userRepository, AdminUserRepository adminUserRepository) {
        this.userRepository = userRepository;
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = adminUserRepository.selectAdminUserByLoginId(loginId);

        if(user != null){
            user.setRole(UserRole.ROLE_ADMIN);
            user.setStatus(UserStatus.ACTIVE);
            return new CustomUserDetails(user, UserRole.ROLE_ADMIN);
        }

        user = userRepository.selectUserByLoginId(loginId);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        user.setRole(UserRole.ROLE_USER);
        return new CustomUserDetails(user,  UserRole.ROLE_USER);
    }
}
