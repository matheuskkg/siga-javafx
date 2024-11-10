package fatec.sigafx;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("siga");

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void close() {
        emf.close();
    }
}
