package io.github.jaksatomovic.rba.core.features.client.shared;

import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.features.shared.context.ContextCreator;
import io.github.jaksatomovic.rba.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type client context creator.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 */
public abstract class ClientContextCreator<C extends OperationContext<? extends AbstractRequest>, R extends AbstractRequest>
    implements ContextCreator<C, R>
{
    protected final ClientStore clientStore;

    @Autowired
    protected ClientContextCreator(final ClientStore clientStore)
    {
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
    }
}
