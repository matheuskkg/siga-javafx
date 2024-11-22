package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.DisciplinaDAO;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "disciplinas")
public class DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private Integer cargaHoraria;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurmaModel> turmas;

    @Transient
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    public DisciplinaModel() {}

    public DisciplinaModel(DisciplinaCriarRequest request) {
        this.nome = request.nome();
        this.cargaHoraria = request.cargaHoraria();
    }

    public static void criarDisciplina(DisciplinaCriarRequest request) {
        disciplinaDAO.salvar(new DisciplinaModel(request));
    }

    public static void atualizarDisciplina(DisciplinaCriarRequest request, Integer id) {
        DisciplinaModel d = disciplinaDAO.buscarPorId(id);
        d.setNome(request.nome());
        d.setCargaHoraria(request.cargaHoraria());

        disciplinaDAO.salvar(d);
    }

    public static void excluirDisciplina(DisciplinaModel request) {
        disciplinaDAO.excluir(request);
    }

    public static List<DisciplinaModel> buscarTodasDisciplinas() {
        return disciplinaDAO.buscarTodos();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<TurmaModel> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaModel> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                ", Carga Horária: " + cargaHoraria;
    }
}
