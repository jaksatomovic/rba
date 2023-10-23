package io.github.jaksatomovic.rba.core.features.client.search;

import io.github.jaksatomovic.rba.api.shared.CardStatus;
import io.github.jaksatomovic.rba.commons.api.ResponseCode;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.commons.utility.ApplicationResponseCode;
import io.github.jaksatomovic.rba.core.exception.AppException;
import io.github.jaksatomovic.rba.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;
import io.github.jaksatomovic.rba.core.persistence.store.ClientStore;
import io.github.jaksatomovic.rba.core.persistence.store.FileStore;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

/**
 *
 */
@Service
public class SearchClientResourceMaintainer
    implements ResourceMaintainer<SearchClientContext>
{
    private static final String FILENAME_SEPARATOR = "_";
    private static final String FILE_EXTENSION     = ".csv";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ClientStore clientStore;
    private final FileStore   fileStore;

    /**
     * Instantiates a new Search client resource maintainer.
     *
     * @param clientStore the client store
     * @param fileStore   the file store
     */
    public SearchClientResourceMaintainer(final ClientStore clientStore, final FileStore fileStore)
    {
        this.clientStore = Defense.notNull(clientStore, ClientStore.class.getSimpleName());
        this.fileStore = Defense.notNull(fileStore, FileStore.class.getSimpleName());
    }

    @Override
    public void resolve(final SearchClientContext context)
    {
        createFileIfNotExist(context);

        updateDbClientStatus(context);

        logger.debug("[MAINTAINER] - Search Client - [OK]");
    }

    private void updateDbClientStatus(final SearchClientContext context)
    {
        DbClient dbClient = context.getExistingClient().get();
        dbClient.setStatus(CardStatus.REQUESTED);

        clientStore.saveEntity(dbClient);

        logger.debug("Client updated - OK");
    }

    private void createFileIfNotExist(final SearchClientContext context)
    {
        if (context.getExistingFile().isPresent())
        {
            return;
        }

        createFile(context.getExistingClient().get());
    }

    private void createFile(final DbClient dbClient)
    {
        logger.debug("CSV Generate - Started");

        String csvFileName = resolveFilename(dbClient);

        generateCSV(dbClient, csvFileName);

        resolveDbFile(dbClient, csvFileName);

        logger.debug("CSV Generate - End");
    }

    private void resolveDbFile(final DbClient dbClient, final String csvFileName)
    {
        DbFile dbFile = new DbFile();
        dbFile.setClientId(dbClient.getId());
        dbFile.setName(csvFileName);
        dbFile.setActive(true);

        fileStore.saveEntity(dbFile);

        logger.debug("File stored - OK");
    }

    private static String resolveFilename(final DbClient dbClient)
    {
        return dbClient.getIdentificationNumber() + FILENAME_SEPARATOR + Instant.now().getEpochSecond() + FILE_EXTENSION;
    }

    private void generateCSV(final DbClient dbClient, final String csvFileName)
    {
        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFileName));
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)
        )
        {
            printer.printRecord(dbClient.getFirstName(), dbClient.getLastName(), dbClient.getIdentificationNumber(),
                CardStatus.REQUESTED);

            printer.flush();
        }
        catch (IOException e)
        {
            throw new AppException(ApplicationResponseCode.technicalErrorCode("CSV File Creation", ResponseCode.INTERNAL_SERVER_ERROR.value(), "error happened while generating csv file"));
        }
    }
}
