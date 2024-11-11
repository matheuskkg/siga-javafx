package fatec.sigafx.model.aulas;

import fatec.sigafx.dao.DisciplinaDAO;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "disciplinas")
public class DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private Integer cargaHoraria;

    @Transient
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    public DisciplinaModel() {}

    public DisciplinaModel(DisciplinaCriarRequest request) {
        this.nome = request.nome();
    }

    public static void criarDisciplina(DisciplinaCriarRequest request) {
        disciplinaDAO.salvarDisciplina(new DisciplinaModel(request));
    }
}
