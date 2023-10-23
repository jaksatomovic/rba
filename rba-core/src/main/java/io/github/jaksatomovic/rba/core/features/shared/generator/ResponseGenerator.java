package io.github.jaksatomovic.rba.core.features.shared.generator;

import io.github.jaksatomovic.rba.commons.api.messages.response.AbstractResponse;
import io.github.jaksatomovic.rba.core.features.shared.context.OperationContext;

/**
 * Base interface for all response generator classes.
 * The idea is to have those classes separated from the logic itself to allow
 * potentially different handling of responses that Provisioning component returns.
 *
 * @param <R> Response type
 * @param <C> Context based on which the response is generated.
 * @author Jaksa Tomovic
 */
public interface ResponseGenerator<R extends AbstractResponse, C extends OperationContext>
{
    R generateResponse(C context);
}
