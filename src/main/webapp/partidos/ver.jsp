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
    <h2>Detalles del Partido</h2>

    <div class="card mb-4">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Información del Partido</h4>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-4 text-center">
                    <h3>${partido.equipoLocal.nombre}</h3>
                    <p class="text-muted">Local</p>
                </div>
                <div class="col-md-4 text-center">
                    <h1 class="display-3">
                        <span class="badge badge-success">${partido.golesLocal}</span>
                        -
                        <span class="badge badge-success">${partido.golesVisitante}</span>
                    </h1>
                    <p class="text-muted">${partido.fechaPartido}</p>
                </div>
                <div class="col-md-4 text-center">
                    <h3>${partido.equipoVisitante.nombre}</h3>
                    <p class="text-muted">Visitante</p>
                </div>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-header">
            <h5 class="mb-0">
                Goles del Partido
                <a href="${pageContext.request.contextPath}/goles?action=registrar&idPartido=${partido.idPartido}"
                   class="btn btn-sm btn-success float-right">
                    Registrar Gol
                </a>
            </h5>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${not empty partido.goles}">
                    <table class="table table-sm">
                        <thead>
                        <tr>
                            <th>Minuto</th>
                            <th>Jugador</th>
                            <th>Equipo</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="gol" items="${partido.goles}">
                            <tr>
                                <td><span class="badge badge-info">${gol.minuto}'</span></td>
                                <td>${gol.jugador.nombre}</td>
                                <td>${gol.jugador.equipo.nombre}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info">
                        No se han registrado goles en este partido
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/partidos" class="btn btn-secondary">
            Volver a Partidos
        </a>
    </div>
</div>

<jsp:include page="../99.jsp" />