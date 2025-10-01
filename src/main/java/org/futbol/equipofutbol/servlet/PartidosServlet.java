package org.futbol.equipofutbol.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.futbol.equipofutbol.entidades.Equipos;
import org.futbol.equipofutbol.entidades.Partidos;
import org.futbol.equipofutbol.service.impl.EquiposServiceImpl;
import org.futbol.equipofutbol.service.impl.PartidosServiceImpl;
import org.futbol.equipofutbol.service.interfaces.IEquiposService;
import org.futbol.equipofutbol.service.interfaces.IPartidosService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "PartidosServlet", urlPatterns = {"/partidos"})
public class PartidosServlet extends HttpServlet {

    private IPartidosService partidosService;
    private IEquiposService equiposService;

    @Override
    public void init() throws ServletException {
        partidosService = new PartidosServiceImpl();
        equiposService = new EquiposServiceImpl();
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
                    listarPartidos(request, response);
                    break;
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "ver":
                    verPartido(request, response);
                    break;
                case "eliminar":
                    eliminarPartido(request, response);
                    break;
                default:
                    listarPartidos(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarPartidos(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("crear".equals(action)) {
                crearPartido(request, response);
            } else if ("actualizarResultado".equals(action)) {
                actualizarResultado(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarPartidos(request, response);
        }
    }

    private void listarPartidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Partidos> listaPartidos = partidosService.listarPartidosConDetalles();
        request.setAttribute("listaPartidos", listaPartidos);
        request.getRequestDispatcher("/partidos/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Equipos> listaEquipos = equiposService.listarTodosLosEquipos();
        request.setAttribute("listaEquipos", listaEquipos);
        request.getRequestDispatcher("/partidos/crear.jsp").forward(request, response);
    }

    private void verPartido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Partidos> partido = partidosService.buscarPartidoConDetalles(id);

        if (partido.isPresent()) {
            request.setAttribute("partido", partido.get());
            request.getRequestDispatcher("/partidos/ver.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Partido no encontrado");
            listarPartidos(request, response);
        }
    }

    private void crearPartido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idEquipoLocal = Long.parseLong(request.getParameter("idEquipoLocal"));
        Long idEquipoVisitante = Long.parseLong(request.getParameter("idEquipoVisitante"));
        String fechaStr = request.getParameter("fechaPartido");

        LocalDate fechaPartido = LocalDate.parse(fechaStr);

        partidosService.crearPartido(idEquipoLocal, idEquipoVisitante, fechaPartido);
        response.sendRedirect(request.getContextPath() + "/partidos");
    }

    private void actualizarResultado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idPartido = Long.parseLong(request.getParameter("idPartido"));
        Integer golesLocal = Integer.parseInt(request.getParameter("golesLocal"));
        Integer golesVisitante = Integer.parseInt(request.getParameter("golesVisitante"));

        partidosService.actualizarResultado(idPartido, golesLocal, golesVisitante);
        response.sendRedirect(request.getContextPath() + "/partidos?action=ver&id=" + idPartido);
    }

    private void eliminarPartido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        partidosService.eliminarPartido(id);
        response.sendRedirect(request.getContextPath() + "/partidos");
    }
}