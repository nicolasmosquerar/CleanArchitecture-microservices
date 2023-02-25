package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.model.curso.Curso;
import co.com.bancolombia.model.curso.gateways.CursoRepository;
import co.com.bancolombia.model.errormessage.CustomMessage;
import co.com.bancolombia.model.excepcion.ExcepcionNegocio;
import co.com.bancolombia.model.usuario.CursoUsuario;
import co.com.bancolombia.model.usuario.Usuario;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Log4j2
@Repository
public class JPARepositoryAdapter extends AdapterOperations<Curso, CursoEntity, Long, JPARepository>
        implements CursoRepository {
    @Autowired
    private UsuarioClientRest client;

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Curso.CursoBuilder.class).build());

    }

    @Override
    public List<Curso> listarCursos() {
        log.info(CustomMessage.builder()
                .action("listar usuarios")
                .detail("ejecucion correcta")
                .method("GET")
                .status("200 OK")
                .build());

        return super.findAll();


    }

    @Override
    public Curso buscarCurso(Long id) throws ExcepcionNegocio {
        Curso curso = super.findById(id);
        if (curso != null) {
            log.info(CustomMessage.builder()
                    .action(String.format("se ha encontrado el curso: %s", curso))
                    .detail("ejecucion correcta")
                    .method("GET")
                    .status("200 OK")
                    .build());
            return super.findById(id);
        } else {
            log.error(CustomMessage.builder()
                    .action("usuario no encontrado")
                    .detail("no se encuentra usuario con el id " +
                            "suministrado")
                    .method("GET")
                    .status("404")
                    .build());

            throw new ExcepcionNegocio("usuario no encontrado", "no se encuentra usuario con el id " +
                    "suministrado");

        }

    }

    @Override
    public Curso agregarCurso(Curso cursoNuevo) {
        Curso curso;
        try {
            log.info(CustomMessage.builder()
                    .action(String.format("se ha agregado el curso: %s", cursoNuevo))
                    .detail("ejecucion correcta")
                    .method("POST")
                    .status("200 OK")
                    .build());
            curso = super.save(cursoNuevo);
        } catch (DataIntegrityViolationException ex) {
            log.error(CustomMessage.builder()
                    .action("campo repetido")
                    .detail("revisar campos repetidos:" + ex.getMessage())
                    .method("POST")
                    .status("500")
                    .build());
            throw new DataIntegrityViolationException("Algunos campos deben ser unicos!");
        }

        return curso;
    }

    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long id) throws ExcepcionNegocio {
        Curso curso = super.findById(id);
        if (curso != null) {
            Usuario usuarioMvsc = client.usuarioPorId(usuario.getId());

            CursoUsuarioEntity cursoUsuario = CursoUsuarioEntity.builder()
                    .usuarioId(usuarioMvsc.getId())
                    .build();
            CursoUsuario cursoUsuarioModel = CursoUsuario.builder()
                    .usuarioId(cursoUsuario.getUsuarioId())
                    .id(cursoUsuario.getId())
                    .build();
            curso.agregarCursoUsuario(cursoUsuarioModel);
            super.save(curso);
            return Optional.of(usuarioMvsc);

        } else {
            throw new ExcepcionNegocio("no se encontro curso con el id" + String.valueOf(id),
                    "curso no encontrado");
        }
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Curso curso = super.findById(cursoId);
        Usuario usuarioMsvc = client.agregarUser(usuario);
        CursoUsuarioEntity cursoUsuario = CursoUsuarioEntity.builder()
                .usuarioId(usuarioMsvc.getId())
                .build();
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(curso.getId());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.agregarCursoUsuario(cursoUsuario);
        super.save(curso);
        return Optional.of(usuarioMsvc);
    }

    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Curso curso = super.findById(cursoId);
        Usuario usuarioMvsc = client.usuarioPorId(usuario.getId());
        CursoUsuarioEntity cursoUsuario = CursoUsuarioEntity.builder().build();
        cursoUsuario.setUsuarioId(usuarioMvsc.getId());
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(curso.getId());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.eliminarCursoUsuario(cursoUsuario);
        super.save(curso);
        return Optional.of(usuarioMvsc);
    }


}
