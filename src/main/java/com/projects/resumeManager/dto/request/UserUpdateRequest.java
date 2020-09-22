package com.projects.resumeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private LocalDate dateOfBirth;

    @NotNull
    private String phoneNumber;
}
