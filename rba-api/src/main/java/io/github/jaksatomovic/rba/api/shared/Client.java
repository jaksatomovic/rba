package io.github.jaksatomovic.rba.api.shared;

import io.github.jaksatomovic.rba.commons.api.model.ApiEntity;

/**
 * The type Client.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class Client
    extends ApiEntity
{
    private String firstName;
    private String lastName;
    private Long identificationNumber;
    private CardStatus status;

    public Client()
    {
    }

    public Client(final String firstName, final String lastName, final Long identificationNumber, final CardStatus status)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationNumber = identificationNumber;
        this.status = status;
    }

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

    public CardStatus getStatus()
    {
        return status;
    }

    public void setStatus(final CardStatus status)
    {
        this.status = status;
    }

    @Override
    protected void appendFields(final StringBuilder sb)
    {
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", identificationNumber=").append(identificationNumber);
        sb.append(", status=").append(status);
    }
}
