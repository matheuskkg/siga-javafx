package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuario.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UsuarioDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvarUsuario(UsuarioModel request) {
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

    public ObservableList<UsuarioModel> buscarTodos() {
        ObservableList<UsuarioModel> res = FXCollections.observableArrayList();

        try (EntityManager em = emf.createEntityManager()) {
            List<UsuarioModel> l = em.createQuery("FROM UsuarioModel", UsuarioModel.class)
                    .getResultList();

            res.addAll(l);
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
            e.printStackTrace();
            return null;
        }
    }
}
