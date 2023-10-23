package io.github.jaksatomovic.rba.core.features.client.create;

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
        CreateClientPreconditionsValidator.class,
    }
)
class CreateClientPreconditionsValidatorTest
    extends AbstractTest
{

    @Autowired
    private CreateClientPreconditionsValidator validator;

    @Test
    void validate()
    {
        validator.validate(getCreateClientContext(prepareCreateClientRequest()));
    }
}