package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.NotaDAO;
import fatec.sigafx.model.aulas.dto.NotaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.*;

/**
 * Selecioanar turma -> Acessar alunos da turma -> Acessar notas dos alunos
 */

@Entity
@Table(name = "notas")
public class NotaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double nota;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoModel aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private TurmaModel turma;

    @Transient
    private static NotaDAO notaDAO = new NotaDAO();

    public NotaModel() {}

    public NotaModel(NotaCriarRequest request) {
        this.nota = request.nota();
        this.aluno = request.aluno();
        this.turma = request.turma();
    }

    public static void criarNota(NotaCriarRequest request) {
        notaDAO.salvar(new NotaModel(request));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public TurmaModel getTurma() {
        return turma;
    }

    public void setTurma(TurmaModel turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "NotaModel{" +
                "id=" + id +
                ", nota=" + nota +
                ", aluno=" + aluno +
                ", turma=" + turma +
                '}';
    }
}
