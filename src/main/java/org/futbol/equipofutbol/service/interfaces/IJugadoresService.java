package org.futbol.equipofutbol.service.interfaces;

import org.futbol.equipofutbol.entidades.Jugadores;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IJugadoresService {

    // Crear un nuevo jugador
    void crearJugador(String nombre, LocalDate fechaNacimiento, Long idEquipo);

    // Actualizar jugador existente
    void actualizarJugador(Long id, String nombre, LocalDate fechaNacimiento, Long idEquipo);

    // Eliminar jugador
    void eliminarJugador(Long id);

    // Buscar jugador por ID
    Optional<Jugadores> buscarJugadorPorId(Long id);

    // Listar todos los jugadores
    List<Jugadores> listarTodosLosJugadores();

    // Listar jugadores con detalles (equipo y posición)
    List<Jugadores> listarJugadoresConDetalles();

    // Buscar jugadores por equipo
    List<Jugadores> buscarJugadoresPorEquipo(Long idEquipo);

    // Buscar jugadores por posición
    List<Jugadores> buscarJugadoresPorPosicion(Long idPosicion);

    // Buscar jugadores sin posición
    List<Jugadores> buscarJugadoresSinPosicion();

    // Asignar posición a un jugador
    void asignarPosicion(Long idJugador, Long idPosicion);

    // Remover posición de un jugador
    void removerPosicion(Long idJugador);

    // Buscar jugadores por nombre parcial
    List<Jugadores> buscarJugadoresPorNombreParcial(String nombreParcial);

    // Contar total de jugadores
    long contarJugadores();
}