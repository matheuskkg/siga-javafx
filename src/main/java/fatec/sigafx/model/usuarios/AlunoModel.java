package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.AlunoDAO;
import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

@Entity
@Table(name = "alunos")
public class AlunoModel extends UsuarioModel {

    @ManyToMany(mappedBy = "alunos")
    private List<TurmaModel> turmas;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NotaModel> notas;

    public AlunoModel() {}

    public AlunoModel(UsuarioCriarRequest request) {
        super(request);
    }

    @Transient
    private static AlunoDAO alunoDAO = new AlunoDAO();

    public static List<AlunoModel> buscarTodosAlunos() {
        return alunoDAO.buscarTodos();
    }

    public static Integer contarFaltasAlunoTurma(Integer alunoId, Integer turmaId) {
        return alunoDAO.contarFaltasAlunoTurma(alunoId, turmaId);
    }

    public List<TurmaModel> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaModel> turmas) {
        this.turmas = turmas;
    }

    public List<NotaModel> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaModel> notas) {
        this.notas = notas;
    }

    @Transient
    private BooleanProperty presente = new SimpleBooleanProperty(false);

    public BooleanProperty presenteProperty() {
        return presente;
    }

    public boolean isPresente() {
        return presente.get();
    }

    public void setPresente(boolean presente) {
        this.presente.set(presente);
    }

}
