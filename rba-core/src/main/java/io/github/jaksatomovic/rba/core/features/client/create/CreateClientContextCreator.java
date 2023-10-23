package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.client.shared.ClientContextCreator;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type create client context creator.
 */
@Service
public class CreateClientContextCreator
    extends ClientContextCreator<CreateClientContext, CreateClientRequest>
{
    private final ClientStore clientStore;

    protected CreateClientContextCreator(final ClientStore clientStore)
    {
        super(clientStore);
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
    }

    @Override
    public CreateClientContext create(final CreateClientRequest request)
    {
        Optional<DbClient> dbClient = resolveClient(request.getIdentificationNumber());

        return new CreateClientContext()
        {
            @Override
            public CreateClientRequest getOriginalRequest()
            {
                return request;
            }

            @Override
            public Optional<DbClient> getExistingClient()
            {
                return dbClient;
            }
        };
    }

    private Optional<DbClient> resolveClient(final Long identificationNumber)
    {
        return clientStore.getRepository().getByIdentificationNumber(identificationNumber);
    }
}
