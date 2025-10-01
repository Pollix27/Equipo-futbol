<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="11.jsp" />

<div class="container mt-5">

    <div class="row mt-4">
        <div class="col-md-12">
            <div class="alert alert-info" role="alert">
                <h4 class="alert-heading">¡Bienvenido!</h4>
                <p>Este sistema te permite gestionar completamente un torneo de fútbol:</p>
                <ul>
                    <li>Crear y administrar equipos</li>
                    <li>Registrar jugadores y asignar posiciones</li>
                    <li>Programar y registrar partidos</li>
                    <li>Anotar goles y ver estadísticas</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="jumbotron text-center">
        <h1 class="lead">⚽ Sistema de Gestión de Torneo de Fútbol ⚽</h1>
        <p class="lead">Administra equipos, jugadores, partidos y goles de forma eficiente</p>
        <hr class="my-4">
        <p> Dirigete al menú para comenzar</p>
    </div>

    <div class="row mt-5">
        <div class="col-md-3">
            <div class="card text-center mb-4">
                <div class="card-body">
                    <i class="fas fa-users fa-3x text-primary mb-3"></i>
                    <h5 class="card-title">Equipos</h5>
                    <p class="card-text">Gestiona los equipos del torneo</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card text-center mb-4">
                <div class="card-body">
                    <i class="fas fa-user fa-3x text-success mb-3"></i>
                    <h5 class="card-title">Jugadores</h5>
                    <p class="card-text">Administra los jugadores</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card text-center mb-4">
                <div class="card-body">
                    <i class="fas fa-futbol fa-3x text-warning mb-3"></i>
                    <h5 class="card-title">Partidos</h5>
                    <p class="card-text">Registra y consulta partidos</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card text-center mb-4">
                <div class="card-body">
                    <i class="fas fa-trophy fa-3x text-danger mb-3"></i>
                    <h5 class="card-title">Goles</h5>
                    <p class="card-text">Registra los goles anotados</p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="99.jsp" />