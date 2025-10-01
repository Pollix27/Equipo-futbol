package org.futbol.equipofutbol.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.futbol.equipofutbol.entidades.Partidos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PartidosDAO extends GenericDAO<Partidos, Long> {

    public PartidosDAO() {
        super(Partidos.class);
    }

    // Buscar partidos por equipo (como local o visitante)
    public List<Partidos> buscarPorEquipo(Long idEquipo) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Partidos p WHERE p.equipoLocal.idEquipo = :idEquipo " +
                    "OR p.equipoVisitante.idEquipo = :idEquipo " +
                    "ORDER BY p.fechaPartido DESC";
            TypedQuery<Partidos> query = em.createQuery(jpql, Partidos.class);
            query.setParameter("idEquipo", idEquipo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar partidos por fecha
    public List<Partidos> buscarPorFecha(LocalDate fecha) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Partidos p WHERE p.fechaPartido = :fecha " +
                    "ORDER BY p.idPartido";
            TypedQuery<Partidos> query = em.createQuery(jpql, Partidos.class);
            query.setParameter("fecha", fecha);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar partidos en un rango de fechas
    public List<Partidos> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Partidos p WHERE p.fechaPartido BETWEEN :inicio AND :fin " +
                    "ORDER BY p.fechaPartido DESC";
            TypedQuery<Partidos> query = em.createQuery(jpql, Partidos.class);
            query.setParameter("inicio", fechaInicio);
            query.setParameter("fin", fechaFin);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Obtener partido con todos los detalles (JOIN FETCH)
    public Optional<Partidos> buscarConDetalles(Long id) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Partidos p " +
                    "LEFT JOIN FETCH p.equipoLocal " +
                    "LEFT JOIN FETCH p.equipoVisitante " +
                    "LEFT JOIN FETCH p.goles " +
                    "WHERE p.idPartido = :id";
            TypedQuery<Partidos> query = em.createQuery(jpql, Partidos.class);
            query.setParameter("id", id);
            List<Partidos> resultados = query.getResultList();
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } finally {
            em.close();
        }
    }

    // Listar todos los partidos con detalles
    public List<Partidos> listarTodosConDetalles() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT DISTINCT p FROM Partidos p " +
                    "LEFT JOIN FETCH p.equipoLocal " +
                    "LEFT JOIN FETCH p.equipoVisitante " +
                    "ORDER BY p.fechaPartido DESC";
            TypedQuery<Partidos> query = em.createQuery(jpql, Partidos.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}