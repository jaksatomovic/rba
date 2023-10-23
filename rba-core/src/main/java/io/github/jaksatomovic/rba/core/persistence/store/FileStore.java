package io.github.jaksatomovic.rba.core.persistence.store;

import io.github.jaksatomovic.rba.commons.api.validation.Check;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;
import io.github.jaksatomovic.rba.core.persistence.repository.ClientRepository;
import io.github.jaksatomovic.rba.core.persistence.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type file store.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class FileStore
    extends Store<Long, DbFile, FileRepository>
{
    private final FileRepository repository;

    /**
     * Instantiates a new file store.
     *
     * @param repository    the repository
     * @param entityManager the entity manager
     */
    protected FileStore(final FileRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
        this.repository = Defense.notNull(repository, FileRepository.class.getSimpleName());
    }

    public FileRepository getRepository()
    {
        return repository;
    }

    @Override
    protected Class<DbFile> getEntityClass()
    {
        return DbFile.class;
    }

    /**
     * Fetch all clients optional.
     *
     * @return the optional
     */
    public Optional<Map<String, Long>> fetchAllCountries()
    {
        List<DbFile> dbCountries = loadTable();

        if (Check.notEmpty(dbCountries))
        {
            return Optional.of(dbCountries.stream()
                                          .collect(Collectors.toMap(DbFile::getName, DbFile::getId)));
        }

        return Optional.empty();
    }
}
