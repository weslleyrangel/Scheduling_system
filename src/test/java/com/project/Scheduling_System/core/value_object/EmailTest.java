package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Should create a valid email address")
    void shouldCreateValidEmail(){
        String validAddress = "weslley@email.com";
        Email email = Email.from(validAddress);
        assertEquals(validAddress, email.getValue());
    }

    @Nested
    @DisplayName("Email Validations")
    class EmailValidations {
        
        @ParameterizedTest
        @ValueSource(strings = {"weslley", "weslley@", "@domain.com", "weslley@domain"})
        @DisplayName("Should throw exception for invalid email formats")
        void shouldThrowExceptionForInvalidFormats(String invalidEmail){
            DomainException ex = assertThrows(DomainException.class, () -> Email.from(invalidEmail));
            assertEquals("Invalid Email.", ex.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "  "})
        @DisplayName("Should throw exception for empty email")
        void shouldThrowExceptionForEmptyEmail(String emptyEmail){
            DomainException ex = assertThrows(DomainException.class, () -> Email.from(emptyEmail));
            assertEquals("O Campo Email é obrigatório.", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for null email")
        void shouldThrowExceptionForNullEmail(){
            DomainException ex = assertThrows(DomainException.class, () -> Email.from(null));
            assertEquals("O Campo Email é obrigatório.", ex.getMessage());
        }
    }
}
