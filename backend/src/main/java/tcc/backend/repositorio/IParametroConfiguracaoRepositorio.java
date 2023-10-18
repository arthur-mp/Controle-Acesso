package tcc.backend.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.backend.entidade.ParametroConfiguracao;

import java.util.Optional;

@Repository
public interface IParametroConfiguracaoRepositorio extends MongoRepository<ParametroConfiguracao, String> {

    Optional<ParametroConfiguracao> findByNome(String nome);
}
