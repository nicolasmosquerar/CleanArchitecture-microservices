package co.com.bancolombia.api.Excepciones;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private String message;
    private String code;
    private String description;
}
