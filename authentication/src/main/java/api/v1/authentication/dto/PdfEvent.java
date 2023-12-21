package api.v1.authentication.dto;

import api.v1.authentication.persistency.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PdfEvent {
    String message;
    Integer status;
    User user;
}
