package tcc.backend.servico;

import tcc.backend.dto.TagsTemporaryDTO;

import java.util.List;

public interface ITagsTemporaryServico {

    TagsTemporaryDTO create(TagsTemporaryDTO tagsTemporaryDTODTO);

    TagsTemporaryDTO edit(TagsTemporaryDTO tagsTemporaryDTODTO);

    TagsTemporaryDTO getOne(String idTagTemporary);

    List<TagsTemporaryDTO> getAll();

    TagsTemporaryDTO delete(String idTagTemporary);
}
