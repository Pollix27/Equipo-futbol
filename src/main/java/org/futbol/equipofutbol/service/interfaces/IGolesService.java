package org.futbol.equipofutbol.service.interfaces;

import org.futbol.equipofutbol.entidades.Goles;

import java.util.List;
import java.util.Optional;

public interface IGolesService {

    // Registrar un nuevo gol
    void registrarGol(Long idPartido, Long idJugador, Integer minuto);

    // Eliminar gol
    void eliminarGol(Long id);

    // Buscar gol por ID
    Optional<Goles> buscarGolPorId(Long id);

    // Listar todos los goles
    List<Goles> listarTodosLosGoles();

    // Listar goles con detalles
    List<Goles> listarGolesConDetalles();

    // Buscar goles por partido
    List<Goles> buscarGolesPorPartido(Long idPartido);

    // Buscar goles por jugador
    List<Goles> buscarGolesPorJugador(Long idJugador);

    // Contar goles de un jugador
    long contarGolesPorJugador(Long idJugador);

    // Obtener máximos goleadores
    List<Object[]> obtenerMaximosGoleadores(int limite);

    // Contar total de goles
    long contarGoles();
}