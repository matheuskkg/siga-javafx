package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class NotaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void excluirPorTurma(Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM NotaModel n WHERE n.turma.id = :turmaId")
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

}
