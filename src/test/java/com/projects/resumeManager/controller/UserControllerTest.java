package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.MapKeyColumn;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void register() throws Exception {
        mvc.perform(get("/register")).andExpect(status().isOk());
    }

    @Test
    public void createUser() throws Exception {
        User user = User.builder()
                .email("gootpy@gmail.com")
                .password("$2a$10$zl3cA/EwYpM41Ti00FID7O3r7Nx79hkl.vqGYcQdn42VbUXquBYpW")
                .firstName("tester")
                .lastName("kim")
                .phoneNumber("01011112222")
                .dateOfBirth(LocalDate.of(1989,7,8))
                .build();
        given(userService.createUser(any())).willReturn(user);

        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\"email\":\"test@gmail.com\", \n" +
                        "     \"password\":\"password\",\n" +
                        "     \"first_name\":\"tester\", \n" +
                        "     \"last_name\":\"kim\",\n" +
                        "     \"date_of_birth\":\"07/08/1989\",\n" +
                        "     \"phone_number\": \"01011112222\"}"));
    }

    @Test
    public void getUserDetail() throws Exception {

        mvc.perform(get("/user/1")).andExpect(status().isOk());
    }
}