package io.github.jaksatomovic.rba.api.features.client.create;

import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.commons.api.messages.response.AbstractResponse;

/**
 * The type Update country response.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class CreateClientResponse
    extends AbstractResponse
{
    /**
     * Instantiates a new Update country response.
     *
     * @param request      the request
     * @param responseCode the response code
     */
    public CreateClientResponse(final AbstractRequest request, final ResponseCode responseCode)
    {
        super(request, responseCode);
    }
}
