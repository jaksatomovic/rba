package io.github.jaksatomovic.rba.core.features.shared.validator;

/**
 * Validator interface.
 *
 * @param <I> input
 * @author Jakša Tomović
 * @since 1.0
 */
public interface Validator<I>
{
    void validate(I input);
}
