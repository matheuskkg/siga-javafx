package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.aulas.dto.AlunoNotasResponse;
import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TurmaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(TurmaModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            System.out.println("Falha ao salvar turma.");
        } finally {
            em.close();
        }
    }

    public void excluir(TurmaModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            var t = em.find(TurmaModel.class, request.getId());

            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao excluir turma.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /*public List<TurmaModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM TurmaModel ", TurmaModel.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public List<TurmaModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            List<TurmaModel> turmas = em.createQuery("FROM TurmaModel", TurmaModel.class)
                    .getResultList();

            for (TurmaModel turma : turmas) {
                List<AlunoNotasResponse> alunosComNotas = turma.getAlunos().stream()
                        .map(aluno -> new AlunoNotasResponse(
                                aluno,
                                aluno.getNotas().stream()
                                        .filter(nota -> nota.getTurma().equals(turma))
                                        .collect(Collectors.toList())
                        ))
                        .collect(Collectors.toList());

                turma.setAlunosComNotas(alunosComNotas);
            }

            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
