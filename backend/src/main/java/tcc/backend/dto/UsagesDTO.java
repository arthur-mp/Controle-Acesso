package tcc.backend.dto;

import lombok.Data;


@Data
public class UsagesDTO {
    private String id;
    private String dateUsageInput;
    private String timeUsageInput;
    private String dateUsageOutput;
    private String timeUsageOutput;
    private String idUser;
    private String tagUser;
}
