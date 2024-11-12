package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.TurmaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

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
}
