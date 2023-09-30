package tcc.backend.dto;

import lombok.Data;


@Data
public class UsagesDTO {
    private String id;
    private String nome;
    private String dateUsage;
    private String timeUsage;
    private String idUser;
    private String tagUser;
}
