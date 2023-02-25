package co.com.bancolombia.model.usuario;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CursoUsuario {
    private Long id;
    private Long usuarioId;
    public boolean equals(Object obj){
        if(this == obj){
            return  true;
        }
        if (!(obj instanceof CursoUsuario)){
            return false;
        }
        CursoUsuario o = (CursoUsuario) obj;
        return this.usuarioId !=null && this.usuarioId.equals(o.usuarioId);
    }
}
