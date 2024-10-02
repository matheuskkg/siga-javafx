package fatec.sigafx.model.aluno;

import fatec.sigafx.model.disciplina.DisciplinaModel;
import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alunos")
public class AlunoModel extends UsuarioModel {
    private String matricula;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "aluno_disciplinas",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<DisciplinaModel> disciplinasCursadas;
}
