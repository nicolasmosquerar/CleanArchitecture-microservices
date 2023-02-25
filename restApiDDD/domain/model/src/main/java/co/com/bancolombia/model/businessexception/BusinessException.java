package co.com.bancolombia.model.businessexception;
import lombok.Getter;

@Getter

public class BusinessException extends  Exception{
private final String description;
private final String message;
public BusinessException(String message, String description){
    super();
    this.message=message;
    this.description = description;
}



}
