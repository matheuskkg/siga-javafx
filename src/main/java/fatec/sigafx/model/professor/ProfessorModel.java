package fatec.sigafx.model.professor;

import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "professores")
public class ProfessorModel extends UsuarioModel {
    public ProfessorModel() {}

    public ProfessorModel(UsuarioCriarRequest request) {
        super(request);
    }
}
