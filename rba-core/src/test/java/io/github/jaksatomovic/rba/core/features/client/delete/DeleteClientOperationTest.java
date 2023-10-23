package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.commons.exception.ApiException;
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
        DeleteClientOperation.class,
    }
)
class DeleteClientOperationTest
    extends AbstractTest
{

    @Autowired
    private DeleteClientOperation operation;

    @MockBean
    private DeleteClientRequestValidator       validator;
    @MockBean
    private DeleteClientPreconditionsValidator preconditionsValidator;
    @MockBean
    private DeleteClientContextCreator         contextCreator;
    @MockBean
    private DeleteClientResourceMaintainer     resourceMaintainer;
    @MockBean
    private DeleteClientResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        DeleteClientRequest request = prepareDeleteClientRequest();
        DeleteClientContext context = getDeleteClientContext(request);

        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareResponse(request));

        DeleteClientResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }

    private DeleteClientResponse prepareResponse(final DeleteClientRequest request)
    {
        return new DeleteClientResponse(request, ResponseCode.OK);
    }
}
