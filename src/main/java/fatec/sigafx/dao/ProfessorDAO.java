package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ProfessorDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public List<ProfessorModel> buscarTodosProfessores() {
        try (EntityManager em = emf.createEntityManager()) {
            List<ProfessorModel> res = em.createQuery("FROM ProfessorModel", ProfessorModel.class)
                    .getResultList();

            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public ProfessorModel buscarPorEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM ProfessorModel WHERE email = :email", ProfessorModel.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
