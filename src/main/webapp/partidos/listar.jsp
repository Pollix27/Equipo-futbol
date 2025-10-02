<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2>Gestión de Partidos</h2>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${error}
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
    </c:if>

    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/partidos?action=nuevo" class="btn btn-primary">
            Partido
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Equipo Local</th>
                    <th>Resultado</th>
                    <th>Equipo Visitante</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="partido" items="${listaPartidos}">
                    <tr>
                        <td>${partido.idPartido}</td>
                        <td>${partido.fechaPartido}</td>
                        <td><strong>${partido.equipoLocal.nombre}</strong></td>
                        <td class="text-center">
                            <span class="badge badge-primary">${partido.golesLocal}</span>
                            -
                            <span class="badge badge-primary">${partido.golesVisitante}</span>
                        </td>
                        <td><strong>${partido.equipoVisitante.nombre}</strong></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/partidos?action=ver&id=${partido.idPartido}"
                               class="btn btn-sm btn-info">
                                Ver
                            </a>
                            <a href="${pageContext.request.contextPath}/goles?action=registrar&idPartido=${partido.idPartido}"
                               class="btn btn-sm btn-success">
                                Gol
                            </a>
                            <a href="${pageContext.request.contextPath}/partidos?action=eliminar&id=${partido.idPartido}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Está seguro de eliminar este partido?')">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty listaPartidos}">
                <div class="alert alert-info">
                    No hay partidos registrados. <a href="${pageContext.request.contextPath}/partidos?action=nuevo">Crear el primero</a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />