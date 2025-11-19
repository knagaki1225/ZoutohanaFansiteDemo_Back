package com.example.zoutohanafansitedemo.auth;

import com.example.zoutohanafansitedemo.entity.user.User;
import com.example.zoutohanafansitedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.selectUserByLoginId(loginId);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user);
    }
}
