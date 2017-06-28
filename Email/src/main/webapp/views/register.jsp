<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register new user</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<!-- Website CSS style -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/registerCss.css">
<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/summernote.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h1 class="title">Email</h1>
					<hr />
				</div>
			</div>
		</div>
		<div class="main-login main-center">
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/signup">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">First Name</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-user fa" aria-hidden="true"></i>
							</span>
							<input type="text" class="form-control" name="firstname" id="firstname" placeholder="Enter your Name" data-toggle="tooltip" onfocus="theFocus(this);" data-placement="top" title="dont use special symbols" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="cols-sm-2 control-label">Lastname</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-users fa" aria-hidden="true"></i>
							</span>
							<input type="text" class="form-control" name="lastname" id="lastname" placeholder="Enter your lastname" data-toggle="tooltip" onfocus="theFocus(this);" data-placement="top" title="dont use spacial symbols" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">Your new Email</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-envelope fa" aria-hidden="true"></i>
							</span>
							<input type="email" class="form-control" name="email" id="email" placeholder="new Email address" data-toggle="tooltip" onfocus="theFocus(this);" data-placement="top" title="enter valid email format" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="cols-sm-2 control-label">Password</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-lock fa-lg" aria-hidden="true"></i>
							</span>
							<input type="password" class="form-control" name="password" id="password" placeholder="Enter your Password" data-toggle="tooltip" onfocus="theFocus(this);" data-placement="top" title="at least 8 characters" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-lock fa-lg" aria-hidden="true"></i>
							</span>
							<input type="password" class="form-control" name="confirm" id="confirm" placeholder="Confirm your Password" data-toggle="tooltip" onfocus="theFocus(this);" data-placement="top" title="same as password" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="cols-sm-2 control-label">Date of birth</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-lock fa-lg" aria-hidden="true"></i>
							</span>
							<input type="date" class="form-control datepicker" name="dob" id="dob" placeholder="yyyy-MM-dd">
						</div>
					</div>
				</div>
				<div class="form-group ">
					<input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="register" value="register" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<div class="login-register">
				<a href="${pageContext.request.contextPath}/">Login</a>
			</div>
		</div>
	</div>
</body>
</html>