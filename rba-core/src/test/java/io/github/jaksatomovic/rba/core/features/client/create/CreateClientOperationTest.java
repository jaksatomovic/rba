package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
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
        CreateClientOperation.class,
    }
)
class CreateClientOperationTest
    extends AbstractTest
{

    @Autowired
    private CreateClientOperation operation;

    @MockBean
    private CreateClientRequestValidator       validator;
    @MockBean
    private CreateClientPreconditionsValidator preconditionsValidator;
    @MockBean
    private CreateClientContextCreator         contextCreator;
    @MockBean
    private CreateClientResourceMaintainer     resourceMaintainer;
    @MockBean
    private CreateClientResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        CreateClientRequest request = prepareCreateClientRequest();
        CreateClientContext context = getCreateClientContext(request);

        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareResponse(request));

        CreateClientResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }

    private CreateClientResponse prepareResponse(final CreateClientRequest request)
    {
        return new CreateClientResponse(request, ResponseCode.OK);
    }
}
