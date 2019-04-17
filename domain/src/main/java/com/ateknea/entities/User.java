package com.ateknea.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Setter
public final class User {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private boolean enabled;


    //////////////////
    // Domain rules //
    //////////////////

    public boolean isValidName(String name) {
        return name.length() <= 20;
    }

    public boolean isValidLastName(String lastName) {
        return lastName.length() <= 40;
    }

    public boolean isValidEmail(String email) {
        if (email.length() > 20) return false;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}