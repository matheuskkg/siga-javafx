package fatec.sigafx.model.aulas.dto;

import fatec.sigafx.model.usuarios.ProfessorModel;

public record DisciplinaCriarRequest(
        String nome,
        ProfessorModel professorResponsavel
) {}
