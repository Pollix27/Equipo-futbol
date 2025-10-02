<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2>Gestión de Goles</h2>

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
        <a href="${pageContext.request.contextPath}/goles?action=registrar" class="btn btn-primary">
            Registrar Gol
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${not empty listaGoles}">
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Partido</th>
                            <th>Jugador</th>
                            <th>Equipo</th>
                            <th>Minuto</th>
                            <th>Fecha</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="gol" items="${listaGoles}">
                            <tr>
                                <td>${gol.idGol}</td>
                                <td>
                                    <c:if test="${not empty gol.partido}">
                                        ${gol.partido.equipoLocal.nombre} vs ${gol.partido.equipoVisitante.nombre}
                                        <br>
                                        <small class="text-muted">
                                            (${gol.partido.golesLocal} - ${gol.partido.golesVisitante})
                                        </small>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${not empty gol.jugador}">
                                        <strong>${gol.jugador.nombre}</strong>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${not empty gol.jugador and not empty gol.jugador.equipo}">
                                        ${gol.jugador.equipo.nombre}
                                    </c:if>
                                </td>
                                <td><span class="badge badge-info">${gol.minuto}'</span></td>
                                <td>
                                    <c:if test="${not empty gol.partido}">
                                        ${gol.partido.fechaPartido}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${not empty gol.partido}">
                                        <a href="${pageContext.request.contextPath}/partidos?action=ver&id=${gol.partido.idPartido}"
                                           class="btn btn-sm btn-info">
                                            Ver Partido
                                        </a>
                                    </c:if>
                                    <a href="${pageContext.request.contextPath}/goles?action=eliminar&id=${gol.idGol}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Está seguro de eliminar este gol?')">
                                        Eliminar
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info">
                        No hay goles registrados. <a href="${pageContext.request.contextPath}/goles?action=registrar">Registrar el primero</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />