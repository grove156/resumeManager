package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.AuthUser;
import com.projects.resumeManager.domain.entity.Authority;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.repository.AuthorityRepository;
import com.projects.resumeManager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email + " is not found");

        }

        AuthUser authUser = AuthUser.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(getAuthorities(email))
                .isEnabled(user.isEnable())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();

        return authUser;


    }

    public Collection<GrantedAuthority> getAuthorities(String email){
        List<Authority> authList = authorityRepository.findByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority : authList) {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority().name()));
        }
        return authorities;
    }
}



