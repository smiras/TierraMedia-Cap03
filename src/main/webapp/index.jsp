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

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
				<c:if test="${errors != null}">
					<ul>
						<c:forEach items="${errors}" var="entry">
							<li><c:out value="${entry.getValue()}"></c:out></li>
						</c:forEach>
					</ul>
				</c:if>
			</p>
		</div>
	</c:if>

	<div class="container">
		<div class="row">
			<div class="col">
				<div>
					<br> <br>
					<h1>Promociones y Atracciones de la Tierra Media</h1>
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productos}" var="productos">
							<tr>
								<td><img alt="" src="${productos.imagen}" class="pequeña"></td>
								<td><strong><c:out value="${productos.nombre}"></c:out></strong></td>
								<td><c:out value="${productos.descripcion}"></c:out></td>
								<td><c:out value="${productos.costo}"></c:out></td>
								<td><c:out value="${productos.duracion}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


			<div class="col-6 col-sm-2">
				
					<br> <br> <br> <br> <br>
					<div>
						<button type="submit" class="btn btn-lg btn-primary"
							onclick="location.href='formLogin.jsp'">Ingresar</button>
					</div>
				
			</div>
		</div>
	</div>


</body>
<jsp:include page="partials/footer.jsp"></jsp:include>
</html>


