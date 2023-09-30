package tcc.backend.servico;

import tcc.backend.dto.UsagesDTO;

import java.util.List;

public interface IUsagesServico {

    UsagesDTO create(UsagesDTO usagesDTO);

    UsagesDTO edit(UsagesDTO usagesDTO);

    UsagesDTO getOne(String idUsages);

    List<UsagesDTO> getAll();

    UsagesDTO delete(String idUsages);
}
