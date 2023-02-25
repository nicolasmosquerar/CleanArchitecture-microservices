package co.com.bancolombia.jpa;

import co.com.bancolombia.model.usuario.CursoUsuario;
import co.com.bancolombia.model.usuario.Usuario;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cursos")
public class CursoEntity {


    public CursoEntity(){
        cursoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;


    @JoinColumn(name = "curso_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoUsuarioEntity> cursoUsuarios;

    @Transient
    private List<Usuario> usuarios;


    public void agregarCursoUsuario(CursoUsuarioEntity cursoUsuario) {
        cursoUsuarios.add(cursoUsuario);

        }


    public void eliminarCursoUsuario(CursoUsuarioEntity cursoUsuario) {
        cursoUsuarios.remove(cursoUsuario);
    }



}
