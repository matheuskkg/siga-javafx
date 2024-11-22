package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.FrequenciaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class FrequenciaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(FrequenciaModel request) {
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
        }
    }

    public void excluirPorTurma(Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM FrequenciaModel f WHERE f.chamada.turma.id = :turmaId")
                    .setParameter("turmaId", turmaId)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void excluirPorAluno(Integer alunoId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM FrequenciaModel f WHERE f.aluno.id = :alunoId")
                    .setParameter("alunoId", alunoId)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Integer buscarQuantidade(Integer alunoId, Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT COUNT(f) FROM FrequenciaModel f WHERE f.aluno.id =:alunoId AND f.chamada.turma.id =:turmaId ", Long.class)
                    .setParameter("alunoId", alunoId)
                    .setParameter("turmaId", turmaId)
                    .getSingleResult()
                    .intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<FrequenciaModel> listagemAulas(Integer alunoId, Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("FROM FrequenciaModel f WHERE f.aluno.id =:alunoId AND f.chamada.turma.id =:turmaId ", FrequenciaModel.class)
                    .setParameter("alunoId", alunoId)
                    .setParameter("turmaId", turmaId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
