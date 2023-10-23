package io.github.jaksatomovic.rba.core.features.shared.peer;

import io.github.jaksatomovic.rba.api.features.client.ClientService;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.create.CreateClientResponse;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientResponse;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.commons.exception.ApiException;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientOperation;
import io.github.jaksatomovic.rba.core.features.client.delete.DeleteClientOperation;
import io.github.jaksatomovic.rba.core.features.client.search.SearchClientOperation;
import org.springframework.stereotype.Service;

/**
 * The type client peer.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class ClientPeer
    implements ClientService
{
    private final SearchClientOperation searchClientOperation;
    private final DeleteClientOperation deleteClientOperation;
    private final CreateClientOperation createClientOperation;

    public ClientPeer(final SearchClientOperation searchClientOperation, final DeleteClientOperation deleteClientOperation, final CreateClientOperation createClientOperation)
    {
        this.searchClientOperation = Defense.notNull(searchClientOperation, SearchClientOperation.class.getSimpleName());
        this.deleteClientOperation = Defense.notNull(deleteClientOperation, DeleteClientOperation.class.getSimpleName());
        this.createClientOperation = Defense.notNull(createClientOperation, CreateClientOperation.class.getSimpleName());
    }

    @Override
    public CreateClientResponse create(final CreateClientRequest request)
        throws ApiException
    {
        return createClientOperation.execute(request);
    }

    @Override
    public SearchClientResponse search(final SearchClientRequest request)
        throws ApiException
    {
        return searchClientOperation.execute(request);
    }

    @Override
    public DeleteClientResponse delete(final DeleteClientRequest request)
        throws ApiException
    {
        return deleteClientOperation.execute(request);
    }
}
