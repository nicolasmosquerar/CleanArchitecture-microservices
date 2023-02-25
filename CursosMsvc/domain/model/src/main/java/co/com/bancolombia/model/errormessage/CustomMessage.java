package co.com.bancolombia.model.errormessage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CustomMessage {
    private String status;
    private String detail;
    private String method;
    private String action;
}
