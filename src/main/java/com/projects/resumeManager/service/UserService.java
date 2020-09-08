package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Authority;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.domain.enums.UserRole;
import com.projects.resumeManager.dto.request.UserCreateRequest;
import com.projects.resumeManager.dto.request.UserUpdateRequest;
import com.projects.resumeManager.dto.response.UserDetailResponse;
import com.projects.resumeManager.repository.AuthorityRepository;
import com.projects.resumeManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //1. get user by user id
    //2. return user found by id, or if user is null, throw user not found exception;
    public UserDetailResponse getUserDetail(Long id) throws Exception {
        //TODO: make userNotFoundException(custom) and change Exception to userNotFoundException(custom)
        //find user with user id
        User user = userRepository.findById(id).orElseThrow(()->new Exception());

        //map found user with user detail response object
        UserDetailResponse userDetail = UserDetailResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .build();

        return userDetail;
    }


    //1. save user in userRepository
    //2. save user's Authority in authorityRepository
    //3. return created user date
    @Transactional
    public User createUser(UserCreateRequest userCreateRequest) {
        //TODO: email duplication validation

        //mapping userCreateRequest dto to User
        User user = User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .name(userCreateRequest.getName())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .dateOfBirth(dateTransformer(userCreateRequest.getDateOfBirth()))
                .createdAt(LocalDateTime.now())
                .enable(true)
                .build();

        //save user in userRepository
        User savedUser = userRepository.save(user);

        //save user's authority date into AuthorityRepository
        Authority authority = Authority.builder()
                .email(userCreateRequest.getEmail())
                .authority(UserRole.ROLE_USER)
                .build();

        authorityRepository.save(authority);

        return savedUser;
    }

    //1. find user with user id
    //2. set user properties as userUpdatedRequest
    //3. save updated user properties
    @Transactional
    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) throws Exception {
        //TODO: make userNotFoundException(custom) and change Exception to userNotFoundException(custom)

        //find user by user id
        User user = userRepository.findById(id).orElseThrow(()->new Exception());

        //set found user properties as requested user details
        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        user.setName(userUpdateRequest.getName());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setDateOfBirth(dateTransformer(userUpdateRequest.getDateOfBirth()));

        //save updated user properties
        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    //Transfer String dateOfBirth type to LocalDate
    private LocalDate dateTransformer(String dateOfBirth){
        Integer day = Integer.parseInt(dateOfBirth.substring(0,2));
        Integer month = Integer.parseInt(dateOfBirth.substring(3,5));
        Integer year = Integer.parseInt(dateOfBirth.substring(6,10));

        return LocalDate.of(year,month,day);
    }
}
