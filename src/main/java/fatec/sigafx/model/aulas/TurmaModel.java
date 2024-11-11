package fatec.sigafx.model.aulas;

import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "turmas")
public class TurmaModel {

    @Id
    private Integer id;

    //fazer hard coded msm fds (final de semana)
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
}
