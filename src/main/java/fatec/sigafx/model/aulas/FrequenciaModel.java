package fatec.sigafx.model.aulas;

import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.*;

@Entity
@Table(name = "frequencias")
public class FrequenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoModel aluno;

    @Column(nullable = false)
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Aluno: " + aluno +
                ", Status: " + status;
    }
}
