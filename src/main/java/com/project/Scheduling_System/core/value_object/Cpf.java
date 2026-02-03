package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainException;
import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.Objects;

public final class Cpf {
    private final String value;

    private Cpf(String value){
        this.value = value;
    }

    public static Cpf from(String rawCpf){
        if(rawCpf == null || rawCpf.trim().isEmpty()){
            throw DomainExceptionFactory.mandatoryField("CPF");
        }
        String digitsOnly = rawCpf.replaceAll("\\D","");

        if(digitsOnly.length() != 11 || isCommonInvalidCpf(digitsOnly)){
            throw DomainExceptionFactory.businessRuleViolated("Invalid CPF format.");
        }
        if(!isValidCpfCalculation(digitsOnly)){
            throw DomainExceptionFactory.businessRuleViolated("Invalid CPF digits.");
        }

        return new Cpf(digitsOnly);
    }

    private static boolean isValidCpfCalculation(String cpf){
        int sum1 = 0;
        for(int i = 0; i < 9; i++){
            sum1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = (sum1 * 10) % 11;
        if(firstDigit == 10) firstDigit = 0;

        if(firstDigit != Character.getNumericValue(cpf.charAt(9))) return false;

        int sum2 = 0;
        for(int i = 0; i < 10; i++){
            sum2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = (sum2 * 10) % 11;
        if(secondDigit == 10) secondDigit = 0;

        return secondDigit == Character.getNumericValue(cpf.charAt(10));
    }

    private static boolean isCommonInvalidCpf(String cpf){
        return cpf.matches("(\\d)\\1{10}");
    }

    public String getValue(){ return value; }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return value.equals(cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString(){
        return value;
    }
}
