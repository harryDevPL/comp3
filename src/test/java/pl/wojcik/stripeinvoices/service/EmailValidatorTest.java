package pl.wojcik.stripeinvoices.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.wojcik.stripeinvoices.Utils.EmailValidator.validate;

class EmailValidatorTest {

    @ParameterizedTest
    @ArgumentsSource(ValidEmails.class)
    void shouldReturnTrue_whenEmailIsValid(String validEmail) {
        assertThat(validate(validEmail)).isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidEmails.class)
    void shouldReturnFalse_whenEmailIsNotValid(String invalidEmail) {
        assertThat(validate(invalidEmail)).isFalse();
    }

    @Test
    void shouldThrowNullPointerException_whenEmailIsNull() {
        // GIVEN, WHEN, THEN
        Assertions.assertThrows(NullPointerException.class, () -> {
            validate(null);
        });
    }

    private static class ValidEmails implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of("jan.kowalski@gmail.com", "jan@gmail.com", "recruitment_task@gmail.com").map(Arguments::of);
        }
    }

    private static class InvalidEmails implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of("jan.kowalski_gmail.com", "jan@", "recruitment_task @gmail.com").map(Arguments::of);
        }
    }
}