package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.api.shared.Client;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.commons.exception.ApiException;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientContext;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientContextCreator;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientOperation;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientPreconditionsValidator;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientRequestValidator;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientResourceMaintainer;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientResponseGenerator;
import io.github.jaksatomovic.rba.core.features.shared.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
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
        SearchClientOperation.class,
    }
)
class SearchClientOperationTest
    extends AbstractTest
{
    @Autowired
    private SearchClientOperation operation;

    @MockBean
    private SearchClientRequestValidator       validator;
    @MockBean
    private SearchClientPreconditionsValidator preconditionsValidator;
    @MockBean
    private SearchClientContextCreator         contextCreator;
    @MockBean
    private SearchClientResourceMaintainer     resourceMaintainer;
    @MockBean
    private SearchClientResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        SearchClientRequest request = prepareSearchClientRequest();
        SearchClientContext context = getSearchClientContext(request);

        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareResponse(request));

        SearchClientResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }

    private SearchClientResponse prepareResponse(final SearchClientRequest request)
    {
        return new SearchClientResponse(request, ResponseCode.OK, prepareClientPayload());
    }
}