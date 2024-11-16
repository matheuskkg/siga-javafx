package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.AlunoDAO;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alunos")
public class AlunoModel extends UsuarioModel {

    @ManyToMany(mappedBy = "alunos")
    private List<TurmaModel> turmas;

    public AlunoModel() {}

    public AlunoModel(UsuarioCriarRequest request) {
        super(request);
    }

    @Transient
    private static AlunoDAO alunoDAO = new AlunoDAO();

    public static List<AlunoModel> buscarTodosAlunos() {
        return alunoDAO.buscarTodosAlunos();
    }

    public static List<AlunoModel> buscarAlunosForaDaTurma(Integer turmaId) {
        return alunoDAO.buscarAlunosForaDaTurma(turmaId);
    }


}
