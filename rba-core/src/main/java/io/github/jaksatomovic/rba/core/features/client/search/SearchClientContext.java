package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.core.features.client.shared.ClientContext;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;

import java.util.Optional;

/**
 * The interface Search client context.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public interface SearchClientContext
    extends ClientContext<SearchClientRequest>
{
    Optional<DbFile> getExistingFile();
}
