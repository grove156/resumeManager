package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Authority;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.domain.enums.UserRole;
import com.projects.resumeManager.dto.request.UserCreateRequest;
import com.projects.resumeManager.repository.AuthorityRepository;
import com.projects.resumeManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //user registration process
    //1. save user in userRepository
    //2. save user's Authority in authorityRepository
    //3. return created user date
    @Transactional
    public User createUser(UserCreateRequest userCreateRequest) {

        //mapping userCreateRequest dto to User
        User user = User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .dateOfBirth(userCreateRequest.getDateOfBirthTransformed())
                .createdAt(LocalDateTime.now())
                .enable(true)
                .build();

        //save user in userRepository
        User savedUser = userRepository.save(user);

        //save user's authority date into AuthorityRepository
        Authority authority = Authority.builder()
                .email(userCreateRequest.getEmail())
                .authority(UserRole.USER)
                .build();

        authorityRepository.save(authority);

        return savedUser;
    }
}
