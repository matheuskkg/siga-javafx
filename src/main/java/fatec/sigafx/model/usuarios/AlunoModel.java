package fatec.sigafx.model.usuarios;

import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
public class AlunoModel extends UsuarioModel {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "aluno")
    private List<NotaModel> notas = new ArrayList<>();

    public AlunoModel() {}

    public AlunoModel(UsuarioCriarRequest request) {
        super(request);
    }

    @Override
    public String toString() {
        return "AlunoModel{" +
                "notas=" + notas.toString() +
                "} " + super.toString();
    }
}
