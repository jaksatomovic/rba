package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.api.shared.Client;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

/**
 * The type Search client response generator.
 */
@Service
public class SearchClientResponseGenerator
    implements ResponseGenerator<SearchClientResponse, SearchClientContext>
{
    @Override
    public SearchClientResponse generateResponse(final SearchClientContext context)
    {
        return new SearchClientResponse(context.getOriginalRequest(), ResponseCode.OK, resolveClient(context));
    }

    private Client resolveClient(final SearchClientContext context)
    {
        Client client = new Client();
        client.setFirstName(context.getExistingClient().get().getFirstName());
        client.setLastName(context.getExistingClient().get().getLastName());
        client.setIdentificationNumber(context.getExistingClient().get().getIdentificationNumber());
        client.setStatus(context.getExistingClient().get().getStatus());
        return client;
    }
}
