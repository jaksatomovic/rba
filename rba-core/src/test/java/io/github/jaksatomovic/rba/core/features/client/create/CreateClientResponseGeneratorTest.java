package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.core.features.client.delete.DeleteClientResponseGenerator;
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
        CreateClientResponseGenerator.class,
    }
)
class CreateClientResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private CreateClientResponseGenerator generator;

    @Test
    void generateResponse()
    {
        CreateClientResponse response = generator.generateResponse(getCreateClientContext(prepareCreateClientRequest()));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}