package com.project.Scheduling_System.core.entity;

import java.util.UUID;

public class DomainExceptionFactory {
    public static DomainException entityNotFound(String entity, UUID id){
        return new EntityNotFoundException(String.format("%s com ID %s não encontrado.", entity, id));
    }

    public static DomainException businessRuleViolated(String message){
        return new BusinessRuleException(message);
    }

    public static DomainException mandatoryField(String fieldName){
        return new BusinessRuleException(String.format("O Campo %s é obrigatório.", fieldName));
    }
}
class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String message){super(message);}
}

class BusinessRuleException extends DomainException{
    public BusinessRuleException(String message){super(message);}
}