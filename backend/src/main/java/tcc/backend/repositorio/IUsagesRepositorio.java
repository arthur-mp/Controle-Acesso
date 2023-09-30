package tcc.backend.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.backend.entidade.Usages;

@Repository
public interface IUsagesRepositorio extends MongoRepository<Usages, String> {
}
