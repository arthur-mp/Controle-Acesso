package tcc.backend.entidade;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Usages {
    @Id
    private String id;
    private String dateUsageInput;
    private String timeUsageInput;
    private String dateUsageOutput;
    private String timeUsageOutput;
    private String idUser;
    private String tagUser;
}
