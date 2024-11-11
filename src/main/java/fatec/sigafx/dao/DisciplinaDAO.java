package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DisciplinaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvarDisciplina(DisciplinaModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar disciplina");
        }
    }
}
