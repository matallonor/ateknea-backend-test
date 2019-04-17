package com.ateknea.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String name;
    private String lastName;
    private String email;
    private boolean enabled;
}