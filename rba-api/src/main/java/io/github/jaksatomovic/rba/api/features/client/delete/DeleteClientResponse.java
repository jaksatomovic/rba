package io.github.jaksatomovic.rba.api.features.client.delete;

import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.commons.api.messages.response.AbstractResponse;

/**
 * The type delete client response.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class DeleteClientResponse
    extends AbstractResponse
{
    /**
     * Instantiates a new Update country response.
     *
     * @param request      the request
     * @param responseCode the response code
     */
    public DeleteClientResponse(final AbstractRequest request, final ResponseCode responseCode)
    {
        super(request, responseCode);
    }
}
