package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.DisciplinaDAO;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "disciplinas")
public class DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true)
    private ProfessorModel professorResponsavel;

    @ManyToMany
    @JoinTable(
            name = "disciplina_alunos",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<AlunoModel> alunos;

    /**
     * Uma disciplina possui várias notas associadas a si,
     * caso uma disciplina seja excluída, todas as notas que estão associadas também serão deletadas.
     */
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaModel> notas;

    public DisciplinaModel() {}

    public DisciplinaModel(DisciplinaCriarRequest request) {
        this.nome = request.nome();
        this.professorResponsavel = request.professorResponsavel();
    }

    public static void criarDisciplina(DisciplinaCriarRequest request) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        disciplinaDAO.salvarDisciplina(new DisciplinaModel(request));
    }
}
