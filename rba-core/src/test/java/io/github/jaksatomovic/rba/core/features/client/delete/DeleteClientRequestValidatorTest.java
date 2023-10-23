package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.core.features.shared.AbstractTest;
import io.github.jaksatomovic.rba.core.features.shared.validator.request.BaseRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        DeleteClientRequestValidator.class,
    }
)
class DeleteClientRequestValidatorTest
    extends AbstractTest
{

    @Autowired
    private DeleteClientRequestValidator validator;

    @MockBean
    private BaseRequestValidator baseRequestValidator;

    @Test
    void validate_OK()
    {
        validator.validate(prepareDeleteClientRequest());
    }
}