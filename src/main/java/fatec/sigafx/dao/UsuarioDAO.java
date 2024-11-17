package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuarios.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UsuarioDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(UsuarioModel request) {
        EntityManager em = emf.createEntityManager();

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

    public void excluir(UsuarioModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            if (!em.contains(request)) {
                request = em.merge(request);
            }

            em.remove(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao excluir usuário.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Caso deseje retornar especificamente alunos, admins ou professores, utilizar seus respectivos DAOs.
     */
    public List<UsuarioModel> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            List<UsuarioModel> res = em.createQuery("FROM UsuarioModel", UsuarioModel.class)
                    .getResultList();

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            return null;
        }
    }
}
