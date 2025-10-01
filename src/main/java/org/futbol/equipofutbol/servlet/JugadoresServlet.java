package org.futbol.equipofutbol.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.futbol.equipofutbol.entidades.Equipos;
import org.futbol.equipofutbol.entidades.Jugadores;
import org.futbol.equipofutbol.entidades.Posiciones;
import org.futbol.equipofutbol.service.impl.EquiposServiceImpl;
import org.futbol.equipofutbol.service.impl.JugadoresServiceImpl;
import org.futbol.equipofutbol.service.impl.PosicionesServiceImpl;
import org.futbol.equipofutbol.service.interfaces.IEquiposService;
import org.futbol.equipofutbol.service.interfaces.IJugadoresService;
import org.futbol.equipofutbol.service.interfaces.IPosicionesService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "JugadoresServlet", urlPatterns = {"/jugadores"})
public class JugadoresServlet extends HttpServlet {

    private IJugadoresService jugadoresService;
    private IEquiposService equiposService;
    private IPosicionesService posicionesService;

    @Override
    public void init() throws ServletException {
        jugadoresService = new JugadoresServiceImpl();
        equiposService = new EquiposServiceImpl();
        posicionesService = new PosicionesServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "listar":
                    listarJugadores(request, response);
                    break;
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarJugador(request, response);
                    break;
                case "asignarPosicion":
                    mostrarFormularioAsignarPosicion(request, response);
                    break;
                default:
                    listarJugadores(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarJugadores(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("crear".equals(action)) {
                crearJugador(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarJugador(request, response);
            } else if ("asignar".equals(action)) {
                asignarPosicion(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarJugadores(request, response);
        }
    }

    private void listarJugadores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Jugadores> listaJugadores = jugadoresService.listarJugadoresConDetalles();
        request.setAttribute("listaJugadores", listaJugadores);
        request.getRequestDispatcher("/jugadores/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Equipos> listaEquipos = equiposService.listarTodosLosEquipos();
        request.setAttribute("listaEquipos", listaEquipos);
        request.getRequestDispatcher("/jugadores/crear.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Jugadores> jugador = jugadoresService.buscarJugadorPorId(id);

        if (jugador.isPresent()) {
            List<Equipos> listaEquipos = equiposService.listarTodosLosEquipos();
            request.setAttribute("jugador", jugador.get());
            request.setAttribute("listaEquipos", listaEquipos);
            request.getRequestDispatcher("/jugadores/editar.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Jugador no encontrado");
            listarJugadores(request, response);
        }
    }

    private void mostrarFormularioAsignarPosicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Jugadores> jugador = jugadoresService.buscarJugadorPorId(id);

        if (jugador.isPresent()) {
            List<Posiciones> listaPosiciones = posicionesService.listarTodasLasPosiciones();
            request.setAttribute("jugador", jugador.get());
            request.setAttribute("listaPosiciones", listaPosiciones);
            request.getRequestDispatcher("/jugadores/asignarPosicion.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Jugador no encontrado");
            listarJugadores(request, response);
        }
    }

    private void crearJugador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        Long idEquipo = Long.parseLong(request.getParameter("idEquipo"));

        LocalDate fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
        }

        jugadoresService.crearJugador(nombre, fechaNacimiento, idEquipo);
        response.sendRedirect(request.getContextPath() + "/jugadores");
    }

    private void actualizarJugador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        Long idEquipo = Long.parseLong(request.getParameter("idEquipo"));

        LocalDate fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
        }

        jugadoresService.actualizarJugador(id, nombre, fechaNacimiento, idEquipo);
        response.sendRedirect(request.getContextPath() + "/jugadores");
    }

    private void eliminarJugador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        jugadoresService.eliminarJugador(id);
        response.sendRedirect(request.getContextPath() + "/jugadores");
    }

    private void asignarPosicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idJugador = Long.parseLong(request.getParameter("idJugador"));
        Long idPosicion = Long.parseLong(request.getParameter("idPosicion"));

        jugadoresService.asignarPosicion(idJugador, idPosicion);
        response.sendRedirect(request.getContextPath() + "/jugadores");
    }
}