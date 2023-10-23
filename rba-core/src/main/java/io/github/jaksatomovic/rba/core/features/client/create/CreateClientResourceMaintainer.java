package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.shared.CardStatus;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateClientResourceMaintainer
    implements ResourceMaintainer<CreateClientContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ClientStore clientStore;

    public CreateClientResourceMaintainer(final ClientStore clientStore)
    {
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
    }

    @Override
    public void resolve(final CreateClientContext context)
    {
        DbClient dbClient = new DbClient();
        dbClient.setFirstName(context.getOriginalRequest().getFirstName());
        dbClient.setLastName(context.getOriginalRequest().getLastName());
        dbClient.setIdentificationNumber(context.getOriginalRequest().getIdentificationNumber());
        dbClient.setStatus(CardStatus.NOT_REQUESTED);

        clientStore.saveEntity(dbClient);

        logger.debug("[MAINTAINER] - Create Client - [OK]");
    }
}
