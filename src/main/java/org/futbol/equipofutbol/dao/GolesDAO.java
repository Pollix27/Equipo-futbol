package org.futbol.equipofutbol.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.futbol.equipofutbol.entidades.Goles;

import java.util.List;

public class GolesDAO extends GenericDAO<Goles, Long> {

    public GolesDAO() {
        super(Goles.class);
    }

    // Buscar goles por partido
    public List<Goles> buscarPorPartido(Long idPartido) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT g FROM Goles g WHERE g.partido.idPartido = :idPartido " +
                    "ORDER BY g.minuto";
            TypedQuery<Goles> query = em.createQuery(jpql, Goles.class);
            query.setParameter("idPartido", idPartido);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar goles por jugador
    public List<Goles> buscarPorJugador(Long idJugador) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT g FROM Goles g WHERE g.jugador.idJugador = :idJugador " +
                    "ORDER BY g.partido.fechaPartido DESC";
            TypedQuery<Goles> query = em.createQuery(jpql, Goles.class);
            query.setParameter("idJugador", idJugador);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Contar goles de un jugador
    public long contarGolesPorJugador(Long idJugador) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT COUNT(g) FROM Goles g WHERE g.jugador.idJugador = :idJugador";
            return em.createQuery(jpql, Long.class)
                    .setParameter("idJugador", idJugador)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Obtener máximos goleadores (top N)
    public List<Object[]> obtenerMaximosGoleadores(int limite) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT g.jugador, COUNT(g) as totalGoles " +
                    "FROM Goles g " +
                    "GROUP BY g.jugador " +
                    "ORDER BY totalGoles DESC";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setMaxResults(limite);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Listar goles con detalles completos
    public List<Goles> listarConDetalles() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT g FROM Goles g " +
                    "LEFT JOIN FETCH g.jugador " +
                    "LEFT JOIN FETCH g.partido " +
                    "ORDER BY g.partido.fechaPartido DESC, g.minuto";
            TypedQuery<Goles> query = em.createQuery(jpql, Goles.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}