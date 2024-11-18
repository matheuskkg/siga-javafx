package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.TurmaDAO;
import fatec.sigafx.model.aulas.dto.TurmaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "turmas")
public class TurmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String curso;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaModel disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorModel professor;

    @ManyToMany
    @JoinTable(
            name = "turma_alunos",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<AlunoModel> alunos;

    // Utilizar para editar uma chamada
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChamadaModel> chamadas;

    @Transient
    private static TurmaDAO turmaDAO = new TurmaDAO();

    public TurmaModel() {}

    public TurmaModel(TurmaCriarRequest request) {
        this.curso = request.curso();
        this.disciplina = request.disciplina();
        this.professor = request.professor();
        this.alunos = request.alunos();
    }

    public static void criarTurma(TurmaCriarRequest request) {
        turmaDAO.salvar(new TurmaModel(request));
    }

    public static void atualizarTurma(TurmaCriarRequest request, Integer id) {
        TurmaModel t = new TurmaModel(request);
        t.setId(id);

        turmaDAO.salvar(t);
    }

    public static void excluirTurma(TurmaModel request) {
        turmaDAO.excluir(request);
    }

    public static List<TurmaModel> buscarTodasTurmas() {
        return turmaDAO.buscarTodos();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public DisciplinaModel getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaModel disciplina) {
        this.disciplina = disciplina;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public List<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoModel> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Curso: " + curso +
                ", Disciplina: " + disciplina.getNome() +
                ", Professor: " + professor.getEmail();
    }
}
