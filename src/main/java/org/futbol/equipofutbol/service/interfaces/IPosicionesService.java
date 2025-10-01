package org.futbol.equipofutbol.service.interfaces;

import org.futbol.equipofutbol.entidades.Posiciones;

import java.util.List;
import java.util.Optional;

public interface IPosicionesService {

    // Crear una nueva posición
    void crearPosicion(String nombre);

    // Actualizar posición existente
    void actualizarPosicion(Long id, String nombre);

    // Eliminar posición
    void eliminarPosicion(Long id);

    // Buscar posición por ID
    Optional<Posiciones> buscarPosicionPorId(Long id);

    // Buscar posición por nombre
    Optional<Posiciones> buscarPosicionPorNombre(String nombre);

    // Listar todas las posiciones
    List<Posiciones> listarTodasLasPosiciones();

    // Obtener posición con jugadores
    Optional<Posiciones> obtenerPosicionConJugadores(Long id);

    // Verificar si existe una posición por nombre
    boolean existePosicionPorNombre(String nombre);

    // Inicializar posiciones predeterminadas
    void inicializarPosicionesPredeterminadas();
}