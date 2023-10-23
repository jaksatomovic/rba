package io.github.jaksatomovic.rba.core.persistence.store;

import io.github.jaksatomovic.rba.commons.api.validation.Check;
import io.github.jaksatomovic.rba.commons.api.validation.Defense;
import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import io.github.jaksatomovic.rba.core.persistence.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type clients store.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class ClientStore
    extends Store<Long, DbClient, ClientRepository>
{
    private final ClientRepository repository;

    /**
     * Instantiates a new clients store.
     *
     * @param repository    the repository
     * @param entityManager the entity manager
     */
    protected ClientStore(final ClientRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
        this.repository = Defense.notNull(repository, ClientRepository.class.getSimpleName());
    }

    public ClientRepository getRepository()
    {
        return repository;
    }

    @Override
    protected Class<DbClient> getEntityClass()
    {
        return DbClient.class;
    }

    /**
     * Fetch all clients optional.
     *
     * @return the optional
     */
    public Optional<Map<String, Long>> fetchAllCountries()
    {
        List<DbClient> dbCountries = loadTable();

        if (Check.notEmpty(dbCountries))
        {
            return Optional.of(dbCountries.stream()
                                          .collect(Collectors.toMap(DbClient::getFirstName, DbClient::getId)));
        }

        return Optional.empty();
    }
}
