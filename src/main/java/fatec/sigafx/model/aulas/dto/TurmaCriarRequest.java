package fatec.sigafx.model.aulas.dto;

import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.usuarios.ProfessorModel;

public record TurmaCriarRequest(
        String curso,
        DisciplinaModel disciplina,
        ProfessorModel professor
) {}
