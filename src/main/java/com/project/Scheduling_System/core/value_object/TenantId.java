package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.UUID;

public final class TenantId {
    private final UUID value;

    private TenantId(UUID value) {
        this.value = value;
    }

    public static TenantId generateNew() {
        return new TenantId(UUID.randomUUID());
    }

    public static TenantId from(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw DomainExceptionFactory.mandatoryField("Tenant ID");
        }
        try {
            return new TenantId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw DomainExceptionFactory.businessRuleViolated("Invalid Tenant ID format.");
        }
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return value.equals(((TenantId) o).value);
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
