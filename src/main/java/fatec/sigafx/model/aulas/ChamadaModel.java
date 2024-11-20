package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.ChamadaDAO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chamadas")
public class ChamadaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private TurmaModel turma;

    @OneToMany(mappedBy = "chamada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FrequenciaModel> frequencias;

    @Column(nullable = false)
    private LocalDate data;

    @Transient
    private static ChamadaDAO chamadaDAO = new ChamadaDAO();

    public ChamadaModel() {}

    public static void salvar(ChamadaModel request) {
        chamadaDAO.salvar(request);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TurmaModel getTurma() {
        return turma;
    }

    public void setTurma(TurmaModel turma) {
        this.turma = turma;
    }

    public List<FrequenciaModel> getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(List<FrequenciaModel> frequencias) {
        this.frequencias = frequencias;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
