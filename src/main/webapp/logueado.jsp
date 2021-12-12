<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>

<!-- CSS only -->
<link href="assets/stylesheets/style.css" rel="stylesheet"
	type="text/css"></link>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="partials/nav.jsp"></jsp:include>
	<jsp:include page="partials/head.jsp"></jsp:include>


	<div class="container">
		<div class="row">
			<div class="col">
				<div>
					<br> <br>
					<h1>Elige tus aventuras</h1>
				</div>
				<br>
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Costo</th>
							<th>Duracion</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productosposibles}" var="productosposibles">
							<tr>
								<td><img alt="" src="${productosposibles.imagen}" class="pequeña"></td>
								<td><strong><c:out value="${productosposibles.nombre}"></c:out></strong></td>
								<td><c:out value="${productosposibles.descripcion}"></c:out></td>
								<td><c:out value="${productosposibles.costo}"></c:out></td>
								<td><c:out value="${productosposibles.duracion}"></c:out></td>
								<td><button type="submit" class="boton boton5 btn">Comprar</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-6 col-sm-2">
				<form action="login" method="post">
					<br> <br>
					<br> <br> <br> <br> <br> <br> <br>
					
					<h2>Bienvenido!!</h2><br>
					<c:out value="${user.nombre}"></c:out>
					<a><c:out value="${user.presupuesto}"></c:out></a> <br>
					<a><c:out value="${user.tiempoDisponible}"></c:out></a>
					<br> <br>
					<div>
						<button type="submit" class="btn btn-lg btn-primary"
							onclick="location.href='index.jsp'">Salir</button>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
<jsp:include page="partials/footer.jsp"></jsp:include>
</html>


