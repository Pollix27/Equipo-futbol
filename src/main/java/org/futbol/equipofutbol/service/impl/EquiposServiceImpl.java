package org.futbol.equipofutbol.service.impl;

import org.futbol.equipofutbol.dao.EquiposDAO;
import org.futbol.equipofutbol.entidades.Equipos;
import org.futbol.equipofutbol.service.interfaces.IEquiposService;

import java.util.List;
import java.util.Optional;

public class EquiposServiceImpl implements IEquiposService {

    private final EquiposDAO equiposDAO;

    public EquiposServiceImpl() {
        this.equiposDAO = new EquiposDAO();
    }

    @Override
    public void crearEquipo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }

        // Verificar si ya existe un equipo con ese nombre
        if (existeEquipoPorNombre(nombre)) {
            throw new IllegalArgumentException("Ya existe un equipo con el nombre: " + nombre);
        }

        Equipos equipo = new Equipos(nombre.trim());
        equiposDAO.crear(equipo);
    }

    @Override
    public void actualizarEquipo(Long id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }

        Optional<Equipos> equipoOpt = equiposDAO.buscarPorId(id);
        if (equipoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un equipo con ID: " + id);
        }

        Equipos equipo = equipoOpt.get();

        // Verificar si el nuevo nombre ya existe en otro equipo
        Optional<Equipos> equipoConMismoNombre = equiposDAO.buscarPorNombre(nombre.trim());
        if (equipoConMismoNombre.isPresent() && !equipoConMismoNombre.get().getIdEquipo().equals(id)) {
            throw new IllegalArgumentException("Ya existe otro equipo con el nombre: " + nombre);
        }

        equipo.setNombre(nombre.trim());
        equiposDAO.actualizar(equipo);
    }

    @Override
    public void eliminarEquipo(Long id) {
        Optional<Equipos> equipoOpt = equiposDAO.buscarPorId(id);
        if (equipoOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe un equipo con ID: " + id);
        }

        equiposDAO.eliminar(id);
    }

    @Override
    public Optional<Equipos> buscarEquipoPorId(Long id) {
        return equiposDAO.buscarPorId(id);
    }

    @Override
    public Optional<Equipos> buscarEquipoPorNombre(String nombre) {
        return equiposDAO.buscarPorNombre(nombre);
    }

    @Override
    public List<Equipos> listarTodosLosEquipos() {
        return equiposDAO.listarTodos();
    }

    @Override
    public List<Equipos> buscarEquiposPorNombreParcial(String nombreParcial) {
        return equiposDAO.buscarPorNombreParcial(nombreParcial);
    }

    @Override
    public Optional<Equipos> obtenerEquipoConJugadores(Long id) {
        return equiposDAO.buscarConJugadores(id);
    }

    @Override
    public long contarEquipos() {
        return equiposDAO.contar();
    }

    @Override
    public boolean existeEquipoPorNombre(String nombre) {
        return equiposDAO.buscarPorNombre(nombre).isPresent();
    }
}