package com.encurso.domain.entity;

import com.encurso.domain.valueObject.Email;
import com.encurso.domain.valueObject.Password;

public class UserLoginRequest {
    private Email email;
    private Password password;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password != null ? password.value() : null;
    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }

}
