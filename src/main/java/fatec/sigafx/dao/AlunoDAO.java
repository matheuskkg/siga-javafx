package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuarios.AlunoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AlunoDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public List<AlunoModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            List<AlunoModel> res = em.createQuery("FROM AlunoModel", AlunoModel.class)
                    .getResultList();

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer contarFaltasAlunoTurma(Integer alunoId, Integer turmaId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT count(f) FROM FrequenciaModel f " +
                                    "WHERE f.aluno.id = :alunoId " +
                                    "AND f.chamada.turma.id = :turmaId " +
                                    "AND f.status = false", Long.class)
                    .setParameter("alunoId", alunoId)
                    .setParameter("turmaId", turmaId)
                    .getSingleResult()
                    .intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
