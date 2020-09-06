package com.projects.resumeManager.dto;

import com.projects.resumeManager.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;

    public SessionUser(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
