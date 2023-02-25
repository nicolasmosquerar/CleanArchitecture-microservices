package co.com.bancolombia.jpa;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="usuario_id",unique = true)
    private Long usuarioId;


    public boolean equals(Object obj){
        if(this == obj){
            return  true;
        }
        if (!(obj instanceof CursoUsuarioEntity)){
            return false;
        }
        CursoUsuarioEntity o = (CursoUsuarioEntity) obj;
        return this.usuarioId !=null && this.usuarioId.equals(o.usuarioId);
    }


}
