package io.github.jaksatomovic.rba.core.persistence.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

/**
 * The type Db file.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Entity
@Table (name = DbFile.TABLE_NAME)
@SequenceGenerator (name = DbFile.SEQUENCE_NAME,
                    sequenceName = DbFile.SEQUENCE_NAME,
                    allocationSize = DbEntity.ALLOCATION_SIZE)
public class DbFile
    extends DbEntity<Long, DbFile>
{
    /**
     * The constant TABLE_NAME.
     */
    public static final  String TABLE_NAME       = "file";
    /**
     * The constant SEQUENCE_NAME.
     */
    public static final  String SEQUENCE_NAME    = "file_id_seq";
    private static final long   serialVersionUID = getSerialVersion();

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
                     generator = SEQUENCE_NAME)
    @Column (name = "id",
             nullable = false)
    private Long id;

    @Column (name = "name",
             nullable = false)
    private String name;

    @Column (name = "client_id")
    private Long clientId;

    @Column (name = "active",
             nullable = false)
    private Boolean active;


    public DbFile()
    {
    }

    public DbFile(final Long id, final String name, final Long clientId, final Boolean active)
    {
        this.id = id;
        this.name = name;
        this.clientId = clientId;
        this.active = active;
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

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Long getClientId()
    {
        return clientId;
    }

    public void setClientId(final Long clientId)
    {
        this.clientId = clientId;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(final Boolean active)
    {
        this.active = active;
    }

    public enum DbFileMapping
        implements TableMapping
    {
        ID("id", "id"),

        NAME("name", "name"),

        CLIENT_ID("client_id", "client_id"),

        ACTIVE("active", "active");

        private final String field;
        private final String column;

        DbFileMapping(String field, String column)
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
        DbFile dbFile = (DbFile)o;
        return Objects.equals(id, dbFile.id) && Objects.equals(name, dbFile.name) && Objects.equals(clientId, dbFile.clientId) && Objects.equals(active, dbFile.active);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id, name, clientId, active);
    }
}
