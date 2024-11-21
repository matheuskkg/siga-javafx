package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.aulas.ChamadaModel;
import fatec.sigafx.model.aulas.FrequenciaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class FrequenciaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public Integer buscarQuantidade(Integer alunoId, Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT COUNT(f) FROM FrequenciaModel f WHERE f.aluno.id =:alunoId AND f.chamada.turma.id =:turmaId ", Long.class)
                    .setParameter("alunoId", alunoId)
                    .setParameter("turmaId", turmaId)
                    .getSingleResult()
                    .intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public List<FrequenciaModel> listagemAulas(Integer alunoId, Integer turmaId) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("FROM FrequenciaModel f WHERE f.aluno.id =:alunoId AND f.chamada.turma.id =:turmaId ", FrequenciaModel.class)
                    .setParameter("alunoId", alunoId)
                    .setParameter("turmaId", turmaId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public void salvar(FrequenciaModel request) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar Frequencia");
        }
    }
}
