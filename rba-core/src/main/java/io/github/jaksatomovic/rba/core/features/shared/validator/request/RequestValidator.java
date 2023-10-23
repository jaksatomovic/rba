package io.github.jaksatomovic.rba.core.features.shared.validator.request;

import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.core.features.shared.validator.Validator;

/**
 * Request Validator interface.
 * Each request is validated used a set of rules defined for specific operation.
 *
 * @param <I> input
 * @author Jakša Tomović
 * @since 1.0
 */
public interface RequestValidator<I extends AbstractRequest>
    extends Validator<I>
{
}
