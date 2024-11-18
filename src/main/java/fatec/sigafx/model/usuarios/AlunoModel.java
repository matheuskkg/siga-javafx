package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.AlunoDAO;
import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alunos")
public class AlunoModel extends UsuarioModel {

    @ManyToMany(mappedBy = "alunos")
    private List<TurmaModel> turmas;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaModel> notas;

    public AlunoModel() {}

    public AlunoModel(UsuarioCriarRequest request) {
        super(request);
    }

    @Transient
    private static AlunoDAO alunoDAO = new AlunoDAO();

    public static List<AlunoModel> buscarTodosAlunos() {
        return alunoDAO.buscarTodos();
    }

    public static List<AlunoModel> buscarAlunosNaTurma(Integer turmaId) {
        return alunoDAO.buscarAlunosNaTurma(turmaId);
    }

    public List<NotaModel> getNotas() {
        return notas;
    }

    public Double getNotaP1() {
        return notas != null && notas.size() > 0 ? notas.get(0).getNota() : null;
    }

    public Double getNotaP2() {
        return notas != null && notas.size() > 1 ? notas.get(1).getNota() : null;
    }

    public Double getNotaP3() {
        return notas != null && notas.size() > 2 ? notas.get(2).getNota() : null;
    }


    //TODO: Aluno precisa de Faltas, P1, P2, P3 e alguma função para usar no Realizar Chamada

}
