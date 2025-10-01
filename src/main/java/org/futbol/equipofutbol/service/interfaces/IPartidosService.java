package org.futbol.equipofutbol.service.interfaces;

import org.futbol.equipofutbol.entidades.Partidos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPartidosService {

    // Crear un nuevo partido
    void crearPartido(Long idEquipoLocal, Long idEquipoVisitante, LocalDate fechaPartido);

    // Actualizar resultado de un partido
    void actualizarResultado(Long idPartido, Integer golesLocal, Integer golesVisitante);

    // Eliminar partido
    void eliminarPartido(Long id);

    // Buscar partido por ID
    Optional<Partidos> buscarPartidoPorId(Long id);

    // Buscar partido con detalles completos
    Optional<Partidos> buscarPartidoConDetalles(Long id);

    // Listar todos los partidos
    List<Partidos> listarTodosLosPartidos();

    // Listar todos los partidos con detalles
    List<Partidos> listarPartidosConDetalles();

    // Buscar partidos por equipo
    List<Partidos> buscarPartidosPorEquipo(Long idEquipo);

    // Buscar partidos por fecha
    List<Partidos> buscarPartidosPorFecha(LocalDate fecha);

    // Buscar partidos en un rango de fechas
    List<Partidos> buscarPartidosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);

    // Contar total de partidos
    long contarPartidos();
}