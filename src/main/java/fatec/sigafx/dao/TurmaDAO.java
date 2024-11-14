package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class TurmaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvarTurma(TurmaModel request) {
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

    public List<TurmaModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM TurmaModel ", TurmaModel.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
