package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.regex.Pattern;

public final class Email {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private final String value;

    private Email(String value){
        this.value = value;
    }

    public static Email from(String address){
        if(address == null || address.trim().isEmpty()){
            throw DomainExceptionFactory.mandatoryField("Email");
        }


    if(!EMAIL_PATTERN.matcher(address).matches() || !address.contains(".")){
        throw DomainExceptionFactory.businessRuleViolated("Invalid Email.");
    }

    return new Email(address.trim().toLowerCase());
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
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
