package co.com.bancolombia.jpa;

import co.com.bancolombia.model.usuario.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuarios",url = "http://localhost:8001/api/")
public interface UsuarioClientRest {

    @GetMapping("buscarUsuario/{id}")
    Usuario usuarioPorId(@PathVariable(value = "id") Long id);

    @PostMapping()
    Usuario agregarUser(@RequestBody Usuario usuario);
}

