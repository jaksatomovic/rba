package io.github.jaksatomovic.rba.core.persistence.repository;

import io.github.jaksatomovic.rba.core.persistence.domain.DbClient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Repository
public interface ClientRepository
    extends CrudRepository<DbClient, Long>, JpaSpecificationExecutor<DbClient>
{
    List<DbClient> findAll();

    Optional<DbClient> getByIdentificationNumber(final Long identificationNumber);
}
