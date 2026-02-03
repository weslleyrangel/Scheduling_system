package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.Objects;
import java.util.UUID;

public final class UserId {
    private final UUID value;

    private UserId(UUID value){
        this.value = value;
    }

    public static UserId genereteNew(){
        return new UserId(UUID.randomUUID());
    }

   public static UserId from(String id){
        if(id == null || id.trim().isEmpty()){
            throw DomainExceptionFactory.mandatoryField("ID");
        }
        try {
            return new UserId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw DomainExceptionFactory.businessRuleViolated("Invalid ID format");
        }
   }

   public static UserId from(UUID id){
        if(id == null){
            throw DomainExceptionFactory.mandatoryField("ID");
        }
        return new UserId(id);
   }

   public UUID getValue(){
        return value;
   }

    @Override
    public boolean equals(Object o) {
        if(this == o ) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return value.equals(userId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
