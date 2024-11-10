package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aluno.AlunoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AlunoDAO {
    private EntityManagerFactory emf = EMF.getEmf();

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

    public AlunoModel buscarAlunoPorNome(String nome) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM AlunoModel WHERE nome = :nome", AlunoModel.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
