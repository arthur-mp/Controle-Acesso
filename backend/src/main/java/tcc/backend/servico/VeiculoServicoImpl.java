package tcc.backend.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.backend.dto.VeiculoDTO;
import tcc.backend.entidade.Veiculo;
import tcc.backend.repositorio.IVeiculoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServicoImpl implements IVeiculoServico{

    @Autowired
    private IVeiculoRepositorio veiculoRepositorio;


    @Override
    public VeiculoDTO create(VeiculoDTO veiculoDTO) {
        return createOrEdit(veiculoDTO);
    }
    @Override
    public VeiculoDTO edit(VeiculoDTO veiculoDTO) {
        return createOrEdit(veiculoDTO);
    }
    @Override
    public VeiculoDTO getOne(String idVeiculo) {
        Optional<Veiculo> veiculoOptional = veiculoRepositorio.findById(idVeiculo);

        if (veiculoOptional.isPresent()){
            Veiculo veiculo = veiculoOptional.get();
            return veiculoToVeiculoDTO(veiculo);
        }
        return null;
    }

    @Override
    public List<VeiculoDTO> getAll() {
        List<VeiculoDTO> veiculosDTO = new ArrayList<>();

        List<Veiculo> veiculos = veiculoRepositorio.findAll();

        if(!veiculos.isEmpty()){
            veiculos.forEach(veiculo -> {
                veiculosDTO.add(veiculoToVeiculoDTO(veiculo));
            });
        }

        return veiculosDTO;
    }
    @Override
    public VeiculoDTO delete(String idVeiculo) {
        Optional<Veiculo> veiculoOptional = veiculoRepositorio.findById(idVeiculo);

        if (veiculoOptional.isPresent()){
            Veiculo veiculo = veiculoOptional.get();
            veiculoRepositorio.delete(veiculo);

            return veiculoToVeiculoDTO(veiculo);
        }
        return null;
    }
    private VeiculoDTO createOrEdit(VeiculoDTO veiculoDTO){
        Veiculo novoVeiculo = veiculoDTOToVeiculo(veiculoDTO);

        Veiculo veiculo = veiculoRepositorio.save(novoVeiculo);

        VeiculoDTO veiculoDTOCriado = veiculoToVeiculoDTO(veiculo);

        return veiculoDTOCriado;
    }

    private VeiculoDTO veiculoToVeiculoDTO(Veiculo veiculo){
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        if(!veiculo.getId().isEmpty()) veiculoDTO.setId(veiculo.getId());
        veiculoDTO.setModelo(veiculo.getModelo());
        veiculoDTO.setCor(veiculo.getCor());
        veiculoDTO.setPlaca(veiculo.getPlaca());
        veiculoDTO.setMarca(veiculo.getMarca());
        veiculoDTO.setUsuarioId(veiculo.getUsuarioId());

        return veiculoDTO;
    };

    private Veiculo veiculoDTOToVeiculo(VeiculoDTO veiculoDTO){
        Veiculo veiculo = new Veiculo();
        if(!veiculoDTO.getId().isEmpty()) veiculo.setId(veiculoDTO.getId());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setUsuarioId(veiculoDTO.getUsuarioId());

        return veiculo;
    }
}
