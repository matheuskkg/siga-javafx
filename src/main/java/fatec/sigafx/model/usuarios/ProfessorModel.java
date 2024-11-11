package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.ProfessorDAO;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professores")
public class ProfessorModel extends UsuarioModel {

    @OneToMany(mappedBy = "professor")
    private List<TurmaModel> turmas;

    public ProfessorModel() {}

    public ProfessorModel(UsuarioCriarRequest request) {
        super(request);
    }

    @Transient
    private static ProfessorDAO professorDAO = new ProfessorDAO();

    public static ProfessorModel buscarProfessorPorEmail(String email) {
        return professorDAO.buscarPorEmail(email);
    }
}
