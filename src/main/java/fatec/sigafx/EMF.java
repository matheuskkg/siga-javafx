package fatec.sigafx;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("siga");

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("siga");
        }
        return emf;
    }

    public static void close() {
        emf.close();
    }
}
