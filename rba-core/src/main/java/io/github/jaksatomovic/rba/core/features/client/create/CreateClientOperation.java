package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Update countries operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class CreateClientOperation
    extends MainOperation<CreateClientRequest, CreateClientResponse, CreateClientContext>
{
    private final CreateClientRequestValidator       requestValidator;
    private final CreateClientPreconditionsValidator preconditionsValidator;
    private final CreateClientContextCreator         contextCreator;
    private final CreateClientResourceMaintainer     resourceMaintainer;
    private final CreateClientResponseGenerator      responseGenerator;

    public CreateClientOperation(final CreateClientRequestValidator requestValidator, final CreateClientPreconditionsValidator preconditionsValidator, final CreateClientContextCreator contextCreator, final CreateClientResourceMaintainer resourceMaintainer, final CreateClientResponseGenerator responseGenerator)
    {
        this.requestValidator = Defense.notNull(requestValidator, CreateClientRequestValidator.class.getSimpleName());
        this.preconditionsValidator = Defense.notNull(preconditionsValidator, CreateClientPreconditionsValidator.class.getSimpleName());
        this.contextCreator = Defense.notNull(contextCreator, CreateClientContextCreator.class.getSimpleName());
        this.resourceMaintainer = Defense.notNull(resourceMaintainer, CreateClientResourceMaintainer.class.getSimpleName());
        this.responseGenerator = Defense.notNull(responseGenerator, CreateClientResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final CreateClientRequest request)
    {
        requestValidator.validate(request);
    }

    @Override
    protected CreateClientContext createContext(final CreateClientRequest request)
    {
        return contextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final CreateClientContext context)
    {
        preconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final CreateClientContext context)
    {
        resourceMaintainer.resolve(context);
    }

    @Override
    protected CreateClientResponse generateResponse(final CreateClientContext context)
    {
        return responseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.CREATE_CLIENT;
    }
}
