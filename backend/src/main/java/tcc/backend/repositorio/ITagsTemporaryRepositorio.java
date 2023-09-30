package tcc.backend.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.backend.entidade.TagsTemporary;

@Repository
public interface ITagsTemporaryRepositorio extends MongoRepository<TagsTemporary, String> {
}
