package co.com.bancolombia.usecase.gestionusuario;

import co.com.bancolombia.model.businessexception.BusinessException;
import co.com.bancolombia.model.usuario.Usuario;
import co.com.bancolombia.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GestionUsuarioUseCase {

    private final  UsuarioRepository usuarioRepository;

    public Usuario creacionUsuarioBd(Usuario usuario){
        return usuarioRepository.addUsuario(usuario);
    }

    public Usuario encontrarUsuarioBd(Long id) throws BusinessException {
        return usuarioRepository.findUsuarioById(id);
    }

    public void eliminarUsuarioBd(Long id){
        usuarioRepository.deleteUsuarioById(id);
    }

    public List<Usuario> listarUsuarios(){

        return usuarioRepository.listarUsuarios();
    }
}
