package com.projects.resumeManager.domain.entity;

import com.projects.resumeManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser(){

        LocalDate date = LocalDate.now();
        LocalDateTime createdAt = LocalDateTime.now();

        User user = User.builder()
                .email("grove156@gmail.com")
                .password("Gkdlfnd12!")
                .firstName("Myeongkeun")
                .lastName("Kim")
                .phoneNumber(01046470435)
                .dateOfBirth(date)
                .createdAt(createdAt)
                .build();

       User newUser = userRepository.save(user);

       System.out.println(newUser);
    }
}