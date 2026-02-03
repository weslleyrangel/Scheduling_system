package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.Cpf;
import com.project.Scheduling_System.core.value_object.Email;
import com.project.Scheduling_System.core.value_object.UserId;

public class User {
    private final UserId id;
    private final String nome;
    private final Email email;
    private final Cpf cpf;

    public User(String nome, Email email, Cpf cpf) {
        if (nome == null || nome.trim().isEmpty()) {
            throw DomainExceptionFactory.mandatoryField("Nome");
        }
        if (email == null) {
            throw DomainExceptionFactory.mandatoryField("Email");
        }
        if (cpf == null) {
            throw DomainExceptionFactory.mandatoryField("CPF");
        }
        this.id = UserId.genereteNew();
        this.nome = nome.trim();
        this.email = email;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public UserId getId() {
        return id;
    }
    public Email getEmail(){return email;}
    public Cpf getCpf(){return cpf;}
}
