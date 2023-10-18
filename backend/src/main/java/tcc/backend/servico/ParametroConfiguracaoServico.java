package tcc.backend.servico;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.backend.dto.ParametroConfiguracaoDTO;
import tcc.backend.entidade.ParametroConfiguracao;
import tcc.backend.entidade.TagsTemporary;
import tcc.backend.repositorio.IParametroConfiguracaoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParametroConfiguracaoServico implements IParametroConfiguracaoServico {

    @Autowired
    private IParametroConfiguracaoRepositorio parametroConfiguracaoRepositorio;

    @Override
    public ParametroConfiguracaoDTO create(ParametroConfiguracaoDTO parametroConfiguracaoDTO) {
        return createOrEdit(parametroConfiguracaoDTO);
    }

    @Override
    public ParametroConfiguracaoDTO edit(ParametroConfiguracaoDTO parametroConfiguracaoDTO) {
        return createOrEdit(parametroConfiguracaoDTO);
    }

    @Override
    public ParametroConfiguracaoDTO getOne(String nomeParametro) {
        Optional<ParametroConfiguracao> parametroConfiguracaoOptional = parametroConfiguracaoRepositorio.findByNome(nomeParametro);

        if(parametroConfiguracaoOptional.isPresent()){
            ParametroConfiguracao parametroConfiguracao = parametroConfiguracaoOptional.get();
            return parametroConfiguracaoToParametroConfiguracaoDTO(parametroConfiguracao);
        }
        return null;
    }

    @Override
    public List<ParametroConfiguracaoDTO> getAll() {
        List<ParametroConfiguracaoDTO> parametroConfiguracaoDTOList = new ArrayList<>();

        List<ParametroConfiguracao> parametroConfiguracaos = parametroConfiguracaoRepositorio.findAll();

        if(!parametroConfiguracaos.isEmpty()){
            parametroConfiguracaos.forEach(parametroConfiguracao -> {
                parametroConfiguracaoDTOList.add(parametroConfiguracaoToParametroConfiguracaoDTO(parametroConfiguracao));
            });
        }

        return parametroConfiguracaoDTOList;
    }

    @Override
    public ParametroConfiguracaoDTO delete(String idParametroConfiguracao) {
        Optional<ParametroConfiguracao> parametroConfiguracaoOptional = parametroConfiguracaoRepositorio.findById(idParametroConfiguracao);

        if (parametroConfiguracaoOptional.isPresent()){
            ParametroConfiguracao parametroConfiguracao = parametroConfiguracaoOptional.get();
            parametroConfiguracaoRepositorio.delete(parametroConfiguracao);

            return parametroConfiguracaoToParametroConfiguracaoDTO(parametroConfiguracao);
        }
        return null;
    }

    private ParametroConfiguracaoDTO createOrEdit(ParametroConfiguracaoDTO parametroConfiguracaoDTO){
        ParametroConfiguracao novoParametroConfiguracao = parametroConfiguracaoDTOtoParametroConfiguracao(parametroConfiguracaoDTO);
        ParametroConfiguracao parametroConfiguracao = parametroConfiguracaoRepositorio.save(novoParametroConfiguracao);
        ParametroConfiguracaoDTO parametroConfiguracaoCriado = parametroConfiguracaoToParametroConfiguracaoDTO(parametroConfiguracao);

        return parametroConfiguracaoCriado;
    }

    private ParametroConfiguracaoDTO parametroConfiguracaoToParametroConfiguracaoDTO(ParametroConfiguracao parametroConfiguracao){
        ParametroConfiguracaoDTO parametroConfiguracaoDTO = new ParametroConfiguracaoDTO();
        if (!parametroConfiguracao.getId().isEmpty()) parametroConfiguracaoDTO.setId(parametroConfiguracao.getId());
        parametroConfiguracaoDTO.setNome(parametroConfiguracao.getNome());
        parametroConfiguracaoDTO.setValor(parametroConfiguracao.getValor());

        return parametroConfiguracaoDTO;
    }

    private ParametroConfiguracao parametroConfiguracaoDTOtoParametroConfiguracao(ParametroConfiguracaoDTO parametroConfiguracaoDTO){
        ParametroConfiguracao parametroConfiguracao = new ParametroConfiguracao();
        if (!parametroConfiguracaoDTO.getId().isEmpty()) parametroConfiguracao.setId(parametroConfiguracaoDTO.getId());
        parametroConfiguracao.setNome(parametroConfiguracaoDTO.getNome());
        parametroConfiguracao.setValor(parametroConfiguracaoDTO.getValor());

        return parametroConfiguracao;
    }
}
