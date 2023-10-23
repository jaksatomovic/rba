package io.github.jaksatomovic.rba.api.features.client.create;

import io.github.jaksatomovic.rba.commons.api.messages.request.AbstractRequest;

/**
 * The type create client request.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class CreateClientRequest
    extends AbstractRequest
{
    private String firstName;
    private String lastName;
    private Long   identificationNumber;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

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
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", identificationNumber=").append(identificationNumber);
    }
}
