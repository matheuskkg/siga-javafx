package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.ChamadaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ChamadaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(ChamadaModel request) {
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
}
