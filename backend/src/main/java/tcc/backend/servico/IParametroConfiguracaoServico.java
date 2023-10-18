package tcc.backend.servico;

import tcc.backend.dto.ParametroConfiguracaoDTO;
import tcc.backend.entidade.ParametroConfiguracao;

import java.util.List;

public interface IParametroConfiguracaoServico {

    ParametroConfiguracaoDTO create(ParametroConfiguracaoDTO parametroConfiguracaoDTO);

    ParametroConfiguracaoDTO edit(ParametroConfiguracaoDTO parametroConfiguracaoDTO);

    ParametroConfiguracaoDTO getOne(String nomeParametro);

    List<ParametroConfiguracaoDTO> getAll();

    ParametroConfiguracaoDTO delete(String idParametroConfiguracao);
}
