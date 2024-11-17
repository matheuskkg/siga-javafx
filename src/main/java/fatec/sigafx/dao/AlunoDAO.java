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
            return null;
        }
    }

    public AlunoModel buscarPorNome(String nome) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM AlunoModel WHERE nome = :nome", AlunoModel.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<AlunoModel> buscarAlunosNaTurma(Integer turmaId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT a FROM AlunoModel a WHERE a.id IN (" +
                                    "SELECT at.id FROM TurmaModel t JOIN t.alunos at WHERE t.id = :turmaId" +
                                    ")", AlunoModel.class)
                    .setParameter("turmaId", turmaId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
