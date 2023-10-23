package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
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
        SearchClientResponseGenerator.class,
    }
)
class SearchClientResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private SearchClientResponseGenerator generator;

    @Test
    void generateResponse()
    {
        SearchClientResponse response = generator.generateResponse(getSearchClientContext(prepareSearchClientRequest()));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getPayload());
        Assertions.assertNotNull(response.getPayload().getFirstName());
        Assertions.assertNotNull(response.getPayload().getLastName());
        Assertions.assertNotNull(response.getPayload().getIdentificationNumber());
        Assertions.assertNotNull(response.getPayload().getStatus());
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}