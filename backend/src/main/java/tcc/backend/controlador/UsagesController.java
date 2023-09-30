package tcc.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.backend.dto.UsagesDTO;
import tcc.backend.servico.IUsagesServico;

import java.util.List;

@RestController
@RequestMapping("/usages")
public class UsagesController {
    @Autowired
    private IUsagesServico usagesServico;

    @GetMapping("/getAll")
    public ResponseEntity<List<UsagesDTO>> getAll(){
        return ResponseEntity.ok(usagesServico.getAll());
    }

    @GetMapping("/getOne")
    public ResponseEntity<UsagesDTO> getOne(@RequestParam("id") String idUsages){
        return ResponseEntity.ok(usagesServico.getOne(idUsages));
    }

    @PostMapping("/create")
    public ResponseEntity<UsagesDTO> create(@RequestBody UsagesDTO request){
        return ResponseEntity.ok(usagesServico.create(request));
    }

    @PutMapping("/edit")
    public ResponseEntity<UsagesDTO> edit(@RequestBody UsagesDTO request){
        return ResponseEntity.ok(usagesServico.edit(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UsagesDTO> delete(@RequestParam("id") String idUsages){
        return ResponseEntity.ok(usagesServico.delete(idUsages));
    }


}

