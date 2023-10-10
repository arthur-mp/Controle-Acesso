package tcc.backend.servico;

import tcc.backend.dto.VeiculoDTO;

import java.util.List;

public interface IVeiculoServico {

    VeiculoDTO create(VeiculoDTO veiculoDTO);

    List<VeiculoDTO> createAll(List<VeiculoDTO> veiculoDTOList);

    VeiculoDTO edit(VeiculoDTO veiculoDTO);

    VeiculoDTO getOne(String idVeiculo);

    List<VeiculoDTO> getAll();

    VeiculoDTO delete(String idVeiculo);
}
