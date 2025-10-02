package org.futbol.equipofutbol.service.impl;

import org.futbol.equipofutbol.dao.PosicionesDAO;
import org.futbol.equipofutbol.entidades.Posiciones;
import org.futbol.equipofutbol.service.interfaces.IPosicionesService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PosicionesServiceImpl implements IPosicionesService {

    private final PosicionesDAO posicionesDAO;

    public PosicionesServiceImpl() {
        this.posicionesDAO = new PosicionesDAO();
    }

    @Override
    public void crearPosicion(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la posición no puede estar vacío");
        }

        // Verificar si ya existe una posición con ese nombre
        if (existePosicionPorNombre(nombre)) {
            throw new IllegalArgumentException("Ya existe una posición con el nombre: " + nombre);
        }

        Posiciones posicion = new Posiciones(nombre.trim());
        posicionesDAO.crear(posicion);
    }

    @Override
    public void actualizarPosicion(Long id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la posición no puede estar vacío");
        }

        Optional<Posiciones> posicionOpt = posicionesDAO.buscarPorId(id);
        if (posicionOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe una posición con ID: " + id);
        }

        Posiciones posicion = posicionOpt.get();

        // Verificar si el nuevo nombre ya existe en otra posición
        Optional<Posiciones> posicionConMismoNombre = posicionesDAO.buscarPorNombre(nombre.trim());
        if (posicionConMismoNombre.isPresent() && !posicionConMismoNombre.get().getIdPosicion().equals(id)) {
            throw new IllegalArgumentException("Ya existe otra posición con el nombre: " + nombre);
        }

        posicion.setNombre(nombre.trim());
        posicionesDAO.actualizar(posicion);
    }

    @Override
    public void eliminarPosicion(Long id) {
        Optional<Posiciones> posicionOpt = posicionesDAO.buscarPorId(id);
        if (posicionOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe una posición con ID: " + id);
        }

        posicionesDAO.eliminar(id);
    }

    @Override
    public Optional<Posiciones> buscarPosicionPorId(Long id) {
        return posicionesDAO.buscarPorId(id);
    }

    @Override
    public Optional<Posiciones> buscarPosicionPorNombre(String nombre) {
        return posicionesDAO.buscarPorNombre(nombre);
    }

    @Override
    public List<Posiciones> listarTodasLasPosiciones() {
        return posicionesDAO.listarTodos();
    }

    @Override
    public Optional<Posiciones> obtenerPosicionConJugadores(Long id) {
        return posicionesDAO.buscarConJugadores(id);
    }

    @Override
    public boolean existePosicionPorNombre(String nombre) {
        return posicionesDAO.buscarPorNombre(nombre).isPresent();
    }

    @Override
    public void inicializarPosicionesPredeterminadas() {
        List<String> posicionesPredeterminadas = Arrays.asList(
                "Portero",
                "Defensa Central",
                "Lateral Derecho",
                "Lateral Izquierdo",
                "Mediocampista Interior Der",
                "Mediocampista Contension",
                "Mediocampista Interior Izq",
                "Enganche",
                "Extremo Derecho",
                "Extremo Izquierdo",
                "Delantero Centro"
        );

        for (String nombrePosicion : posicionesPredeterminadas) {
            if (!existePosicionPorNombre(nombrePosicion)) {
                crearPosicion(nombrePosicion);
            }
        }
    }
}