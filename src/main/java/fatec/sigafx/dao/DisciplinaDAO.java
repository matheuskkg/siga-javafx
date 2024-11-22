package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.DisciplinaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DisciplinaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(DisciplinaModel request) {
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

    public void excluir(DisciplinaModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            if (!em.contains(request)) {
                request = em.merge(request);
            }

            em.remove(request);
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

    public List<DisciplinaModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM DisciplinaModel ", DisciplinaModel.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DisciplinaModel buscarPorId(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM DisciplinaModel WHERE id = :id", DisciplinaModel.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
