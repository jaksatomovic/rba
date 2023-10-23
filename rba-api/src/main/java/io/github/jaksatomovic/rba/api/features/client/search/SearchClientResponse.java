package io.github.jaksatomovic.rba.api.features.client.search;

import io.github.jaksatomovic.rba.api.shared.Client;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.rba.commons.api.messages.response.AbstractPayloadResponse;

/**
 * The type Search client response.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class SearchClientResponse
    extends AbstractPayloadResponse<Client>
{
    public SearchClientResponse(final AbstractRequest request, final ResponseCode code, final Client payload)
    {
        super(request, code, payload);
    }
}
