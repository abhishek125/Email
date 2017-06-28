<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login to email</title>
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/loginCss.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
</head>
<body>
	<div class="container">
		<c:if test="${message != null}">
			<div class="alert alert-info" role="alert">${message}</div>
		</c:if>
		<c:if test="${param.error != null}">
			<div class="alert alert-info" id="error">username or password was incorrect please try again</div>
		</c:if>
		<div class="card card-container">
			<img id="profile-img" class="profile-img-card" src="${pageContext.request.contextPath}/resources/images/avatar.png" />
			<p id="profile-name" class="profile-name-card"></p>
			<form name="f" class="form-signin" method="post" action="${pageContext.request.contextPath}/">
				<span id="reauth-email" class="reauth-email"></span>
				<input type="text" id="inputEmail" name="username" class="form-control" placeholder="Email address" required autofocus>
				<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required />
				<input class="btn btn-lg btn-primary btn-block btn-signin" name="submit" type="submit" value="sign in" />
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<!-- form finish -->
			<a href="${pageContext.request.contextPath}/forgot" class="forgot-password"> Forgot the password? </a>
			<br />
			<a href="${pageContext.request.contextPath}/register" class="forgot-password"> register new user? </a>
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->
</body>
</html>
