package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.helper.Usuarios;
import co.com.bancolombia.model.businessexception.BusinessException;
import co.com.bancolombia.model.usuario.Usuario;
import co.com.bancolombia.model.usuario.gateways.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class JPARepositoryAdapter extends AdapterOperations<Usuario, Usuarios, Long, JPARepository>
implements UsuarioRepository
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
         super(repository, mapper, x -> mapper.mapBuilder(x,Usuario.UsuarioBuilder.class).build());

    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        return super.save(usuario);
    }

    @Override
    public Usuario findUsuarioById(Long id) throws BusinessException {
        if (super.findById(id) != null){
            return super.findById(id);

        } else {
            throw new BusinessException("no se encuentra usuario con ese id", "description");

        }
    }

    @Override
    public void deleteUsuarioById(Long id){
        super.delete(id);

    }

    @Override
    public List<Usuario> listarUsuarios() {
        return super.findAll();
    }


}
