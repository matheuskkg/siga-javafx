package fatec.sigafx.model.aulas;

import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.*;

@Entity
@Table(name = "notas")
public class NotaModel {

    //TODO: estabelecer como deve ser o relacionamento da nota com as outras entidades

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double nota;

    public NotaModel() {}

    public NotaModel(Double nota, AlunoModel aluno) {
        this.nota = nota;
        //this.aluno = aluno;
    }
}
