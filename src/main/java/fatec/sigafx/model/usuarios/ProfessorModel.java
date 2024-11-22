package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.ProfessorDAO;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professores")
public class ProfessorModel extends UsuarioModel {

    //TODO: estudar a utilização deste atributo para diminuir o número de querys realizadas
    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private List<TurmaModel> turmas;

    public ProfessorModel() {}

    public ProfessorModel(UsuarioCriarRequest request) {
        super(request);
    }

    @Transient
    private static ProfessorDAO professorDAO = new ProfessorDAO();

    public static List<ProfessorModel> buscarTodosProfessores() {
        return professorDAO.buscarTodos();
    }

    public List<TurmaModel> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaModel> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() +
                ", E-Mail: " + getEmail();
    }
}
