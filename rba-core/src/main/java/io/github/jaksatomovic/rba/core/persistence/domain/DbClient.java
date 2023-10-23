package io.github.jaksatomovic.rba.core.persistence.domain;

import io.github.jaksatomovic.rba.api.shared.CardStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

/**
 * The type Db client.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Entity
@Table (name = DbClient.TABLE_NAME)
@SequenceGenerator (name = DbClient.SEQUENCE_NAME,
                    sequenceName = DbClient.SEQUENCE_NAME,
                    allocationSize = DbEntity.ALLOCATION_SIZE)
public class DbClient
    extends DbEntity<Long, DbClient>
{
    /**
     * The constant TABLE_NAME.
     */
    public static final  String TABLE_NAME       = "client";
    /**
     * The constant SEQUENCE_NAME.
     */
    public static final  String SEQUENCE_NAME    = "client_id_seq";
    private static final long   serialVersionUID = getSerialVersion();

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
                     generator = SEQUENCE_NAME)
    @Column (name = "id",
             nullable = false)
    private Long id;

    @Column (name = "first_name",
             nullable = false)
    private String firstName;

    @Column (name = "last_name",
             nullable = false)
    private String lastName;

    @Column (name = "identification_number",
             nullable = false)
    private Long identificationNumber;

    @Enumerated (EnumType.STRING)
    @Column (name = "status",
             nullable = false)
    private CardStatus status;

    public DbClient()
    {
    }

    public DbClient(final Long id, final String firstName, final String lastName, final Long identificationNumber, final CardStatus status)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationNumber = identificationNumber;
        this.status = status;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(final Long id)
    {
        this.id = id;
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

    public enum DbClientMapping
        implements TableMapping
    {
        ID("id", "id"),

        FIRST_NAME("firstName", "first_name"),

        LAST_NAME("lastName", "last_name"),

        IDENTIFICATION_NUMBER("identificationNumber", "identification_number"),

        STATUS("status", "status");

        private final String field;
        private final String column;

        DbClientMapping(String field, String column)
        {
            this.field = field;
            this.column = column;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getField()
        {
            return this.field;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getColumn()
        {
            return this.column;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString()
        {
            return String.format("%s::[field=%s, column=%s]", name(), getField(), getColumn());
        }
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        DbClient dbClient = (DbClient)o;
        return Objects.equals(id, dbClient.id) && Objects.equals(firstName, dbClient.firstName) && Objects.equals(lastName, dbClient.lastName) && Objects.equals(identificationNumber, dbClient.identificationNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id, firstName, lastName, identificationNumber);
    }
}
