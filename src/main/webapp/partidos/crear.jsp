<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2>Crear Nuevo Partido</h2>

    <div class="card">
        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/partidos">
                <input type="hidden" name="action" value="crear">

                <div class="form-group">
                    <label for="idEquipoLocal">Equipo Local:</label>
                    <select class="form-control" id="idEquipoLocal" name="idEquipoLocal" required>
                        <option value="">Seleccione el equipo local...</option>
                        <c:forEach var="equipo" items="${listaEquipos}">
                            <option value="${equipo.idEquipo}">${equipo.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="idEquipoVisitante">Equipo Visitante:</label>
                    <select class="form-control" id="idEquipoVisitante" name="idEquipoVisitante" required>
                        <option value="">Seleccione el equipo visitante...</option>
                        <c:forEach var="equipo" items="${listaEquipos}">
                            <option value="${equipo.idEquipo}">${equipo.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="fechaPartido">Fecha del Partido:</label>
                    <input type="date" class="form-control" id="fechaPartido" name="fechaPartido" required>
                </div>

                <button type="submit" class="btn btn-primary">
                    Guardar
                </button>
                <a href="${pageContext.request.contextPath}/partidos" class="btn btn-secondary">
                    Cancelar
                </a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />