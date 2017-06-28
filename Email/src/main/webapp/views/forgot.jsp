<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>recover password</title>
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/forgotCss.css" />
</head>
<body>
	<div id="div-forms">
		<form id="lost-form">
			<div class="modal-body">
				<div id="div-lost-msg">
					<div id="text-lost-msg">Type your e-mail.</div>
				</div>
				<input id="lost_email" class="form-control" type="text" placeholder="enter your E-Mail" required>
			</div>
			<div class="modal-footer">
				<div>
					<button type="submit" class="btn btn-primary btn-lg btn-block">Send</button>
				</div>
				<div>
					<button id="lost_login_btn" type="button" class="btn btn-link">
						<a href="${pageContext.request.contextPath}/register"> Log in </a>
					</button>
					<button id="lost_register_btn" type="button" class="btn btn-link">
						<a href="${pageContext.request.contextPath}/register"> register </a>
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>