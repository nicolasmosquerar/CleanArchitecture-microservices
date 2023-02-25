package co.com.bancolombia.api;
import co.com.bancolombia.model.curso.Curso;
import co.com.bancolombia.model.errormessage.CustomMessage;
import co.com.bancolombia.model.excepcion.ExcepcionNegocio;
import co.com.bancolombia.model.usuario.Usuario;
import co.com.bancolombia.usecase.curso.CursoUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/curso", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Log4j2
public class ApiRest {
private final CursoUseCase cursoUseCase;


    @GetMapping(path = "/listarCursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> lista = cursoUseCase.listarCursos();
        log.info(CustomMessage.builder()
                .action("consumo API")
                .detail("consumo correcto")
                .method("POST")
                .status("200")
                .build())
        ;

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping(path = "/buscarCurso/{id}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable Long id) throws ExcepcionNegocio {
        Curso curso = cursoUseCase.buscarCurso(id);

        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @PostMapping(path = "/crearCurso")
    public ResponseEntity<Curso> agregarCurso(@RequestBody Curso curso) throws ExcepcionNegocio {
        Curso cursoNuevo = cursoUseCase.GuardarCurso(curso);
        return new ResponseEntity<>(cursoNuevo,HttpStatus.CREATED);
    }

    @PutMapping(path = "/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) throws ExcepcionNegocio {
        Optional<Usuario> usuarioNuevo = cursoUseCase.asignarUsuario(usuario,cursoId);
        if (usuarioNuevo.isPresent()){
            return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearusuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> usuarioNuevo = cursoUseCase.crearUsuario(usuario,cursoId);
        if (usuarioNuevo.isPresent()){
            return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo.get());
        }
        return ResponseEntity.notFound().build();
    }

}
