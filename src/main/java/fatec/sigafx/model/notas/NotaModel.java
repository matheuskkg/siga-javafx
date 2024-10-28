package fatec.sigafx.model.notas;

import fatec.sigafx.model.aluno.AlunoModel;
import jakarta.persistence.*;

@Entity
@Table(name = "notas")
public class NotaModel {

    @Id
    @GeneratedValue
    private Integer id;

    private Double nota;

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
