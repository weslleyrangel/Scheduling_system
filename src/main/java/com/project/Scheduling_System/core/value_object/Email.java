package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.regex.Pattern;

public final class Email {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String INVALID_EMAIL_MESSAGE = "Invalid Email.";
    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email from(String address) {
        if (isInvalidInput(address)) {
            throw DomainExceptionFactory.mandatoryField("Email");
        }
        if (isInvalidFormat(address)) {
            throw DomainExceptionFactory.businessRuleViolated(INVALID_EMAIL_MESSAGE);
        }
        return new Email(address.trim().toLowerCase());
    }

    private static boolean isInvalidInput(String address) {
        return address == null || address.trim().isEmpty();
    }

    private static boolean isInvalidFormat(String address) {
        return !EMAIL_PATTERN.matcher(address).matches() || !address.contains(".");
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
