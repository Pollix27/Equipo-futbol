package org.futbol.equipofutbol.service.impl;

import org.futbol.equipofutbol.dao.EquiposDAO;
import org.futbol.equipofutbol.dao.JugadoresDAO;
import org.futbol.equipofutbol.dao.PosicionesDAO;
import org.futbol.equipofutbol.entidades.Equipos;
import org.futbol.equipofutbol.entidades.Jugadores;
import org.futbol.equipofutbol.entidades.Posiciones;
import org.futbol.equipofutbol.service.interfaces.IJugadoresService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JugadoresServiceImpl implements IJugadoresService {

    private final JugadoresDAO jugadoresDAO;
    private final EquiposDAO equiposDAO;
    private final PosicionesDAO posicionesDAO;

    public JugadoresServiceImpl() {
        this.jugadoresDAO = new JugadoresDAO();
        this.equiposDAO = new EquiposDAO();
        this.posicionesDAO = new PosicionesDAO();
    }

    @Override
    public void crearJugador(String nombre, LocalDate fechaNacimiento, Long idEquipo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }

        if (idEquipo == null) {
            throw new IllegalArgumentException("Debe especificar el equipo del jugador");
        }

        // Verificar que el equipo existe
        Optional<Equipos> equipoOpt = equiposDAO.buscarPorId(idEquipo);
        if (equipoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un equipo con ID: " + idEquipo);
        }

        Jugadores jugador = new Jugadores(nombre.trim(), fechaNacimiento);
        jugador.setEquipo(equipoOpt.get());
        jugadoresDAO.crear(jugador);
    }

    @Override
    public void actualizarJugador(Long id, String nombre, LocalDate fechaNacimiento, Long idEquipo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }

        Optional<Jugadores> jugadorOpt = jugadoresDAO.buscarPorId(id);
        if (jugadorOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un jugador con ID: " + id);
        }

        // Verificar que el equipo existe
        Optional<Equipos> equipoOpt = equiposDAO.buscarPorId(idEquipo);
        if (equipoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un equipo con ID: " + idEquipo);
        }

        Jugadores jugador = jugadorOpt.get();
        jugador.setNombre(nombre.trim());
        jugador.setFechaNacimiento(fechaNacimiento);
        jugador.setEquipo(equipoOpt.get());
        jugadoresDAO.actualizar(jugador);
    }

    @Override
    public void eliminarJugador(Long id) {
        Optional<Jugadores> jugadorOpt = jugadoresDAO.buscarPorId(id);
        if (jugadorOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un jugador con ID: " + id);
        }

        jugadoresDAO.eliminar(id);
    }

    @Override
    public Optional<Jugadores> buscarJugadorPorId(Long id) {
        return jugadoresDAO.buscarPorId(id);
    }

    @Override
    public List<Jugadores> listarTodosLosJugadores() {
        return jugadoresDAO.listarTodos();
    }

    @Override
    public List<Jugadores> listarJugadoresConDetalles() {
        return jugadoresDAO.listarConDetalles();
    }

    @Override
    public List<Jugadores> buscarJugadoresPorEquipo(Long idEquipo) {
        return jugadoresDAO.buscarPorEquipo(idEquipo);
    }

    @Override
    public List<Jugadores> buscarJugadoresPorPosicion(Long idPosicion) {
        return jugadoresDAO.buscarPorPosicion(idPosicion);
    }

    @Override
    public List<Jugadores> buscarJugadoresSinPosicion() {
        return jugadoresDAO.buscarSinPosicion();
    }

    @Override
    public void asignarPosicion(Long idJugador, Long idPosicion) {
        Optional<Jugadores> jugadorOpt = jugadoresDAO.buscarPorId(idJugador);
        if (jugadorOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un jugador con ID: " + idJugador);
        }

        Optional<Posiciones> posicionOpt = posicionesDAO.buscarPorId(idPosicion);
        if (posicionOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe una posición con ID: " + idPosicion);
        }

        Jugadores jugador = jugadorOpt.get();
        jugador.setPosicion(posicionOpt.get());
        jugadoresDAO.actualizar(jugador);
    }

    @Override
    public void removerPosicion(Long idJugador) {
        Optional<Jugadores> jugadorOpt = jugadoresDAO.buscarPorId(idJugador);
        if (jugadorOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un jugador con ID: " + idJugador);
        }

        Jugadores jugador = jugadorOpt.get();
        jugador.setPosicion(null);
        jugadoresDAO.actualizar(jugador);
    }

    @Override
    public List<Jugadores> buscarJugadoresPorNombreParcial(String nombreParcial) {
        return jugadoresDAO.buscarPorNombreParcial(nombreParcial);
    }

    @Override
    public long contarJugadores() {
        return jugadoresDAO.contar();
    }
}