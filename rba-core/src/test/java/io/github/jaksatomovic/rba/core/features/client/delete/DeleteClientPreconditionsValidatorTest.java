package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.core.features.shared.AbstractTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        DeleteClientPreconditionsValidator.class,
    }
)
class DeleteClientPreconditionsValidatorTest
    extends AbstractTest
{

    @Autowired
    private DeleteClientPreconditionsValidator validator;

    @Test
    void validate()
    {
        validator.validate(getDeleteClientContext(prepareDeleteClientRequest()));
    }
}