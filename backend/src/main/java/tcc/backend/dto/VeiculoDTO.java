package tcc.backend.dto;

import lombok.Data;

@Data
public class VeiculoDTO {
    private String id;
    private String modelo;
    private String marca;
    private String placa;
    private String cor;
    private String usuarioId;
}
