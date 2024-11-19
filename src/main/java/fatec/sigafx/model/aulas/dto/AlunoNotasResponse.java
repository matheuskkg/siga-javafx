package fatec.sigafx.model.aulas.dto;

import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.usuarios.AlunoModel;

import java.util.List;

public record AlunoNotasResponse(
        AlunoModel aluno,
        List<NotaModel> notas
) {}
