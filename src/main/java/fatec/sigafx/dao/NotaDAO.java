package fatec.sigafx.dao;

import fatec.sigafx.model.aluno.AlunoModel;
import fatec.sigafx.model.notas.NotaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NotaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("siga");

    public void salvarNota(Double valorNota, AlunoModel aluno) {
        NotaModel nota = new NotaModel(valorNota, aluno);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(nota);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar nota");
        }
    }
}
