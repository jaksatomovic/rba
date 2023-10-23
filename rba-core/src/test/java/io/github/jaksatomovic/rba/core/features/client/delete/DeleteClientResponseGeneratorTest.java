package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.core.features.shared.AbstractTest;
import org.junit.jupiter.api.Assertions;
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
        DeleteClientResponseGenerator.class,
    }
)
class DeleteClientResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private DeleteClientResponseGenerator generator;

    @Test
    void generateResponse()
    {
        DeleteClientResponse response = generator.generateResponse(getDeleteClientContext(prepareDeleteClientRequest()));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}