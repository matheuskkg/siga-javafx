package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

public class UsuarioDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvarUsuario(UsuarioModel request) {
        EntityManager em = emf.createEntityManager();

        UsuarioModel u = buscarPorEmail(request.getEmail());
        if (u != null) {
            request.setId(u.getId());
        }

        try {
            em.getTransaction().begin();
            em.merge(request);
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

    public UsuarioModel buscarPorId(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM UsuarioModel WHERE id = :id", UsuarioModel.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public UsuarioModel buscarPorEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM UsuarioModel WHERE email = :email", UsuarioModel.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário com email " + email + " não encontrado.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        } catch (NoResultException e) {
            System.out.println("Usuário com nome " + nome + " não encontrado.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
