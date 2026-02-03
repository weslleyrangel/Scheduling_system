package com.project.Scheduling_System.core.value_object;

import com.project.Scheduling_System.core.entity.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Value Object: CPF")
class CpfTest {
    @Test
    @DisplayName("Should create a valid CPF.")
    void shouldCreateValidCpf(){
        String validdCpf = "52998224725";
        Cpf cpf = Cpf.from(validdCpf);

        assertEquals(validdCpf, cpf.getValue(), "The stored value should be exactly the digits.");
    }

    @Test
    @DisplayName("Should create a valid CPF even with mask characters.")
    void shouldCreateValidCpfWithMask(){
        String maskedCpf = "529.982.247-25";
        Cpf cpf = Cpf.from(maskedCpf);

        assertEquals("52998224725", cpf.getValue(), "The domain should clean the mask and store only digits.");
    }

    @Nested
    @DisplayName("CPF Invariants and Rules")
    class CpfInvariants{
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "123", "1234567890", "123456789012"})
        @DisplayName("Should throw exception for invalid digit length")
        void shouldThrowExceptionForInvalidLength(String invalidLength){
            DomainException ex = assertThrows(DomainException.class, () -> Cpf.from(invalidLength));
            assertEquals("Invalid CPF format.", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception for null CPF")
        void shouldThrowExceptionForNullCpf() {
            DomainException ex = assertThrows(DomainException.class, () -> Cpf.from(null));
            assertEquals("O Campo CPF é obrigatório.", ex.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"00000000000", "11111111111", "22222222222",
                "33333333333", "44444444444", "55555555555",
                "66666666666", "77777777777", "88888888888", "99999999999"
        })
        @DisplayName("Should reject CPFs with all identical digits")
        void shouldRejectIdenticalDigits(String identicalDigits){
            DomainException ex = assertThrows(DomainException.class, () -> Cpf.from(identicalDigits));
            assertEquals("Invalid CPF format.", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when first verification digit fails.")
        void shouldFailWhenFristDigitWrong(){
            String wrongFristDigit = "52998224705";
            DomainException ex = assertThrows(DomainException.class, () -> Cpf.from(wrongFristDigit));
            assertEquals("Invalid CPF digits.", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when second verification digit fails.")
        void shouldFailWhenSecondDigitIsWrong(){
            String wrongSecondDigit = "52998224720";
            DomainException ex = assertThrows(DomainException.class, () -> Cpf.from(wrongSecondDigit));
            assertEquals("Invalid CPF digits.", ex.getMessage());
                }
    }
}
