package io.github.jaksatomovic.rba.core.features.shared;

import io.github.jaksatomovic.rba.api.features.client.create.CreateClientRequest;
import io.github.jaksatomovic.rba.api.features.client.delete.DeleteClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientRequest;
import io.github.jaksatomovic.rba.api.features.client.search.SearchClientResponse;
import io.github.jaksatomovic.rba.api.shared.CardStatus;
import io.github.jaksatomovic.rba.api.shared.Client;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.core.features.client.create.CreateClientContext;
import io.github.jaksatomovic.rba.core.features.client.delete.DeleteClientContext;
import io.github.jaksatomovic.rba.core.features.client.search.SearchClientContext;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;

import java.util.Optional;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class AbstractTest
{

    private static final Long    IDENTIFICATION_NUMBER = 123123123123123L;
    private static final String  FIRST_NAME            = "Ime";
    private static final String  LAST_NAME             = "Prezime";
    private static final String  FILE_NAME             = "csv_file_name.csv";
    public static final  long    ID                    = 1L;
    public static final  boolean FILE_IS_ACTIVE_STATUS = true;
    public static final  long    CLIENT_ID             = 11L;

    protected CreateClientRequest prepareCreateClientRequest()
    {
        CreateClientRequest request = new CreateClientRequest();

        request.setFirstName(FIRST_NAME);
        request.setLastName(LAST_NAME);
        request.setIdentificationNumber(IDENTIFICATION_NUMBER);

        return request;
    }

    protected CreateClientContext getCreateClientContext(final CreateClientRequest request)
    {
        return new CreateClientContext()
        {
            @Override
            public Optional<DbClient> getExistingClient()
            {
                return Optional.empty();
            }

            @Override
            public CreateClientRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    protected DeleteClientRequest prepareDeleteClientRequest()
    {
        DeleteClientRequest request = new DeleteClientRequest();

        request.setIdentificationNumber(IDENTIFICATION_NUMBER);

        return request;
    }

    protected DeleteClientContext getDeleteClientContext(final DeleteClientRequest request)
    {
        return new DeleteClientContext()
        {
            @Override
            public Optional<DbClient> getExistingClient()
            {
                return Optional.of(prepareDbClient());
            }

            @Override
            public Optional<DbFile> getExistingFile()
            {
                return Optional.of(new DbFile());
            }

            @Override
            public DeleteClientRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    protected SearchClientRequest prepareSearchClientRequest()
    {
        SearchClientRequest request = new SearchClientRequest();
        request.setIdentificationNumber(IDENTIFICATION_NUMBER);
        return request;
    }

    protected SearchClientResponse prepareSearchClientResponse(final SearchClientRequest request)
    {
        return new SearchClientResponse(request, ResponseCode.OK, new Client(FIRST_NAME, LAST_NAME, IDENTIFICATION_NUMBER, CardStatus.REQUESTED));
    }

    protected SearchClientContext getSearchClientContext(final SearchClientRequest request)
    {

        return new SearchClientContext()
        {

            @Override
            public Optional<DbClient> getExistingClient()
            {
                return Optional.of(prepareDbClient());
            }

            @Override
            public Optional<DbFile> getExistingFile()
            {
                return Optional.empty();
            }

            @Override
            public SearchClientRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    private DbClient prepareDbClient()
    {
        DbClient dbClient = new DbClient();
        dbClient.setFirstName(FIRST_NAME);
        dbClient.setLastName(LAST_NAME);
        dbClient.setId(ID);
        dbClient.setIdentificationNumber(IDENTIFICATION_NUMBER);
        dbClient.setStatus(CardStatus.REQUESTED);
        return dbClient;
    }

    private DbFile prepareDbFile()
    {
        DbFile dbFile = new DbFile();
        dbFile.setClientId(CLIENT_ID);
        dbFile.setId(ID);
        dbFile.setName(FILE_NAME);
        dbFile.setActive(FILE_IS_ACTIVE_STATUS);
        return dbFile;
    }



    protected Client prepareClientPayload()
    {
        Client client = new Client();

        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setIdentificationNumber(IDENTIFICATION_NUMBER);
        client.setStatus(CardStatus.REQUESTED);

        return client;
    }
}
