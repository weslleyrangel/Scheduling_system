package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainExceptionFactory;

import java.util.Objects;
import java.util.UUID;

public final class ServiceId {
    private final UUID value;

    private ServiceId(UUID value) {
        this.value = value;
    }

    public static ServiceId generateNew() {
        return new ServiceId(UUID.randomUUID());
    }

    public static ServiceId from(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw DomainExceptionFactory.mandatoryField("Service ID");
        }
        try {
            return new ServiceId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw DomainExceptionFactory.businessRuleViolated("Invalid Service ID format.");
        }
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServiceId serviceId = (ServiceId) o;
        return Objects.equals(value, serviceId.value);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
    @Override
    public String toString() {
        return value.toString();
    }
}
