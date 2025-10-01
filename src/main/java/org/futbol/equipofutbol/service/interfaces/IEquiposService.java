package org.futbol.equipofutbol.service.interfaces;

import org.futbol.equipofutbol.entidades.Equipos;

import java.util.List;
import java.util.Optional;

public interface IEquiposService {

    // Crear un nuevo equipo
    void crearEquipo(String nombre);

    // Actualizar equipo existente
    void actualizarEquipo(Long id, String nombre);

    // Eliminar equipo
    void eliminarEquipo(Long id);

    // Buscar equipo por ID
    Optional<Equipos> buscarEquipoPorId(Long id);

    // Buscar equipo por nombre
    Optional<Equipos> buscarEquipoPorNombre(String nombre);

    // Listar todos los equipos
    List<Equipos> listarTodosLosEquipos();

    // Buscar equipos por nombre parcial
    List<Equipos> buscarEquiposPorNombreParcial(String nombreParcial);

    // Obtener equipo con jugadores
    Optional<Equipos> obtenerEquipoConJugadores(Long id);

    // Contar total de equipos
    long contarEquipos();

    // Verificar si existe un equipo por nombre
    boolean existeEquipoPorNombre(String nombre);
}