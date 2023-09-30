package tcc.backend.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.backend.entidade.Users;

import java.util.Optional;

@Repository
public interface IUsersRepositorio extends MongoRepository<Users, String> {
    Optional<Users> findByEmailAndSenha(String email, String senha);
}
