package org.futbol.equipofutbol.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.futbol.equipofutbol.entidades.Goles;
import org.futbol.equipofutbol.entidades.Jugadores;
import org.futbol.equipofutbol.entidades.Partidos;
import org.futbol.equipofutbol.service.impl.GolesServiceImpl;
import org.futbol.equipofutbol.service.impl.JugadoresServiceImpl;
import org.futbol.equipofutbol.service.impl.PartidosServiceImpl;
import org.futbol.equipofutbol.service.interfaces.IGolesService;
import org.futbol.equipofutbol.service.interfaces.IJugadoresService;
import org.futbol.equipofutbol.service.interfaces.IPartidosService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GolesServlet", urlPatterns = {"/goles"})
public class GolesServlet extends HttpServlet {

    private IGolesService golesService;
    private IPartidosService partidosService;
    private IJugadoresService jugadoresService;

    @Override
    public void init() throws ServletException {
        golesService = new GolesServiceImpl();
        partidosService = new PartidosServiceImpl();
        jugadoresService = new JugadoresServiceImpl();
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
                    listarGoles(request, response);
                    break;
                case "registrar":
                    mostrarFormularioRegistrar(request, response);
                    break;
                case "eliminar":
                    eliminarGol(request, response);
                    break;
                default:
                    listarGoles(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarGoles(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("registrar".equals(action)) {
                registrarGol(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarGoles(request, response);
        }
    }

    private void listarGoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Goles> listaGoles = golesService.listarGolesConDetalles();
        request.setAttribute("listaGoles", listaGoles);
        request.getRequestDispatcher("/goles/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioRegistrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPartidoStr = request.getParameter("idPartido");

        if (idPartidoStr != null && !idPartidoStr.isEmpty()) {
            Long idPartido = Long.parseLong(idPartidoStr);
            Optional<Partidos> partido = partidosService.buscarPartidoConDetalles(idPartido);

            if (partido.isPresent()) {
                Partidos p = partido.get();
                List<Jugadores> jugadoresLocal = jugadoresService.buscarJugadoresPorEquipo(
                        p.getEquipoLocal().getIdEquipo());
                List<Jugadores> jugadoresVisitante = jugadoresService.buscarJugadoresPorEquipo(
                        p.getEquipoVisitante().getIdEquipo());

                request.setAttribute("partido", p);
                request.setAttribute("jugadoresLocal", jugadoresLocal);
                request.setAttribute("jugadoresVisitante", jugadoresVisitante);
            }
        } else {
            List<Partidos> listaPartidos = partidosService.listarPartidosConDetalles();
            request.setAttribute("listaPartidos", listaPartidos);
        }

        request.getRequestDispatcher("/goles/registrar.jsp").forward(request, response);
    }

    private void registrarGol(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idPartido = Long.parseLong(request.getParameter("idPartido"));
        Long idJugador = Long.parseLong(request.getParameter("idJugador"));
        Integer minuto = Integer.parseInt(request.getParameter("minuto"));

        golesService.registrarGol(idPartido, idJugador, minuto);
        response.sendRedirect(request.getContextPath() + "/partidos?action=ver&id=" + idPartido);
    }

    private void eliminarGol(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        golesService.eliminarGol(id);
        response.sendRedirect(request.getContextPath() + "/goles");
    }
}