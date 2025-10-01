package org.futbol.equipofutbol.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.futbol.equipofutbol.entidades.Jugadores;

import java.util.List;

public class JugadoresDAO extends GenericDAO<Jugadores, Long> {

    public JugadoresDAO() {
        super(Jugadores.class);
    }

    // Buscar jugadores por equipo
    public List<Jugadores> buscarPorEquipo(Long idEquipo) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugadores j WHERE j.equipo.idEquipo = :idEquipo";
            TypedQuery<Jugadores> query = em.createQuery(jpql, Jugadores.class);
            query.setParameter("idEquipo", idEquipo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar jugadores por posición
    public List<Jugadores> buscarPorPosicion(Long idPosicion) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugadores j WHERE j.posicion.idPosicion = :idPosicion";
            TypedQuery<Jugadores> query = em.createQuery(jpql, Jugadores.class);
            query.setParameter("idPosicion", idPosicion);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar jugadores sin posición asignada
    public List<Jugadores> buscarSinPosicion() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugadores j WHERE j.posicion IS NULL";
            TypedQuery<Jugadores> query = em.createQuery(jpql, Jugadores.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar jugadores por nombre parcial
    public List<Jugadores> buscarPorNombreParcial(String nombreParcial) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugadores j WHERE LOWER(j.nombre) LIKE LOWER(:nombre)";
            TypedQuery<Jugadores> query = em.createQuery(jpql, Jugadores.class);
            query.setParameter("nombre", "%" + nombreParcial + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Obtener jugador con equipo y posición (JOIN FETCH)
    public List<Jugadores> listarConDetalles() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugadores j " +
                    "LEFT JOIN FETCH j.equipo " +
                    "LEFT JOIN FETCH j.posicion";
            TypedQuery<Jugadores> query = em.createQuery(jpql, Jugadores.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}