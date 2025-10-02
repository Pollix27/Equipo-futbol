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
    <h2>Crear Nuevo Equipo</h2>

    <div class="card">
        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/equipos">
                <input type="hidden" name="action" value="crear">

                <div class="form-group">
                    <label for="nombre">Nombre del Equipo:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre"
                           placeholder="Ej: Real Madrid" required>
                </div>

                <button type="submit" class="btn btn-primary">
                    Guardar
                </button>
                <a href="${pageContext.request.contextPath}/equipos" class="btn btn-secondary">
                    Cancelar
                </a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../99.jsp" />