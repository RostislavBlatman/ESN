package rest.pojos.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import rest.utils.DateDeserializer;

import java.time.LocalDateTime;

@Data
public class LoginErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddThh:mm:ss.SSSZ")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
