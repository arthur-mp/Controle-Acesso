package tcc.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.backend.dto.VeiculoDTO;
import tcc.backend.servico.IVeiculoServico;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private IVeiculoServico veiculoServico;

    @GetMapping("/getAll")
    public ResponseEntity<List<VeiculoDTO>> getAll(){
        return ResponseEntity.ok(veiculoServico.getAll());
    }

    @GetMapping("/getOne")
    public ResponseEntity<VeiculoDTO> getOne(@RequestParam("id") String idVeiculo){
        return ResponseEntity.ok(veiculoServico.getOne(idVeiculo));
    }

    @PostMapping("/create")
    public ResponseEntity<VeiculoDTO> create(@RequestBody VeiculoDTO request){
        return ResponseEntity.ok(veiculoServico.create(request));
    }

    @PostMapping("/createAll")
    public ResponseEntity<List<VeiculoDTO>> createAll(@RequestBody List<VeiculoDTO> veiculos){
        return ResponseEntity.ok(veiculoServico.createAll(veiculos));
    }

    @PutMapping("/edit")
    public ResponseEntity<VeiculoDTO> edit(@RequestBody VeiculoDTO request){
        return ResponseEntity.ok(veiculoServico.edit(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<VeiculoDTO> delete(@RequestParam("id") String idVeiculo){
        return ResponseEntity.ok(veiculoServico.delete(idVeiculo));
    }


}

