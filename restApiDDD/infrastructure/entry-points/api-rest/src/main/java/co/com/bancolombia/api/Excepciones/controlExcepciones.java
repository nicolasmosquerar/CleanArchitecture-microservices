package co.com.bancolombia.api.Excepciones;

import co.com.bancolombia.model.businessexception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class controlExcepciones {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDto> errorDtoResponseEntity(BusinessException exception){
        ErrorDto error = ErrorDto.builder().code("404")
                .message(exception.getMessage())
                .description(exception.getDescription())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorDto> handleMissingPathVariableException(MissingPathVariableException ex) {
        ErrorDto error = new ErrorDto("404", "ID is required","des");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDto> handleIoException(IOException ex) {
        ErrorDto error = new ErrorDto("404", "ID is required","des");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDto> handleIoException(NumberFormatException ex) {
        ErrorDto error = new ErrorDto("401", "ingrese un id valido","id invalido");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}


