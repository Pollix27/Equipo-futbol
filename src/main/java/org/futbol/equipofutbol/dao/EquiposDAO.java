package org.futbol.equipofutbol.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.futbol.equipofutbol.entidades.Equipos;

import java.util.List;
import java.util.Optional;

public class EquiposDAO extends GenericDAO<Equipos, Long> {

    public EquiposDAO() {
        super(Equipos.class);
    }

    // Buscar equipo por nombre
    public Optional<Equipos> buscarPorNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM Equipos e WHERE e.nombre = :nombre";
            TypedQuery<Equipos> query = em.createQuery(jpql, Equipos.class);
            query.setParameter("nombre", nombre);
            List<Equipos> resultados = query.getResultList();
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } finally {
            em.close();
        }
    }

    // Buscar equipos por nombre parcial (LIKE)
    public List<Equipos> buscarPorNombreParcial(String nombreParcial) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM Equipos e WHERE LOWER(e.nombre) LIKE LOWER(:nombre)";
            TypedQuery<Equipos> query = em.createQuery(jpql, Equipos.class);
            query.setParameter("nombre", "%" + nombreParcial + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Obtener equipo con sus jugadores (JOIN FETCH)
    public Optional<Equipos> buscarConJugadores(Long id) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM Equipos e LEFT JOIN FETCH e.jugadores WHERE e.idEquipo = :id";
            TypedQuery<Equipos> query = em.createQuery(jpql, Equipos.class);
            query.setParameter("id", id);
            List<Equipos> resultados = query.getResultList();
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } finally {
            em.close();
        }
    }
}