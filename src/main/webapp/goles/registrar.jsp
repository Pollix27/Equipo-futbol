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
    <h2><i class="fas fa-plus-circle"></i> Registrar Gol</h2>

    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${not empty partido}">
                    <!-- Formulario cuando ya se seleccionó un partido -->
                    <div class="alert alert-info mb-4">
                        <h5>Partido Seleccionado</h5>
                        <p class="mb-0">
                            <strong>${partido.equipoLocal.nombre}</strong>
                            <span class="badge badge-primary">${partido.golesLocal}</span>
                            -
                            <span class="badge badge-primary">${partido.golesVisitante}</span>
                            <strong>${partido.equipoVisitante.nombre}</strong>
                            <br>
                            <small class="text-muted">Fecha: ${partido.fechaPartido}</small>
                        </p>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/goles">
                        <input type="hidden" name="action" value="registrar">
                        <input type="hidden" name="idPartido" value="${partido.idPartido}">

                        <div class="form-group">
                            <label for="idJugador">Jugador que anotó:</label>
                            <select class="form-control" id="idJugador" name="idJugador" required>
                                <option value="">Seleccione el jugador...</option>
                                <optgroup label="${partido.equipoLocal.nombre} (Local)">
                                    <c:forEach var="jugador" items="${jugadoresLocal}">
                                        <option value="${jugador.idJugador}">${jugador.nombre}</option>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="${partido.equipoVisitante.nombre} (Visitante)">
                                    <c:forEach var="jugador" items="${jugadoresVisitante}">
                                        <option value="${jugador.idJugador}">${jugador.nombre}</option>
                                    </c:forEach>
                                </optgroup>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="minuto">Minuto del gol:</label>
                            <input type="number" class="form-control" id="minuto" name="minuto"
                                   min="0" max="120" placeholder="Ej: 45" required>
                            <small class="form-text text-muted">Ingrese el minuto entre 0 y 120</small>
                        </div>

                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Registrar Gol
                        </button>
                        <a href="${pageContext.request.contextPath}/partidos?action=ver&id=${partido.idPartido}"
                           class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </form>
                </c:when>
                <c:otherwise>
                    <!-- Selección de partido -->
                    <h5 class="mb-3">Seleccione el partido:</h5>
                    <c:choose>
                        <c:when test="${not empty listaPartidos}">
                            <div class="list-group">
                                <c:forEach var="p" items="${listaPartidos}">
                                    <a href="${pageContext.request.contextPath}/goles?action=registrar&idPartido=${p.idPartido}"
                                       class="list-group-item list-group-item-action">
                                        <div class="d-flex w-100 justify-content-between">
                                            <h5 class="mb-1">
                                                    ${p.equipoLocal.nombre}
                                                <span class="badge badge-primary">${p.golesLocal}</span>
                                                -
                                                <span class="badge badge-primary">${p.golesVisitante}</span>
                                                    ${p.equipoVisitante.nombre}
                                            </h5>
                                            <small class="text-muted">${p.fechaPartido}</small>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-warning">
                                No hay partidos registrados.
                                <a href="${pageContext.request.contextPath}/partidos?action=nuevo">Crear un partido primero</a>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="mt-3">
                        <a href="${pageContext.request.contextPath}/goles" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Volver
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />