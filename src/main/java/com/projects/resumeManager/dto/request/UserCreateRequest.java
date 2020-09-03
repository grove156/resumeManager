package com.projects.resumeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCreateRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String dateOfBirth;

    private LocalDate dateOfBirthTransformed = dateTransformer(dateOfBirth);

    @NotNull
    private String phoneNumber;

    private LocalDate dateTransformer(String dateOfBirth){
        Integer day = Integer.parseInt(dateOfBirth.substring(0,2));
        Integer month = Integer.parseInt(dateOfBirth.substring(3,5));;
        Integer year = Integer.parseInt(dateOfBirth.substring(6,10));;

        return LocalDate.of(year,month,day);
    }

}
