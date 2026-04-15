package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.TenantId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domain: Service")
class ServiceTest {
    private final TenantId validTenantId = TenantId.generateNew();
    @Test
    @DisplayName("Should create a valid service")
    void shouldCreateValidService() {
        Service service = new Service(validTenantId, "Corte Masculino", new BigDecimal("50.00"), 30);

        assertNotNull(service.getId());
        assertEquals(validTenantId, service.getTenantId());
        assertEquals("Corte Masculino", service.getName());
    }

    @Test
    @DisplayName("Should throw exception when TenantId is null")
    void shouldThrowExceptionForNullTenantId() {
        DomainException ex = assertThrows(DomainException.class,
                () -> new Service(null, "Corte", new BigDecimal("50.00"), 30));
        assertEquals("O Campo TenantId é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for negative price")
    void shouldThrowExceptionForNegativePrice(){
        assertThrows(DomainException.class, () -> new Service(validTenantId,"Corte", new BigDecimal("-10.00"), 30));
    }
}
