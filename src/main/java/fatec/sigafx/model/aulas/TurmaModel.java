package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.TurmaDAO;
import fatec.sigafx.model.aulas.dto.TurmaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.util.AulasUtil;
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
    @JoinColumn(name = "disciplina_id")
    private DisciplinaModel disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "turma_alunos",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<AlunoModel> alunos;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaModel> notas;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChamadaModel> chamadas;

    @Transient
    private List<AlunoModel> alunosView;

    @Transient
    private static TurmaDAO turmaDAO = new TurmaDAO();

    @Transient
    private static AulasUtil aulasUtil = new AulasUtil();

    @Transient
    private Integer faltas;

    public TurmaModel() {}

    public TurmaModel(TurmaCriarRequest request) {
        this.curso = request.curso();
        this.disciplina = request.disciplina();
        this.professor = request.professor();
        this.alunos = request.alunos();
    }

    public static void criar(TurmaCriarRequest request) {
        turmaDAO.salvar(new TurmaModel(request));
    }

    public static void atualizar(TurmaCriarRequest request, TurmaModel turmaAntiga) {
        TurmaModel t = new TurmaModel(request);
        t.setId(turmaAntiga.getId());
        t.setNotas(turmaAntiga.getNotas());
        t.setChamadas(turmaAntiga.getChamadas());

        turmaDAO.salvar(t);
    }

    public static void excluir(TurmaModel request) {
        turmaDAO.excluir(request);
    }

    public static List<TurmaModel> buscarTodos() {
        return aulasUtil.filtrarNotasAlunosTurmas(turmaDAO.buscarTodos());
    }

    public static List<TurmaModel> buscarPorProfessor(ProfessorModel professor) {
        return aulasUtil.filtrarNotasAlunosTurmas(turmaDAO.buscarPorProfessor(professor));
    }

    public static List<TurmaModel> buscarPorAluno(AlunoModel aluno) {
        return aulasUtil.filtrarNotasAlunosTurmas(turmaDAO.buscarPorAluno(aluno));
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

    public List<NotaModel> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaModel> notas) {
        this.notas = notas;
    }

    public List<ChamadaModel> getChamadas() {
        return chamadas;
    }

    public void setChamadas(List<ChamadaModel> chamadas) {
        this.chamadas = chamadas;
    }

    public List<AlunoModel> getAlunosView() {
        return alunosView;
    }

    public void setAlunosView(List<AlunoModel> alunosView) {
        this.alunosView = alunosView;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return "Curso: " + curso +
                ", Disciplina: " + disciplina.getNome() +
                ", Professor: " + professor.getEmail();
    }
}
