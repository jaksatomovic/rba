package io.github.jaksatomovic.rba.core.features.client.delete;

import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.core.features.client.shared.ClientContext;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;

import java.util.Optional;

/**
 * The interface delete client context.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public interface DeleteClientContext
    extends ClientContext<DeleteClientRequest>
{
    Optional<DbFile> getExistingFile();
}
