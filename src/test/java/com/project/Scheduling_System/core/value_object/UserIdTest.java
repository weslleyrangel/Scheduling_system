package com.project.Scheduling_System.core.value_object;


import com.project.Scheduling_System.core.entity.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

    @Test
    @DisplayName("Should generate a new valid UserId")
    void shouldGenerateNewValidUserId(){
        UserId id = UserId.genereteNew();
        assertNotNull(id.getValue());
    }

    @Nested
    @DisplayName("From Method Validations")
    class FromMethodValidations{
        @Test
        @DisplayName("Should accept a valid UUID String")
        void shouldAcceptValidUuidString(){
            String validUuid = UUID.randomUUID().toString();
            UserId id = UserId.from(validUuid);
            assertEquals(validUuid, id.toString());
        }

        @Test
        @DisplayName("Should throw exception for invalid UUID format")
        void shouldThrowExceptionForInvalidFormat(){
            DomainException ex = assertThrows(DomainException.class, () -> UserId.from("invalid-uuid-123"));
            assertEquals("Invalid ID format", ex.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "  ", " "})
        @DisplayName("Should throw exception for empty or blank ID")
        void shouldThrowExceptionForEmptyId(String emptyId){
            DomainException ex = assertThrows(DomainException.class, () -> UserId.from(emptyId));
            assertEquals("O Campo ID é obrigatório.", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when UUID is null")
        void shouldThrowExceptionWhenUuidIsNull(){
            assertThrows(DomainException.class, () -> UserId.from((UUID) null));
        }
    }
}
