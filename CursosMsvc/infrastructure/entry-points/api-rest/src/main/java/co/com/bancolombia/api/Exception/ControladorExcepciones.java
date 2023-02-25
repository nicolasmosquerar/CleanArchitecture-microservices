package co.com.bancolombia.api.Exception;

import co.com.bancolombia.model.excepcion.ExcepcionNegocio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControladorExcepciones extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> errorNullPointer(NullPointerException nullPointerException){
        ErrorDto error = ErrorDto.builder().code("404")
                .detail(nullPointerException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> errorUsuarioNoEncontrado(ExcepcionNegocio excepcionNegocio){
        ErrorDto error = ErrorDto.builder()
                .code("404")
                .detail(excepcionNegocio.getDescription() + excepcionNegocio.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> erroresRuntime(Exception ex){
        ErrorDto error = ErrorDto.builder()
                .code("500")
                .detail("error al ejecutar el programa: "+ ex.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
