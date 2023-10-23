package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Delete client operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class DeleteClientOperation
    extends MainOperation<DeleteClientRequest, DeleteClientResponse, DeleteClientContext>
{
    private final DeleteClientRequestValidator       requestValidator;
    private final DeleteClientPreconditionsValidator preconditionsValidator;
    private final DeleteClientContextCreator         contextCreator;
    private final DeleteClientResourceMaintainer     resourceMaintainer;
    private final DeleteClientResponseGenerator      responseGenerator;

    public DeleteClientOperation(final DeleteClientRequestValidator requestValidator, final DeleteClientPreconditionsValidator preconditionsValidator, final DeleteClientContextCreator contextCreator, final DeleteClientResourceMaintainer resourceMaintainer, final DeleteClientResponseGenerator responseGenerator)
    {
        this.requestValidator = Defense.notNull(requestValidator, DeleteClientRequestValidator.class.getSimpleName());
        this.preconditionsValidator = Defense.notNull(preconditionsValidator, DeleteClientPreconditionsValidator.class.getSimpleName());
        this.contextCreator = Defense.notNull(contextCreator, DeleteClientContextCreator.class.getSimpleName());
        this.resourceMaintainer = Defense.notNull(resourceMaintainer, DeleteClientResourceMaintainer.class.getSimpleName());
        this.responseGenerator = Defense.notNull(responseGenerator, DeleteClientResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final DeleteClientRequest request)
    {
        requestValidator.validate(request);
    }

    @Override
    protected DeleteClientContext createContext(final DeleteClientRequest request)
    {
        return contextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final DeleteClientContext context)
    {
        preconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final DeleteClientContext context)
    {
        resourceMaintainer.resolve(context);
    }

    @Override
    protected DeleteClientResponse generateResponse(final DeleteClientContext context)
    {
        return responseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.DELETE_STATISTICS;
    }
}
