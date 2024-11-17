package fatec.sigafx.model.aulas.dto;

import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;

import java.util.List;

public record TurmaCriarRequest(
        String curso,
        DisciplinaModel disciplina,
        ProfessorModel professor,
        List<AlunoModel> alunos
) {}
