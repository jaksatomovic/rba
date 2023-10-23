package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import io.github.jaksatomovic.rba.core.persistence.store.FileStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteClientResourceMaintainer
    implements ResourceMaintainer<DeleteClientContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ClientStore clientStore;
    private final FileStore   fileStore;

    public DeleteClientResourceMaintainer(final ClientStore clientStore, final FileStore fileStore)
    {
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
        this.fileStore = Defense.notNull(fileStore, FileStore.class.getSimpleName());
    }

    @Override
    public void resolve(final DeleteClientContext context)
    {
        // TODO - order matters
        resolveDbFile(context);
        resolveDbClient(context);

        logger.debug("[MAINTAINER] - Delete Client - [OK]");
    }

    private void resolveDbClient(final DeleteClientContext context)
    {
        clientStore.deleteEntity(context.getExistingClient().get());
    }

    private void resolveDbFile(final DeleteClientContext context)
    {
        if (context.getExistingFile().isPresent())
        {
            DbFile dbFile = context.getExistingFile().get();
            dbFile.setClientId(null);
            dbFile.setActive(false);
            fileStore.saveEntity(dbFile);

            logger.debug("[MAINTAINER] - File update - [OK]");
        }
    }
}
