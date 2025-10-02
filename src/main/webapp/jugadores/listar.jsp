<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2>Gestión de Jugadores</h2>

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
        <a href="${pageContext.request.contextPath}/jugadores?action=nuevo" class="btn btn-primary">
            Nuevo Jugador
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Fecha Nacimiento</th>
                    <th>Equipo</th>
                    <th>Posición</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="jugador" items="${listaJugadores}">
                    <tr>
                        <td>${jugador.idJugador}</td>
                        <td>${jugador.nombre}</td>
                        <td>${jugador.fechaNacimiento != null ? jugador.fechaNacimiento : 'N/A'}</td>
                        <td>${jugador.equipo.nombre}</td>
                        <td>
                            <c:choose>
                                <c:when test="${jugador.posicion != null}">
                                    <span class="badge badge-info">${jugador.posicion.nombre}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-secondary">Sin posición</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/jugadores?action=editar&id=${jugador.idJugador}"
                               class="btn btn-sm btn-warning">
                                Editar
                            </a>
                            <a href="${pageContext.request.contextPath}/jugadores?action=asignarPosicion&id=${jugador.idJugador}"
                               class="btn btn-sm btn-info">
                                Posición
                            </a>
                            <a href="${pageContext.request.contextPath}/jugadores?action=eliminar&id=${jugador.idJugador}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Está seguro de eliminar este jugador?')">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty listaJugadores}">
                <div class="alert alert-info">
                    No hay jugadores registrados. <a href="${pageContext.request.contextPath}/jugadores?action=nuevo">Crear el primero</a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />