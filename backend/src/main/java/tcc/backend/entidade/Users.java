package tcc.backend.entidade;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Users {
    @Id
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
