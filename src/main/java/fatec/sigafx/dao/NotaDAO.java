package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.NotaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class NotaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void excluirPorTurma(Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM NotaModel n WHERE n.turma.id = :turmaId")
                    .setParameter("turmaId", turmaId)
                    .executeUpdate();

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

    // Salvar ou atualizar uma nota
    public void salvarNota(NotaModel nota) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            if (nota.getId() == null) {
                em.persist(nota); // Salva uma nova nota
            } else {
                em.merge(nota); // Atualiza uma nota existente
            }

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

    // Buscar uma nota específica pelo ID
    public NotaModel buscarNotaPorId(Integer id) {

        try (EntityManager em = emf.createEntityManager()) {
            return em.find(NotaModel.class, id);
        }
    }

    // Buscar todas as notas de um aluno em uma turma
    public List<NotaModel> buscarNotasPorAlunoETurma(Integer alunoId, Integer turmaId) {

        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT n FROM NotaModel n WHERE n.aluno.id = :alunoId AND n.turma.id = :turmaId",
                            NotaModel.class)
                    .setParameter("alunoId", alunoId)
                    .setParameter("turmaId", turmaId)
                    .getResultList();
        }
    }

}
