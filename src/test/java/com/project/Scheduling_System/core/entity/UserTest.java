package com.project.Scheduling_System.core.entity;

import com.project.Scheduling_System.core.value_object.Cpf;
import com.project.Scheduling_System.core.value_object.Email;
import com.project.Scheduling_System.core.value_object.Password;
import com.project.Scheduling_System.core.value_object.TenantId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domain: User")
class UserTest {

    private final TenantId validTenantId = TenantId.generateNew();
    private final Email validEmail = Email.from("test@example.com");
    private final Cpf validCpf = Cpf.from("52998224725");
    private final Password validPassword = Password.from("Strong@Pass123");

    @Test
    @DisplayName("Should create a valid user with all required attributes")
    void shouldCreateValidUser() {
        User user = new User(validTenantId, "Weslley Rangel", validEmail, validCpf, validPassword);

        assertNotNull(user.getId());
        assertEquals(validTenantId, user.getTenantId(), "User must belong to the correct Tenant.");
        assertEquals("Weslley Rangel", user.getName());
    }

    @Nested
    @DisplayName("Dependency and Security Validations")
    class DependencyValidation{
        @Test
        @DisplayName("Should throw exception when TenantId is missing")
        void shouldRequireTenantId(){
            DomainException ex = assertThrows(DomainException.class, () -> new User(null, "Weslley", validEmail, validCpf, validPassword));
            assertEquals("O Campo TenantId é obrigatório.", ex.getMessage());
        }
    }

    @Nested
    @DisplayName("Security Validations")
    class SecurityValidation{
        @Test
        @DisplayName("Should throw exception when password is null")
        void shouldThrowExceptionForNullPassword() {
            DomainException exception = assertThrows(DomainException.class, () -> new User(validTenantId,"Weslley", validEmail, validCpf, null));
            assertEquals("O Campo Password é obrigatório.", exception.getMessage());
        }

    }

    @Nested
    @DisplayName("Identification Validations")
    class IdentificationValidation {

        @Test
        @DisplayName("User must have a valid UserId upon creation")
        void shouldHaveIdOnCreation(){
            User user = new User(validTenantId,"Weslley Rangel", validEmail, validCpf, validPassword);

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
            User user = new User( validTenantId,expectedName, validEmail, validCpf, validPassword);
            assertEquals(expectedName, user.getName(), "The stored name must match the provided name.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "  ", " "})
        @DisplayName("Should throw DomainException when name is empty")
        void shouldThrowExceptionForEmptyName(String invalidName) {
            DomainException exception = assertThrows(DomainException.class, () -> {
                new User(validTenantId, invalidName, validEmail, validCpf, validPassword);
            });
            assertEquals("O Campo Nome é obrigatório.", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when name is null")
        void shouldThrowExceptionForNullName() {
            DomainException exception = assertThrows(DomainException.class, () -> new User(validTenantId, null, validEmail, validCpf, validPassword));
            assertEquals("O Campo Nome é obrigatório.", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Email Validations")
    class EmailValidation {
        @Test
        @DisplayName("Should throw exception when email is null")
        void shouldThrowExceptionForNullEmail() {
            DomainException exception = assertThrows(DomainException.class, () -> new User(validTenantId, "Weslley", null, null, null));
            assertEquals("O Campo Email é obrigatório.", exception.getMessage());
        }
    }
}
