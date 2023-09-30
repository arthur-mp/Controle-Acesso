package tcc.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.backend.dto.TagsTemporaryDTO;
import tcc.backend.servico.ITagsTemporaryServico;

import java.util.List;

@RestController
@RequestMapping("/tagsTemporary")
public class TagsTemporaryController {
    @Autowired
    private ITagsTemporaryServico tagsTemporaryServico;

    @GetMapping("/getAll")
    public ResponseEntity<List<TagsTemporaryDTO>> getAll(){
        return ResponseEntity.ok(tagsTemporaryServico.getAll());
    }

    @GetMapping("/getOne")
    public ResponseEntity<TagsTemporaryDTO> getOne(@RequestParam("id") String idVeiculo){
        return ResponseEntity.ok(tagsTemporaryServico.getOne(idVeiculo));
    }

    @PostMapping("/create")
    public ResponseEntity<TagsTemporaryDTO> create(@RequestBody TagsTemporaryDTO request){
        return ResponseEntity.ok(tagsTemporaryServico.create(request));
    }

    @PutMapping("/edit")
    public ResponseEntity<TagsTemporaryDTO> edit(@RequestBody TagsTemporaryDTO request){
        return ResponseEntity.ok(tagsTemporaryServico.edit(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TagsTemporaryDTO> delete(@RequestParam("id") String idVeiculo){
        return ResponseEntity.ok(tagsTemporaryServico.delete(idVeiculo));
    }


}

