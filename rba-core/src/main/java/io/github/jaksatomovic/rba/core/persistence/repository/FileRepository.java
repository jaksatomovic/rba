package io.github.jaksatomovic.rba.core.persistence.repository;

import io.github.jaksatomovic.rba.core.persistence.domain.DbFile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository
    extends CrudRepository<DbFile, Long>, JpaSpecificationExecutor<DbFile>
{
    List<DbFile> findAll();
    Optional<DbFile> getByClientId(final Long clientID);
}
