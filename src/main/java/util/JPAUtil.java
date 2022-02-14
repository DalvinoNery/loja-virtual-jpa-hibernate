package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}