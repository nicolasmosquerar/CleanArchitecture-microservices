package co.com.bancolombia.model.usuario.gateways;

import co.com.bancolombia.model.businessexception.BusinessException;
import co.com.bancolombia.model.usuario.Usuario;

import java.util.List;

public interface UsuarioRepository {

    Usuario addUsuario(Usuario usuario);

    Usuario findUsuarioById(Long id) throws BusinessException;

    void deleteUsuarioById(Long id);

    List<Usuario> listarUsuarios();


}
