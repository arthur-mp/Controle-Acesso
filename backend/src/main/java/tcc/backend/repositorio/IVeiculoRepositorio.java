package tcc.backend.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.backend.entidade.Veiculo;

@Repository
public interface IVeiculoRepositorio extends MongoRepository<Veiculo, String> {
}
