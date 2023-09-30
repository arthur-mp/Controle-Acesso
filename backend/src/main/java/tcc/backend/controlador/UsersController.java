package tcc.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.backend.dto.LoginDTO;
import tcc.backend.dto.UsersDTO;
import tcc.backend.servico.IUsersServico;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private IUsersServico usersServico;

    @GetMapping("/getAll")
    public ResponseEntity<List<UsersDTO>> getAll(){
        return ResponseEntity.ok(usersServico.getAll());
    }

    @GetMapping("/getOne")
    public ResponseEntity<UsersDTO> getOne(@RequestParam("id") String idVeiculo){
        return ResponseEntity.ok(usersServico.getOne(idVeiculo));
    }

    @PostMapping("/create")
    public ResponseEntity<UsersDTO> create(@RequestBody UsersDTO request){
        return ResponseEntity.ok(usersServico.create(request));
    }

    @PutMapping("/edit")
    public ResponseEntity<UsersDTO> edit(@RequestBody UsersDTO request){
        return ResponseEntity.ok(usersServico.edit(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UsersDTO> delete(@RequestParam("id") String idVeiculo){
        return ResponseEntity.ok(usersServico.delete(idVeiculo));
    }
    @PostMapping("/login")
    public ResponseEntity<UsersDTO> login(@RequestBody LoginDTO request){
        return ResponseEntity.ok(usersServico.login(request));
    }

}

