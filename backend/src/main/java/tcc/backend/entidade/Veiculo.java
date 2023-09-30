package tcc.backend.entidade;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Veiculo {

    @Id
    private String id;
    private String modelo;
    private String marca;
    private String placa;
    private String cor;
    private String usuarioId;
}
