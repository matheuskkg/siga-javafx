package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.NotaDAO;
import fatec.sigafx.model.aulas.dto.NotaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "notas")
public class NotaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double nota;

    @Enumerated(EnumType.STRING)
    private TipoNota tipo;

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

    public static void salvar(NotaModel nota){
        notaDAO.salvar(nota);
    }

    public static void excluirPorTurma(Integer turmaId) {
        notaDAO.excluirPorTurma(turmaId);
    }

    public static void excluirPorAluno(Integer alunoId) {
        notaDAO.excluirPorAluno(alunoId);
    }

    public static List<NotaModel> buscarPorAlunoTurma(Integer alunoId, Integer turmaId){
        return notaDAO.buscarNotasPorAlunoETurma(alunoId, turmaId);
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

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
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
        return "Nota: " + nota +
                ", Curso: " + turma.getCurso() +
                ", Disciplina: " + turma.getDisciplina().getNome() +
                ", Aluno: " + aluno.getEmail();
    }
}
