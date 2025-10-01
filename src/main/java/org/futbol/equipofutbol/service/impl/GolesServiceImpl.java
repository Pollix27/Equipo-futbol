package org.futbol.equipofutbol.service.impl;

import org.futbol.equipofutbol.dao.GolesDAO;
import org.futbol.equipofutbol.dao.JugadoresDAO;
import org.futbol.equipofutbol.dao.PartidosDAO;
import org.futbol.equipofutbol.entidades.Goles;
import org.futbol.equipofutbol.entidades.Jugadores;
import org.futbol.equipofutbol.entidades.Partidos;
import org.futbol.equipofutbol.service.interfaces.IGolesService;

import java.util.List;
import java.util.Optional;

public class GolesServiceImpl implements IGolesService {

    private final GolesDAO golesDAO;
    private final PartidosDAO partidosDAO;
    private final JugadoresDAO jugadoresDAO;

    public GolesServiceImpl() {
        this.golesDAO = new GolesDAO();
        this.partidosDAO = new PartidosDAO();
        this.jugadoresDAO = new JugadoresDAO();
    }

    @Override
    public void registrarGol(Long idPartido, Long idJugador, Integer minuto) {
        if (idPartido == null || idJugador == null) {
            throw new IllegalArgumentException("Debe especificar el partido y el jugador");
        }

        if (minuto == null || minuto < 0 || minuto > 120) {
            throw new IllegalArgumentException("El minuto debe estar entre 0 y 120");
        }

        // Verificar que el partido existe
        Optional<Partidos> partidoOpt = partidosDAO.buscarPorId(idPartido);
        if (partidoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un partido con ID: " + idPartido);
        }

        // Verificar que el jugador existe
        Optional<Jugadores> jugadorOpt = jugadoresDAO.buscarPorId(idJugador);
        if (jugadorOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un jugador con ID: " + idJugador);
        }

        Partidos partido = partidoOpt.get();
        Jugadores jugador = jugadorOpt.get();

        // Verificar que el jugador pertenece a uno de los equipos del partido
        if (!jugador.getEquipo().equals(partido.getEquipoLocal()) &&
                !jugador.getEquipo().equals(partido.getEquipoVisitante())) {
            throw new IllegalArgumentException("El jugador no pertenece a ninguno de los equipos del partido");
        }

        // Crear el gol
        Goles gol = new Goles(partido, jugador, minuto);
        golesDAO.crear(gol);

        // Actualizar el marcador del partido
        if (jugador.getEquipo().equals(partido.getEquipoLocal())) {
            partido.setGolesLocal(partido.getGolesLocal() + 1);
        } else {
            partido.setGolesVisitante(partido.getGolesVisitante() + 1);
        }
        partidosDAO.actualizar(partido);
    }

    @Override
    public void eliminarGol(Long id) {
        Optional<Goles> golOpt = golesDAO.buscarPorId(id);
        if (golOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un gol con ID: " + id);
        }

        Goles gol = golOpt.get();
        Partidos partido = gol.getPartido();
        Jugadores jugador = gol.getJugador();

        // Actualizar el marcador del partido antes de eliminar
        if (jugador.getEquipo().equals(partido.getEquipoLocal())) {
            partido.setGolesLocal(Math.max(0, partido.getGolesLocal() - 1));
        } else {
            partido.setGolesVisitante(Math.max(0, partido.getGolesVisitante() - 1));
        }
        partidosDAO.actualizar(partido);

        golesDAO.eliminar(id);
    }

    @Override
    public Optional<Goles> buscarGolPorId(Long id) {
        return golesDAO.buscarPorId(id);
    }

    @Override
    public List<Goles> listarTodosLosGoles() {
        return golesDAO.listarTodos();
    }

    @Override
    public List<Goles> listarGolesConDetalles() {
        return golesDAO.listarConDetalles();
    }

    @Override
    public List<Goles> buscarGolesPorPartido(Long idPartido) {
        return golesDAO.buscarPorPartido(idPartido);
    }

    @Override
    public List<Goles> buscarGolesPorJugador(Long idJugador) {
        return golesDAO.buscarPorJugador(idJugador);
    }

    @Override
    public long contarGolesPorJugador(Long idJugador) {
        return golesDAO.contarGolesPorJugador(idJugador);
    }

    @Override
    public List<Object[]> obtenerMaximosGoleadores(int limite) {
        if (limite <= 0) {
            throw new IllegalArgumentException("El límite debe ser mayor a 0");
        }
        return golesDAO.obtenerMaximosGoleadores(limite);
    }

    @Override
    public long contarGoles() {
        return golesDAO.contar();
    }
}