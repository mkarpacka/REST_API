package com.example.comarch;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Base64PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.equals(s);
    }
}
