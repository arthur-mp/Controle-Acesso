package tcc.backend.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tcc.backend.entidade.Usages;

import java.util.Optional;

@Repository
public interface IUsagesRepositorio extends MongoRepository<Usages, String> {

    @Query("{ 'tagUser' : ?0, 'dateUsageOutput' : null }")
    Optional<Usages> findByTagUserAndNotDateUsageOutput(String codigoTag);
}
