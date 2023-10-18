package tcc.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.backend.dto.ParametroConfiguracaoDTO;
import tcc.backend.servico.IParametroConfiguracaoServico;

import java.util.List;

@RestController
@RequestMapping("/parametroConfiguracao")
public class ParametroConfiguracaoController {

    @Autowired
    private IParametroConfiguracaoServico parametroConfiguracaoServico;

    @GetMapping("/getAll")
    public ResponseEntity<List<ParametroConfiguracaoDTO>> getAll(){
        return ResponseEntity.ok(parametroConfiguracaoServico.getAll());
    }

    @GetMapping("/getOne")
    public ResponseEntity<ParametroConfiguracaoDTO> getOne(@RequestParam("nomeParametro") String nomeParametro){
        return ResponseEntity.ok(parametroConfiguracaoServico.getOne(nomeParametro));
    }

    @PostMapping("/create")
    public ResponseEntity<ParametroConfiguracaoDTO> create(@RequestBody ParametroConfiguracaoDTO request){
        return ResponseEntity.ok(parametroConfiguracaoServico.create(request));
    }

    @PutMapping("/edit")
    public ResponseEntity<ParametroConfiguracaoDTO> edit(@RequestBody ParametroConfiguracaoDTO request){
        return ResponseEntity.ok(parametroConfiguracaoServico.edit(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ParametroConfiguracaoDTO> delete(@RequestParam("id") String idParametroConfiguracao){
        return ResponseEntity.ok(parametroConfiguracaoServico.delete(idParametroConfiguracao));
    }

}
