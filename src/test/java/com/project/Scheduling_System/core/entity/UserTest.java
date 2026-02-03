package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.Cpf;
import com.project.Scheduling_System.core.value_object.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domain: User")
class UserTest {
    
    private final Email validEmail = Email.from("test@example.com");
    private final Cpf validCpf = Cpf.from("52998224725");

    @Nested
    @DisplayName("Identification Validations")
    class IdentificationValidation {

        @Test
        @DisplayName("User must have a valid UserId upon creation")
        void shouldHaveIdOnCreation(){
            User user = new User("Weslley Rangel", validEmail, validCpf);

            assertNotNull(user.getId(), "User ID should not be null.");
            assertNotNull(user.getId().getValue(), "The UUID inside UserId should not be null.");
        }
    }

    @Nested
    @DisplayName("Name Validations")
    class NameValidation {
        @Test
        @DisplayName("Should create a user when the name is valid")
        void shouldCreateUserWithValidName() {

            String expectedName = "Weslley Rangel";
            User user = new User(expectedName, validEmail, validCpf);
            assertEquals(expectedName, user.getNome(), "The stored name must match the provided name.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "  ", " "})
        @DisplayName("Should throw DomainException when name is empty")
        void shouldThrowExceptionForEmptyName(String invalidName) {
            DomainException exception = assertThrows(DomainException.class, () -> {
                new User(invalidName, validEmail, validCpf);
            });

            // Note: The expected message remains in Portuguese because DomainExceptionFactory generates it in Portuguese.
            assertEquals("O Campo Nome é obrigatório.", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when name is null")
        void shouldThrowExceptionForNullName() {
            DomainException exception = assertThrows(DomainException.class, () -> new User(null, validEmail, validCpf));
            assertEquals("O Campo Nome é obrigatório.", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Email Validations")
    class EmailValidation {
        @Test
        @DisplayName("Should throw exception when email is null")
        void shouldThrowExceptionForNullEmail() {
            DomainException exception = assertThrows(DomainException.class, () -> new User("Weslley", null, null));
            assertEquals("O Campo Email é obrigatório.", exception.getMessage());
        }
    }
}
