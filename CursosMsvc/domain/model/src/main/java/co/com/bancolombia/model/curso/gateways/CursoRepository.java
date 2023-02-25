package co.com.bancolombia.model.curso.gateways;

import co.com.bancolombia.model.curso.Curso;
import co.com.bancolombia.model.excepcion.ExcepcionNegocio;
import co.com.bancolombia.model.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {
    List<Curso> listarCursos();
    Curso buscarCurso(Long id) throws ExcepcionNegocio;
    Curso agregarCurso(Curso cursoNuevo) ;

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) throws ExcepcionNegocio;
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
}
