package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.*;

public class User {
    private final UserId id;
    private final TenantId tenantId;
    private final String name;
    private final Email email;
    private final Cpf cpf;
    private final Password password;

    public User(TenantId tenantId, String name, Email email, Cpf cpf, Password password) {
        validateInput(tenantId, name, email, cpf, password);

        this.id = UserId.generateNew();
        this.tenantId = tenantId;
        this.name = name.trim();
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    private void validateInput(TenantId tenantId, String nome, Email email, Cpf cpf, Password password) {
        if (tenantId == null) throw DomainExceptionFactory.mandatoryField("TenantId");
        if (nome == null || nome.trim().isEmpty()) throw DomainExceptionFactory.mandatoryField("Nome");
        if (email == null) throw DomainExceptionFactory.mandatoryField("Email");
        if (cpf == null) throw DomainExceptionFactory.mandatoryField("CPF");
        if (password == null) throw DomainExceptionFactory.mandatoryField("Password");
    }

    public UserId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Password getPassword() {
        return password;
    }

    public TenantId getTenantId() {
        return tenantId;
    }
}
