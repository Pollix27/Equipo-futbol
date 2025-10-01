package org.futbol.equipofutbol.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "BaseFutbol";
    private static EntityManagerFactory emf;

    // Inicializar el EntityManagerFactory
    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            System.err.println("Error al crear EntityManagerFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // Obtener un EntityManager
    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory no está inicializado");
        }
        return emf.createEntityManager();
    }

    // Cerrar el EntityManagerFactory
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // Verificar si el EntityManagerFactory está abierto
    public static boolean isOpen() {
        return emf != null && emf.isOpen();
    }
}