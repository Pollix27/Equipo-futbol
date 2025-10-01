package org.futbol.equipofutbol.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.futbol.equipofutbol.entidades.Equipos;
import org.futbol.equipofutbol.service.impl.EquiposServiceImpl;
import org.futbol.equipofutbol.service.interfaces.IEquiposService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EquiposServlet", urlPatterns = {"/equipos"})
public class EquiposServlet extends HttpServlet {

    private IEquiposService equiposService;

    @Override
    public void init() throws ServletException {
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
                    listarEquipos(request, response);
                    break;
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarEquipo(request, response);
                    break;
                default:
                    listarEquipos(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarEquipos(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("crear".equals(action)) {
                crearEquipo(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarEquipo(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            listarEquipos(request, response);
        }
    }

    private void listarEquipos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Equipos> listaEquipos = equiposService.listarTodosLosEquipos();
        request.setAttribute("listaEquipos", listaEquipos);
        request.getRequestDispatcher("/equipos/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/equipos/crear.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Equipos> equipo = equiposService.buscarEquipoPorId(id);

        if (equipo.isPresent()) {
            request.setAttribute("equipo", equipo.get());
            request.getRequestDispatcher("/equipos/editar.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Equipo no encontrado");
            listarEquipos(request, response);
        }
    }

    private void crearEquipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        equiposService.crearEquipo(nombre);
        request.setAttribute("mensaje", "Equipo creado exitosamente");
        response.sendRedirect(request.getContextPath() + "/equipos");
    }

    private void actualizarEquipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        equiposService.actualizarEquipo(id, nombre);
        request.setAttribute("mensaje", "Equipo actualizado exitosamente");
        response.sendRedirect(request.getContextPath() + "/equipos");
    }

    private void eliminarEquipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        equiposService.eliminarEquipo(id);
        request.setAttribute("mensaje", "Equipo eliminado exitosamente");
        response.sendRedirect(request.getContextPath() + "/equipos");
    }
}