package fatec.sigafx.util;

import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.AlunoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AulasUtil {

    public AlunoModel filtrarNotasAlunos(AlunoModel aluno, Integer turmaId) {
        AlunoModel a = new AlunoModel();
        a.setId(aluno.getId());
        a.setNome(aluno.getNome());
        a.setEmail(aluno.getEmail());
        a.setSenha(aluno.getSenha());

        a.setNotas(aluno.getNotas().stream()
                .filter(nota -> nota.getTurma().getId().equals(turmaId))
                .collect(Collectors.toList()));

        return a;
    }

    public List<TurmaModel> filtrarNotasAlunosTurmas(List<TurmaModel> turmas) {
        for (TurmaModel turma : turmas) {
            List<AlunoModel> alunos = turma.getAlunos();
            List<AlunoModel> alunosRes = new ArrayList<>();
            for (AlunoModel aluno : alunos) {
                alunosRes.add(filtrarNotasAlunos(aluno, turma.getId()));
            }

            turma.setAlunosView(alunosRes);
        }

        return turmas;
    }
}
