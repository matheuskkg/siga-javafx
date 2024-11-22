package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.FrequenciaDAO;
import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "chamada_id", nullable = false)
    private ChamadaModel chamada;

    private static FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public static void salvar(FrequenciaModel request){
        frequenciaDAO.salvar(request);
    }

    public static Integer buscarQuantidade(int alunoId, int turmaId){
        return frequenciaDAO.buscarQuantidade(alunoId, turmaId);
    }

    public static List<FrequenciaModel> listagemAulas(int alunoId, int turmaId){
        return frequenciaDAO.listagemAulas(alunoId, turmaId);
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ChamadaModel getChamada() {
        return chamada;
    }

    public void setChamada(ChamadaModel chamada) {
        this.chamada = chamada;
    }

    @Override
    public String toString() {
        return "Aluno: " + aluno +
                ", Status: " + status;
    }
}
