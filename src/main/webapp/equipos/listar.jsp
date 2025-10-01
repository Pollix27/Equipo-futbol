<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/30/25
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../11.jsp" />

<div class="container mt-4">
    <h2><i class="fas fa-users"></i> Gestión de Equipos</h2>

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
        <a href="${pageContext.request.contextPath}/equipos?action=nuevo" class="btn btn-primary">
            <i class="fas fa-plus"></i> Nuevo Equipo
        </a>
    </div>

    <div class="card">
        <div class="card-body">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="equipo" items="${listaEquipos}">
                    <tr>
                        <td>${equipo.idEquipo}</td>
                        <td>${equipo.nombre}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/equipos?action=editar&id=${equipo.idEquipo}"
                               class="btn btn-sm btn-warning">
                                <i class="fas fa-edit"></i> Editar
                            </a>
                            <a href="${pageContext.request.contextPath}/equipos?action=eliminar&id=${equipo.idEquipo}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Está seguro de eliminar este equipo?')">
                                <i class="fas fa-trash"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty listaEquipos}">
                <div class="alert alert-info">
                    No hay equipos registrados. <a href="${pageContext.request.contextPath}/equipos?action=nuevo">Crear el primero</a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />