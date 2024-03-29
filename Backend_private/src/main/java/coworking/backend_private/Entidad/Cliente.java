package coworking.backend_private.Entidad;

import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name="CLIENTES")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String nombreUsuario;
    private String password;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String email;
    private String codigoPostal;
    private String rol = "cliente";
    private boolean alta = true;

    private String hashPassword(String passwordNotEncrypted) {
        return BCrypt.hashpw(passwordNotEncrypted, BCrypt.gensalt());
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }
}
