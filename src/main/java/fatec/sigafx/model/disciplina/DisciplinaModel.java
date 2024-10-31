package fatec.sigafx.model.disciplina;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * TODO: Linkar Disciplinas com Notas
 */

@Entity
@Table(name = "disciplinas")
public class DisciplinaModel {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String codigo;

    private Integer cargaHoraria;

    public DisciplinaModel() {}

    public DisciplinaModel(String nome, String codigo, Integer cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }
}
