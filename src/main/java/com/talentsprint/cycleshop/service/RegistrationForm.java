package com.talentsprint.cycleshop.service;

import lombok.Data;

@Data

public class RegistrationForm {

    private String name;

    private String password;

    private String repeatPassword;

    private String role;

    public boolean isValid() {

        return password.equals(repeatPassword);

    }

}
