package fatec.sigafx.model.professor;

import fatec.sigafx.model.disciplina.DisciplinaModel;
import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professores")
public class ProfessorModel extends UsuarioModel {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "professores_disciplinas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<DisciplinaModel> disciplinasLecionadas;
}
