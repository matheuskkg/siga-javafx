package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UsuarioDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvarUsuario(UsuarioModel request) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar usuário.");
        } finally {
            em.close();
        }
    }

    /**
     * Utilizado para realizar o login.<br>
     * Caso deseje retornar especificamente alunos, admins ou professores, utilizar seus respectivos DAOs.
     */
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
