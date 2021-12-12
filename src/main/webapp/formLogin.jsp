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
		<form action="logueado" method="post">
			<br>
			<div>
				<label for="username" class="form-label">Usuario</label> <input
					class="form-control" name="username">
			</div>

			<div>
				<label for="password" class="form-label">Contraseña</label> <input
					type="password" class="form-control" name="password">
			</div>
			<br>

			<div>
				<button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
			</div>
		</form>
	</div>
	<br>

</body>
<jsp:include page="partials/footer.jsp"></jsp:include>
</html>