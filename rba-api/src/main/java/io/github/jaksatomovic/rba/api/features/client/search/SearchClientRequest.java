package io.github.jaksatomovic.rba.api.features.client.search;

import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;

/**
 * The type create client request.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class SearchClientRequest
    extends AbstractRequest
{
    private Long identificationNumber;

    public Long getIdentificationNumber()
    {
        return identificationNumber;
    }

    public void setIdentificationNumber(final Long identificationNumber)
    {
        this.identificationNumber = identificationNumber;
    }

    @Override
    protected void appendFields(final StringBuilder sb)
    {
        sb.append(", identificationNumber=").append(identificationNumber);
    }
}
