<!DOCTYPE HTML>
<html>
	<head>
		<title>NGINX - Torneo de Futbol</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="subpage">

		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="index.jsp">NGINX </a></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="index.jsp">Inicio</a></li>
					<li><a href= ${pageContext.request.contextPath}/equipos>Equipos</a></li>
					<li><a href= ${pageContext.request.contextPath}/jugadores>Jugadores</a></li>
                    <li><a href= ${pageContext.request.contextPath}/partidos>Partidos</a></li>
                    <li><a href= ${pageContext.request.contextPath}/goles>Goles</a></li>
				</ul>
			</nav>

		<!-- One -->
			<section id="One" class="wrapper style3">
				<div class="inner">
					<header class="align-center">
                        <p>UACM San Lorenzo Tezonco</p>
						<p>Pollix27 - Kernel-apt - Richard</p>
						<h2> Sistema de Gesti&oacute;n de Torneo de F&uacute;tbol</h2>
					</header>
				</div>
			</section>

		<!-- Two -->
			<section id="two" class="wrapper style2">
				<div class="inner">
					<div class="box">
						<div class="content">

