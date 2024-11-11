package fatec.sigafx.model.aulas;

import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.*;

@Entity
@Table(name = "notas")
public class NotaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double nota;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplinaModel disciplina;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoModel aluno;

    public NotaModel() {}

    public NotaModel(Double nota, AlunoModel aluno) {
        this.nota = nota;
        this.aluno = aluno;
    }

    public String toString() {
        return "NotaModel{" +
                "id=" + id +
                ", nota=" + nota +
                '}';
    }
}
