package co.com.bancolombia.usecase.curso;

import co.com.bancolombia.model.curso.Curso;
import co.com.bancolombia.model.curso.gateways.CursoRepository;
import co.com.bancolombia.model.excepcion.ExcepcionNegocio;
import co.com.bancolombia.model.usuario.Usuario;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CursoUseCase {
    private final CursoRepository cursoRepository;

    public List<Curso> listarCursos(){
        return cursoRepository.listarCursos();
    }

    public Curso buscarCurso(Long id) throws ExcepcionNegocio {
        return cursoRepository.buscarCurso(id);
    }

    public Curso GuardarCurso(Curso curso)  {
        return cursoRepository.agregarCurso(curso);
    }

    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) throws ExcepcionNegocio {
        return cursoRepository.asignarUsuario(usuario,cursoId);
    }

    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId){
        return cursoRepository.crearUsuario(usuario,cursoId);
    }
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId){
        return cursoRepository.eliminarUsuario(usuario,cursoId);
    }


}
