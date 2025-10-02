<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2>Crear Nuevo Jugador</h2>

    <div class="card">
        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/jugadores">
                <input type="hidden" name="action" value="crear">

                <div class="form-group">
                    <label for="nombre">Nombre del Jugador:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre"
                           placeholder="Ej: Lionel Messi" required>
                </div>

                <div class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento">
                </div>

                <div class="form-group">
                    <label for="idEquipo">Equipo:</label>
                    <select class="form-control" id="idEquipo" name="idEquipo" required>
                        <option value="">Seleccione un equipo...</option>
                        <c:forEach var="equipo" items="${listaEquipos}">
                            <option value="${equipo.idEquipo}">${equipo.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">
                    Guardar
                </button>
                <a href="${pageContext.request.contextPath}/jugadores" class="btn btn-secondary">
                    Cancelar
                </a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />