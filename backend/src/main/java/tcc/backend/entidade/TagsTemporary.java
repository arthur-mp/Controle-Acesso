package tcc.backend.entidade;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class TagsTemporary {
    @Id
    private String id;
    private String codeTag;
}
