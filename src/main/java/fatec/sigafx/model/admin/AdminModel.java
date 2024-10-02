package fatec.sigafx.model.admin;

import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminModel extends UsuarioModel {



}
