package fatec.sigafx.model.aluno;

import fatec.sigafx.model.notas.NotaModel;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
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

    public List<NotaModel> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return "AlunoModel{" +
                "notas=" + notas.toString() +
                "} " + super.toString();
    }
}
