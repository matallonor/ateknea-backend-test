package com.ateknea.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class User {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private boolean enabled;

}