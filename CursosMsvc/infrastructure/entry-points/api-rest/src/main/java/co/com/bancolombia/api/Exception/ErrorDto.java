package co.com.bancolombia.api.Exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private String code;
    private String detail;
}
