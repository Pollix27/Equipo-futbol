package org.futbol.equipofutbol.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.futbol.equipofutbol.dao.GenericDAO;
import org.futbol.equipofutbol.entidades.Posiciones;

import java.util.List;
import java.util.Optional;

public class PosicionesDAO extends GenericDAO<Posiciones, Long> {

    public PosicionesDAO() {
        super(Posiciones.class);
    }

    // Buscar posición por nombre
    public Optional<Posiciones> buscarPorNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Posiciones p WHERE p.nombre = :nombre";
            TypedQuery<Posiciones> query = em.createQuery(jpql, Posiciones.class);
            query.setParameter("nombre", nombre);
            List<Posiciones> resultados = query.getResultList();
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } finally {
            em.close();
        }
    }

    // Obtener posición con sus jugadores (JOIN FETCH)
    public Optional<Posiciones> buscarConJugadores(Long id) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Posiciones p LEFT JOIN FETCH p.jugadores WHERE p.idPosicion = :id";
            TypedQuery<Posiciones> query = em.createQuery(jpql, Posiciones.class);
            query.setParameter("id", id);
            List<Posiciones> resultados = query.getResultList();
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } finally {
            em.close();
        }
    }
}