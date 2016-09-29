<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Spring Web MVC project</title>
<link rel="stylesheet"
	href="<c:url value="/public/css/jquery-ui-1.10.3.custom.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/public/css/jquery-ui-1.10.3.custom.min.css"/>" />
<script src="<c:url value="/public/js/jquery-1.9.1.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/public/css/bootstrap.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/public/css/bootstrap.min.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/public/css/bootstrap-theme.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/public/css/bootstrap-theme.min.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/public/css/rich_calendar.css"/>" />
<link rel="stylesheet" href="<c:url value="/public/css/navbar.css"/>" />
<link rel="stylesheet" href="<c:url value="/public/css/demo.css"/>" />
<script src="<c:url value="/public/js/bootstrap.js"/>"></script>
<script src="<c:url value="/public/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/public/js/rich_calendar.js" />"></script>
<script src="<c:url value="/public/js/rc_lang_en.js"/>"></script>

</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
             <div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">SPRING MVC</a>
			</div>
			<div class="navbar-collapse collapse">
				<c:url var="login" value="/login" />
				<form class="navbar-form navbar-right" action="${login}"
					method="POST">
					<div class="form-group">
						<input name="username" type="email" placeholder="Email"
							class="form-control" required>
					</div>
					<div class="form-group">
						<input name="password" placeholder="Password" class="form-control"
							type="password" required>
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
					<a class="btn btn-success" data-toggle="collapse"
						href="#collapseExample" aria-expanded="false"
						aria-controls="collapseExample"> Registro </a>

				</form>
			</div>
			<!--/.navbar-collapse -->


		</div>
	</div>
     <div class="container">
		<div class="collapse" id="collapseExample">
			<c:url var="registro" value="/cliente" />

			<form class="form-horizontal col-sm-offset-3"style="margin-top:70px;" action="${registro}"
				method="POST">

				<div class="form-group">
					<div class="col-md-8">
						<input name="nombre" type="text" value=""
							placeholder="Ingrese un nombre" class="form-control input-md"
							required>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-8">
						<input name="apellido" type="text" class="form-control input-md"
							value="" placeholder="Ingrese un apellido"
							class="form-control input-md" required>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-8">
						<input name="email" type="email" value=""
							placeholder="Ingrese un email" class="form-control input-md"
							required>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-8">
						<input name="clave" type="password" value=""
							placeholder="Ingrese una clave" class="form-control input-md"
							required>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Aceptar</button>
				<button type="button" class="btn btn-secondary">Cancelar</button>
				
			</form>	
		</div>

	</div>
	

</body>
</html>