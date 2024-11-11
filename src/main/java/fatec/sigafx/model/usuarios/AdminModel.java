package fatec.sigafx.model.usuarios;

import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminModel extends UsuarioModel {

    public AdminModel() {}

    public AdminModel(UsuarioCriarRequest request) {
        super(request);
    }
}
