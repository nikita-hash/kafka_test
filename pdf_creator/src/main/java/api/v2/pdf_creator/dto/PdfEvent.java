package api.v2.pdf_creator.dto;
import api.v2.pdf_creator.persistency.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PdfEvent {
    String message;
    Integer status;
    User user;
}
