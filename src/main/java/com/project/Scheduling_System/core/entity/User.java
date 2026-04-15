package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.Cpf;
import com.project.Scheduling_System.core.value_object.Email;
import com.project.Scheduling_System.core.value_object.Password;
import com.project.Scheduling_System.core.value_object.UserId;

public class User {
    private final UserId id;
    private final String name;
    private final Email email;
    private final Cpf cpf;
    private final Password password;

    public User(String name, Email email, Cpf cpf, Password password) {
        validadeInput(name, email, cpf, password);

        this.id = UserId.generateNew();
        this.name = name.trim();
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    private void validadeInput(String name, Email email, Cpf cpf, Password password){
        if(name == null || name.trim().isEmpty()){
            throw DomainExceptionFactory.mandatoryField("Nome");
        }
        if(email == null){
            throw DomainExceptionFactory.mandatoryField("Email");
        }
        if(cpf == null){
            throw DomainExceptionFactory.mandatoryField("CPF");
        }
        if(password == null){
            throw DomainExceptionFactory.mandatoryField("Password");
        }
    }

    public UserId getId() { return id; }
    public String getName() { return name; }
    public Email getEmail() { return email; }
    public Cpf getCpf() { return cpf; }
    public Password getPassword() { return password; }
}
