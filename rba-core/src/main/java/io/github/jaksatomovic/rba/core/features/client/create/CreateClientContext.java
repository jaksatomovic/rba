package io.github.jaksatomovic.rba.core.features.client.create;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.core.features.client.shared.ClientContext;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;

import java.util.Optional;

/**
 * The interface create client context.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public interface CreateClientContext
    extends ClientContext<CreateClientRequest>
{
}
