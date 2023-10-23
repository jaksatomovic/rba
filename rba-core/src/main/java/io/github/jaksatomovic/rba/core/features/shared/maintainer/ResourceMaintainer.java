package io.github.jaksatomovic.rba.core.features.shared.maintainer;

import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.core.features.shared.context.OperationContext;

/**
 * Base interface for all resource maintainer classes.
 *
 * @param <C> Context based on which the resources are maintained.
 * @author Jaksa Tomovic
 */
public interface ResourceMaintainer<C extends OperationContext<? extends AbstractRequest>>
{
    /**
     * resolve.
     *
     * @param context the context
     */
    void resolve(C context);

}
