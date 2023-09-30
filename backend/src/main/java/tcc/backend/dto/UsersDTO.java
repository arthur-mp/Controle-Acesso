package tcc.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersDTO {
    private String id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String matricula;
    private String email;
    private String codigoTag;
    private String senha;
    private List<String> veiculosId;
    private List<String> usagesId;
}
