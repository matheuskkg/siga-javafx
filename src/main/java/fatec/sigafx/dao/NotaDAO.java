package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class NotaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(NotaModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar nota");
        }
    }

    //debug apenas
    public void buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            List<NotaModel> res = em.createQuery("FROM NotaModel", NotaModel.class)
                    .getResultList();

            for (NotaModel n : res) {
                System.out.println(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
