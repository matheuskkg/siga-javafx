package fatec.sigafx.dao;

import fatec.sigafx.EMF;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.aulas.NotaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class NotaDAO {
    private EntityManagerFactory emf = EMF.getEmf();

    public void salvar(Double valorNota, AlunoModel aluno) {
        NotaModel nota = new NotaModel(valorNota, aluno);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(nota);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Falha ao salvar nota");
        }
    }
}
