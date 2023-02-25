package co.com.bancolombia.model.excepcion;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExcepcionNegocio extends  Exception{
    private final String description;
    private final String message;

    public ExcepcionNegocio(String message, String description){
        super();
        this.message=message;
        this.description = description;
    }



}
