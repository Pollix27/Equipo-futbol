<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2><i class="fas fa-map-marker-alt"></i> Asignar Posición a Jugador</h2>

    <div class="card">
        <div class="card-body">
            <div class="alert alert-info">
                <strong>Jugador:</strong> ${jugador.nombre}<br>
                <strong>Equipo:</strong> ${jugador.equipo.nombre}<br>
                <strong>Posición Actual:</strong>
                <c:choose>
                    <c:when test="${jugador.posicion != null}">
                        ${jugador.posicion.nombre}
                    </c:when>
                    <c:otherwise>
                        Sin posición asignada
                    </c:otherwise>
                </c:choose>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/jugadores">
                <input type="hidden" name="action" value="asignar">
                <input type="hidden" name="idJugador" value="${jugador.idJugador}">

                <div class="form-group">
                    <label for="idPosicion">Nueva Posición:</label>
                    <select class="form-control" id="idPosicion" name="idPosicion" required>
                        <option value="">Seleccione una posición...</option>
                        <c:forEach var="posicion" items="${listaPosiciones}">
                            <option value="${posicion.idPosicion}"
                                ${jugador.posicion != null && posicion.idPosicion == jugador.posicion.idPosicion ? 'selected' : ''}>
                                    ${posicion.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save"></i> Asignar Posición
                </button>
                <a href="${pageContext.request.contextPath}/jugadores" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />