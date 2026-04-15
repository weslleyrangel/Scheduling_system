package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Password {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    private static final String WEAK_PASSWORD_MESSAGE = "A palavra-passe não cumpre os requisitos mínimos de segurança.";

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password from(String rawPassword) {
        if (isInvalidInput(rawPassword)) {
            throw DomainExceptionFactory.mandatoryField("Password");
        }
        if (isInvalidFormat(rawPassword)) {
            throw DomainExceptionFactory.businessRuleViolated(WEAK_PASSWORD_MESSAGE);
        }
        return new Password(rawPassword);
    }

    private static boolean isInvalidInput(String password) {
        return password == null || password.trim().isEmpty();
    }

    private static boolean isInvalidFormat(String password) {
        return !PASSWORD_PATTERN.matcher(password).matches();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "********";
    }
}
