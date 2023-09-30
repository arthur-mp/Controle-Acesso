package tcc.backend.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.backend.dto.TagsTemporaryDTO;
import tcc.backend.entidade.TagsTemporary;
import tcc.backend.repositorio.ITagsTemporaryRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagsTemporaryServicoImpl implements ITagsTemporaryServico{

    @Autowired
    private ITagsTemporaryRepositorio tagsTemporaryRepositorio;


    @Override
    public TagsTemporaryDTO create(TagsTemporaryDTO tagsTemporaryDTO) {
        return createOrEdit(tagsTemporaryDTO);
    }
    @Override
    public TagsTemporaryDTO edit(TagsTemporaryDTO tagsTemporaryDTO) {
        return createOrEdit(tagsTemporaryDTO);
    }
    @Override
    public TagsTemporaryDTO getOne(String idTagsTemporaryDTO) {
        Optional<TagsTemporary> tagsTemporaryOptional = tagsTemporaryRepositorio.findById(idTagsTemporaryDTO);

        if (tagsTemporaryOptional.isPresent()){
            TagsTemporary tagsTemporary = tagsTemporaryOptional.get();
            return tagTemporaryToTagsTemporaryDTO(tagsTemporary);
        }
        return null;
    }

    @Override
    public List<TagsTemporaryDTO> getAll() {
        List<TagsTemporaryDTO> tagsTemporaryDTO = new ArrayList<>();

        List<TagsTemporary> tagsTemporaries = tagsTemporaryRepositorio.findAll();

        if(!tagsTemporaries.isEmpty()){
            tagsTemporaries.forEach(tagTemporary -> {
                tagsTemporaryDTO.add(tagTemporaryToTagsTemporaryDTO(tagTemporary));
            });
        }

        return tagsTemporaryDTO;
    }
    @Override
    public TagsTemporaryDTO delete(String idTagsTemporary) {
        Optional<TagsTemporary> tagsTemporaryOptional = tagsTemporaryRepositorio.findById(idTagsTemporary);

        if (tagsTemporaryOptional.isPresent()){
            TagsTemporary tagsTemporary = tagsTemporaryOptional.get();
            tagsTemporaryRepositorio.delete(tagsTemporary);

            return tagTemporaryToTagsTemporaryDTO(tagsTemporary);
        }
        return null;
    }
    private TagsTemporaryDTO createOrEdit(TagsTemporaryDTO veiculoDTO){
        TagsTemporary novoTagsTemporary = tagTemporaryDTOToTagsTemporary(veiculoDTO);

        TagsTemporary tagsTemporary = tagsTemporaryRepositorio.save(novoTagsTemporary);

        TagsTemporaryDTO tagsTemporaryCriado = tagTemporaryToTagsTemporaryDTO(tagsTemporary);

        return tagsTemporaryCriado;
    }

    private TagsTemporaryDTO tagTemporaryToTagsTemporaryDTO(TagsTemporary tagsTemporary){
        TagsTemporaryDTO tagsTemporaryDTO = new TagsTemporaryDTO();
        if(!tagsTemporary.getId().isEmpty()) tagsTemporaryDTO.setId(tagsTemporary.getId());
        tagsTemporaryDTO.setCodeTag(tagsTemporary.getId());

        return tagsTemporaryDTO;
    };

    private TagsTemporary tagTemporaryDTOToTagsTemporary(TagsTemporaryDTO tagsTemporaryDTO){
        TagsTemporary tagsTemporary = new TagsTemporary();
        if(!tagsTemporaryDTO.getId().isEmpty()) tagsTemporary.setId(tagsTemporaryDTO.getId());
        tagsTemporary.setCodeTag(tagsTemporaryDTO.getId());

        return tagsTemporary;
    }
}
