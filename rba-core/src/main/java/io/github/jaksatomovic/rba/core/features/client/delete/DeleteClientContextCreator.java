package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.client.shared.ClientContextCreator;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import io.github.jaksatomovic.rba.core.persistence.store.FileStore;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class DeleteClientContextCreator
    extends ClientContextCreator<DeleteClientContext, DeleteClientRequest>
{
    private final ClientStore clientStore;
    private final FileStore   fileStore;

    protected DeleteClientContextCreator(final ClientStore clientStore, final FileStore fileStore)
    {
        super(clientStore);
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
        this.fileStore = Defense.notNull(fileStore, FileStore.class.getSimpleName());
    }

    @Override
    public DeleteClientContext create(final DeleteClientRequest request)
    {
        Optional<DbClient> dbClient = resolveClient(request.getIdentificationNumber());

        Optional<DbFile> dbFile = resolveFile(dbClient);

        return new DeleteClientContext()
        {

            @Override
            public DeleteClientRequest getOriginalRequest()
            {
                return request;
            }

            @Override
            public Optional<DbClient> getExistingClient()
            {
                return dbClient;
            }

            @Override
            public Optional<DbFile> getExistingFile()
            {
                return dbFile;
            }
        };
    }

    private Optional<DbClient> resolveClient(final Long identificationNumber)
    {
        return clientStore.getRepository().getByIdentificationNumber(identificationNumber);
    }

    private Optional<DbFile> resolveFile(final Optional<DbClient> dbClient)
    {
        if (dbClient.isPresent())
        {
            return fileStore.getRepository().getByClientId(dbClient.get().getId());
        }

        return Optional.empty();
    }
}
