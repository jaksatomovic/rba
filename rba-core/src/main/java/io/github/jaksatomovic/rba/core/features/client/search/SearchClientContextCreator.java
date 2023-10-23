package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.client.shared.ClientContextCreator;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import io.github.jaksatomovic.rba.core.persistence.store.FileStore;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Search client context creator.
 */
@Service
public class SearchClientContextCreator
    extends ClientContextCreator<SearchClientContext, SearchClientRequest>
{
    private static final String DB_STATISTICS = "dbStatistics";

    private final ClientStore clientStore;
    private final FileStore   fileStore;

    protected SearchClientContextCreator(final ClientStore clientStore, final FileStore fileStore)
    {
        super(clientStore);
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
        this.fileStore = Defense.notNull(fileStore, FileStore.class.getSimpleName());
    }

    @Override
    public SearchClientContext create(final SearchClientRequest request)
    {
        Optional<DbClient> dbClient = resolveClient(request.getIdentificationNumber());

        Optional<DbFile> dbFile = resolveFile(dbClient);

        return new SearchClientContext()
        {

            @Override
            public SearchClientRequest getOriginalRequest()
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
