package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Search client operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class SearchClientOperation
    extends MainOperation<SearchClientRequest, SearchClientResponse, SearchClientContext>
{
    private final SearchClientRequestValidator       requestValidator;
    private final SearchClientPreconditionsValidator preconditionsValidator;
    private final SearchClientContextCreator         contextCreator;
    private final SearchClientResourceMaintainer     resourceMaintainer;
    private final SearchClientResponseGenerator      responseGenerator;

    public SearchClientOperation(final SearchClientRequestValidator requestValidator, final SearchClientPreconditionsValidator preconditionsValidator, final SearchClientContextCreator contextCreator, final SearchClientResourceMaintainer resourceMaintainer, final SearchClientResponseGenerator responseGenerator)
    {
        this.requestValidator = Defense.notNull(requestValidator, SearchClientRequestValidator.class.getSimpleName());
        this.preconditionsValidator = Defense.notNull(preconditionsValidator, SearchClientPreconditionsValidator.class.getSimpleName());
        this.contextCreator = Defense.notNull(contextCreator, SearchClientContextCreator.class.getSimpleName());
        this.resourceMaintainer = Defense.notNull(resourceMaintainer, SearchClientResourceMaintainer.class.getSimpleName());
        this.responseGenerator = Defense.notNull(responseGenerator, SearchClientResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final SearchClientRequest request)
    {
        requestValidator.validate(request);
    }

    @Override
    protected SearchClientContext createContext(final SearchClientRequest request)
    {
        return contextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final SearchClientContext context)
    {
        preconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final SearchClientContext context)
    {
        resourceMaintainer.resolve(context);
    }

    @Override
    protected SearchClientResponse generateResponse(final SearchClientContext context)
    {
        return responseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.SEARCH_STATISTICS;
    }
}
