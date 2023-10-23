package io.github.jaksatomovic.rba.core.utility;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface Closure<T> {
    T execute() throws Exception;
}
