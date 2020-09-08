package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.response.UserDetailResponse;
import com.projects.resumeManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getUserDetail() throws Exception {
        User userDetail = User.builder()
                .email("tester@gmail.com")
                .password("$2a$10$zl3cA/EwYpM41Ti00FID7O3r7Nx79hkl.vqGYcQdn42VbUXquBYpW")
                .name("Tester Kim")
                .createdAt(LocalDateTime.now())
                .phoneNumber("01011112222")
                .dateOfBirth(LocalDate.now())
                .enable(true)
                .build();

        given(userRepository.findById(any())).willReturn(Optional.of(userDetail));

        UserDetailResponse user = userService.getUserDetail(1L);

        assertThat(user.getName(),is("Tester Kim"));
    }
}