package api.v1.authentication.Dto;

import api.v1.authentication.persistency.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdfEvent {
    String message;
    Integer status;
    User user;
}
