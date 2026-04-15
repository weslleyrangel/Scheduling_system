package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Value Object: Password")
class PasswordTest {
    @Test
    @DisplayName("Should create a valid password when it meets all strength requirements")
    void shouldCreateValidPassword() {
        String strongPassword = "StrongPassword@123";
        Password password = Password.from(strongPassword);
        assertEquals(strongPassword, password.getValue());
    }

    @Nested
    @DisplayName("Password Strength Validations")
    class PasswordStrengthValidations {

        @ParameterizedTest
        @ValueSource(strings = {
                "weak12!",       // Menos de 8 caracteres
                "nouppercase12!", // Sem maiúscula
                "NOLOWERCASE12!", // Sem minúscula
                "NoNumbersHere!", // Sem número
                "NoSpecialChar123"// Sem caractere especial
        })
        @DisplayName("Should throw exception when password does not meet strength criteria")
        void shouldThrowForWeakPasswords(String weakPassword) {
            DomainException ex = assertThrows(DomainException.class, () -> Password.from(weakPassword));
            assertEquals("A palavra-passe não cumpre os requisitos mínimos de segurança.", ex.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "  "})
        @DisplayName("Should throw exception for empty password")
        void shouldThrowExceptionForEmptyPassword(String emptyPassword) {
            DomainException ex = assertThrows(DomainException.class, () -> Password.from(emptyPassword));
            assertEquals("O Campo Password é obrigatório.", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for null password")
        void shouldThrowExceptionForNullPassword() {
            DomainException ex = assertThrows(DomainException.class, () -> Password.from(null));
            assertEquals("O Campo Password é obrigatório.", ex.getMessage());

        }
    }
}
