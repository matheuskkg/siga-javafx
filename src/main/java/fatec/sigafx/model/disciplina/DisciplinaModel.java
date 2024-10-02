package fatec.sigafx.model.disciplina;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class DisciplinaModel {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String codigo;

    private Integer cargaHoraria;
}
