package fatec.sigafx.dao;

import fatec.sigafx.model.aluno.AlunoModel;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AlunoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("siga");

    public void salvarAluno(UsuarioCriarRequest request) {
        EntityManager em = emf.createEntityManager();

        try {
            AlunoModel aluno = new AlunoModel(request);

            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar aluno.");
        } finally {
            em.close();
        }
    }

    public void buscarTodosAlunos() {
        try (EntityManager em = emf.createEntityManager()) {
            List<AlunoModel> res = em.createQuery("FROM AlunoModel", AlunoModel.class)
                    .getResultList();

            //return res;

            //está aqui apenas p debug, remover dps
            for (AlunoModel a : res) {
                System.out.println(a.toString());
            }
        } catch (Exception e) {
            System.out.println("Falha ao buscar todos os alunos.");
        }
    }
}
