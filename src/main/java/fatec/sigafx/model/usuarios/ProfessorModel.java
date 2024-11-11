package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.ProfessorDAO;
import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professores")
public class ProfessorModel extends UsuarioModel {

    @OneToMany(mappedBy = "professorResponsavel")
    private List<DisciplinaModel> disciplinasLecionadas;

    public ProfessorModel() {}

    public ProfessorModel(UsuarioCriarRequest request) {
        super(request);
    }

    public static ProfessorModel buscarProfessorPorEmail(String email) {
        ProfessorDAO professorDAO = new ProfessorDAO();

        return professorDAO.buscarPorEmail(email);
    }
}
