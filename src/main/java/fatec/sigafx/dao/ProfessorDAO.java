package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuarios.ProfessorModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ProfessorDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public List<ProfessorModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            List<ProfessorModel> res = em.createQuery("FROM ProfessorModel", ProfessorModel.class)
                    .getResultList();

            return res;
        } catch (Exception e) {
            return null;
        }
    }
}
