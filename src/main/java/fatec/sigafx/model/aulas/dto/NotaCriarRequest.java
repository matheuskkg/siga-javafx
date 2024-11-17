package fatec.sigafx.model.aulas.dto;

import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.AlunoModel;

public record NotaCriarRequest(
        Double nota,
        AlunoModel aluno,
        TurmaModel turma
) {}
