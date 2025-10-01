package org.futbol.equipofutbol.service.impl;

import org.futbol.equipofutbol.dao.EquiposDAO;
import org.futbol.equipofutbol.dao.PartidosDAO;
import org.futbol.equipofutbol.entidades.Equipos;
import org.futbol.equipofutbol.entidades.Partidos;
import org.futbol.equipofutbol.service.interfaces.IPartidosService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PartidosServiceImpl implements IPartidosService {

    private final PartidosDAO partidosDAO;
    private final EquiposDAO equiposDAO;

    public PartidosServiceImpl() {
        this.partidosDAO = new PartidosDAO();
        this.equiposDAO = new EquiposDAO();
    }

    @Override
    public void crearPartido(Long idEquipoLocal, Long idEquipoVisitante, LocalDate fechaPartido) {
        if (idEquipoLocal == null || idEquipoVisitante == null) {
            throw new IllegalArgumentException("Debe especificar ambos equipos");
        }

        if (idEquipoLocal.equals(idEquipoVisitante)) {
            throw new IllegalArgumentException("Un equipo no puede jugar contra sí mismo");
        }

        if (fechaPartido == null) {
            throw new IllegalArgumentException("Debe especificar la fecha del partido");
        }

        // Verificar que ambos equipos existen
        Optional<Equipos> equipoLocalOpt = equiposDAO.buscarPorId(idEquipoLocal);
        if (equipoLocalOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un equipo local con ID: " + idEquipoLocal);
        }

        Optional<Equipos> equipoVisitanteOpt = equiposDAO.buscarPorId(idEquipoVisitante);
        if (equipoVisitanteOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un equipo visitante con ID: " + idEquipoVisitante);
        }

        Partidos partido = new Partidos(equipoLocalOpt.get(), equipoVisitanteOpt.get(), fechaPartido);
        partidosDAO.crear(partido);
    }

    @Override
    public void actualizarResultado(Long idPartido, Integer golesLocal, Integer golesVisitante) {
        if (golesLocal == null || golesVisitante == null) {
            throw new IllegalArgumentException("Debe especificar los goles de ambos equipos");
        }

        if (golesLocal < 0 || golesVisitante < 0) {
            throw new IllegalArgumentException("Los goles no pueden ser negativos");
        }

        Optional<Partidos> partidoOpt = partidosDAO.buscarPorId(idPartido);
        if (partidoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un partido con ID: " + idPartido);
        }

        Partidos partido = partidoOpt.get();
        partido.setGolesLocal(golesLocal);
        partido.setGolesVisitante(golesVisitante);
        partidosDAO.actualizar(partido);
    }

    @Override
    public void eliminarPartido(Long id) {
        Optional<Partidos> partidoOpt = partidosDAO.buscarPorId(id);
        if (partidoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un partido con ID: " + id);
        }

        partidosDAO.eliminar(id);
    }

    @Override
    public Optional<Partidos> buscarPartidoPorId(Long id) {
        return partidosDAO.buscarPorId(id);
    }

    @Override
    public Optional<Partidos> buscarPartidoConDetalles(Long id) {
        return partidosDAO.buscarConDetalles(id);
    }

    @Override
    public List<Partidos> listarTodosLosPartidos() {
        return partidosDAO.listarTodos();
    }

    @Override
    public List<Partidos> listarPartidosConDetalles() {
        return partidosDAO.listarTodosConDetalles();
    }

    @Override
    public List<Partidos> buscarPartidosPorEquipo(Long idEquipo) {
        return partidosDAO.buscarPorEquipo(idEquipo);
    }

    @Override
    public List<Partidos> buscarPartidosPorFecha(LocalDate fecha) {
        return partidosDAO.buscarPorFecha(fecha);
    }

    @Override
    public List<Partidos> buscarPartidosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        return partidosDAO.buscarPorRangoFechas(fechaInicio, fechaFin);
    }

    @Override
    public long contarPartidos() {
        return partidosDAO.contar();
    }
}