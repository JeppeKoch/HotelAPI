package dk.lyngby;

import dk.lyngby.config.AppConfig;
import dk.lyngby.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;


public class Main {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("hotel");

    public static void main(String[] args) {
        AppConfig.startServer(7777, emf);

    }

}
