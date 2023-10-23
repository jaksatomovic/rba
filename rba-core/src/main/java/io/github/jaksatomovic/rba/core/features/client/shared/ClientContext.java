package io.github.jaksatomovic.rba.core.features.client.shared;

import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;

import java.util.Optional;

/**
 * The interface Client context.
 *
 * @param <R> the type parameter
 */
public interface ClientContext<R extends AbstractRequest>
    extends OperationContext<R>
{
    Optional<DbClient> getExistingClient();
}
