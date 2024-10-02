package fatec.sigafx.dao;

import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsuarioDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("siga");

    public void salvarUsuario(UsuarioModel usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
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

    public UsuarioModel buscarPorNome(String nome) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM UsuarioModel WHERE nome = :nome", UsuarioModel.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
