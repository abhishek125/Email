<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<c:url var="destinationMessage" value="${pageContext.request.contextPath}/sendmessage" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>your important messages</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/summernote.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/summernote.css" />
</head>
<body>
	<!-- header -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Email</a>
			</div>
			<form class="navbar-form navbar-left">
				<div class="input-group">
					<input type="text" class="form-control" />
					<span class="input-group-addon">
						<i class="fa fa-search"></i>
					</span>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="${pageContext.request.contextPath}/profile">
						<span class="glyphicon glyphicon-user"></span>
						profile
					</a>
				</li>
				<li>
					<form action="${pageContext.request.contextPath}/logout" method="post">
						<button type="submit" class="btn btn-link mylink">
							<span class="glyphicon glyphicon-log-in"> </span>
							logout
						</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</li>
			</ul>
		</div>
	</nav>
	<!-- header ends -->
	<div class="row">
		<div class="mail-box">
			<!-- sidebar -->
			<aside class="sm-side">
				<div class="user-head">
					<a class="inbox-avatar" href="javascript:;">
						<img width="64" height="60" src="${profile.profilePic}">
					</a>
					<div class="user-name">
						<h5>${profile.firstname} ${profile.lastname}</h5>
						<span>${profile.email}</span>
					</div>
				</div>
				<!-- message editor starts -->
				<div class="inbox-body">
					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" id="compose">compose</button>
					<!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button aria-hidden="true" data-dismiss="modal" class="close" type="button">x</button>
									<h4 class="modal-title">Compose</h4>
								</div>
								<div class="modal-body">
									<form role="form" action="${pageContext.request.contextPath}/sendmessage" enctype="multipart/form-data" class="form-horizontal" id="messageForm" method="post">
										<div class="form-group">
											<label class="col-lg-2 control-label">To</label>
											<div class="col-lg-10">
												<input type="text" placeholder="" id="receiverMail" name="receiverId" class="form-control" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">Subject</label>
											<div class="col-lg-10">
												<input type="text" placeholder="" id="subject" name="subject" class="form-control" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">Message</label>
											<div class="col-lg-10">
												<textarea rows="10" cols="30" name="messageBody" class="form-control" id="summernote">
										</textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<span class="btn green fileinput-button">
													<i class="fa fa-plus fa fa-white"></i>
													<span>Attachment</span>
													<input type="file" id="myFile" multiple="multiple" />
												</span>
												<button class="btn btn-send" type="submit">Send</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">Documents</label>
											<div class="col-lg-10">
												<div class="row" id="uploadFileSection"></div>
												<div class="row" id="dynamic"></div>
											</div>
										</div>
									</form>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
				</div>
				<!-- message editor windwo ends -->
				<ul class="inbox-nav inbox-divider">
					<li>
						<a href="${pageContext.request.contextPath}/inbox">
							<i class="fa fa-inbox"></i>
							Inbox
							<span class="label label-danger pull-right">${inboxCount}</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/outbox">
							<i class="fa fa-envelope-o"></i>
							Sent Mail
							<span class="label label-danger pull-right">${outboxCount}</span>
						</a>
					</li>
					<li class="active">
						<a href="${pageContext.request.contextPath}/flagged">
							<i class="fa fa-bookmark-o"></i>
							Important
							<span class="label label-danger pull-right">${flaggedCount}</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/spam">
							<i class=" fa fa-external-link"></i>
							spam
							<span class="label label-danger pull-right">${spamCount}</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/trash">
							<i class=" fa fa-trash-o"></i>
							Trash
							<span class="label label-danger pull-right">${trashCount}</span>
						</a>
					</li>
				</ul>
				<ul class="nav nav-pills nav-stacked labels-info inbox-divider">
					<li>
						<h4>Labels</h4>
					</li>
					<li>
						<a href="#">
							<i class=" fa fa-sign-blank text-danger"></i>
							Work
						</a>
					</li>
					<li>
						<a href="#">
							<i class=" fa fa-sign-blank text-info "></i>
							Family
						</a>
					</li>
				</ul>
				<div class="inbox-body text-center">
					<div class="btn-group">
						<a class="btn mini btn-primary" href="javascript:;">
							<i class="fa fa-plus"></i>
						</a>
					</div>
					<div class="btn-group">
						<a class="btn mini btn-success" href="javascript:;">
							<i class="fa fa-phone"></i>
						</a>
					</div>
					<div class="btn-group">
						<a class="btn mini btn-info" href="javascript:;">
							<i class="fa fa-cog"></i>
						</a>
					</div>
				</div>
			</aside>
			<!-- sidebar ends -->
			<!-- main content -->
			<aside class="lg-side">
				<div class="inbox-head"></div>
				<div class="inbox-body">
					<div class="mail-option">
						<div class="chk-all">
							<input type="checkbox" class="mail-checkbox mail-group-checkbox" id="allornone">
							<div class="btn-group">
								<a data-toggle="dropdown" href="#" class="btn mini all" aria-expanded="false">
									All
									<i class="fa fa-angle-down "></i>
								</a>
								<ul class="dropdown-menu">
									<li>
										<button class="btn btn-link" onclick="none('true')">None</button>
									</li>
									<li>
										<button class="btn btn-link" onclick="read('true')">Read</button>
									</li>
									<li>
										<button class="btn btn-link" onclick="unread('true')">Unread</button>
									</li>
								</ul>
							</div>
						</div>
						<div class="btn-group">
							<a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips">
								<i class=" fa fa-refresh"></i>
							</a>
						</div>
						<div class="btn-group hidden-phone">
							<a data-toggle="dropdown" href="#" class="btn mini blue" aria-expanded="false">
								More
								<i class="fa fa-angle-down "></i>
							</a>
							<ul class="dropdown-menu">
								<li>
									<button class="btn btn-link" onclick="changeStatus('UNFLAGGED')">
										<i class="fa fa-edit"></i>
										not important
									</button>
								</li>
								<li>
									<button class="btn btn-link" onclick="changeStatus('SPAM')">
										<i class="fa fa-ban"></i>
										Spam
									</button>
								</li>
								<li class="divider"></li>
								<li>
									<button class="btn btn-link" onclick="changeStatus('DELETED')">
										<i class="fa fa-trash-o"></i>
										Delete
									</button>
								</li>
							</ul>
						</div>
						<span id="dynamicstatus"></span>
						<ul class="unstyled inbox-pagination">
							<li>
								<span>1-50 of 234</span>
							</li>
							<li>
								<a class="np-btn" href="#">
									<i class="fa fa-angle-left  pagination-left"></i>
								</a>
							</li>
							<li>
								<a class="np-btn" href="#">
									<i class="fa fa-angle-right pagination-right"></i>
								</a>
							</li>
						</ul>
					</div>
					<table class="table table-inbox table-hover">
						<tbody>
							<c:forEach var="object" items="${messages}" varStatus="status">
								<c:set var="myVar" value="message${status}" />
								<fmt:formatDate value="${object.dateSent}" var="myVar" type="date" pattern="MMM dd, yyyy" />
								<tr id="my${object.messageId}" onclick="showit('${object.messageId}')" class="${fn:toLowerCase(object.receiverRead)}">
									<td>
										<input type="hidden" value="${object.messageId}" />
										<input type="checkbox" class="mail-checkbox">
									</td>
									<td>
										<i class="fa fa-star" style="color: #f78a09;"></i>
									</td>
									<td>${profiles[status.index].firstname}${profiles[status.index].lastname}</td>
									<td>${object.subject}</td>
									<td>
										<i class="fa fa-paperclip"></i>
									</td>
									<td>${myVar}</td>
								</tr>
								<tr style="display: none;" id="${object.messageId}">
									<td class="container" style="max-width: 800px;" colspan="6">
										<div class="portrait">
											${object.messageBody}
											<br />
											<c:if test="${object.files ne ''}">
												<h4>ATTACHMENTS</h4>
												<c:set var="my" value="${object.files}" />
												<c:set var="fileParts" value="${fn:split(my,';')}" />
												<c:forEach var="files" items="${fileParts}">
													<a href="${files}">${fn:substringAfter(files, "/uploads/")} </a>
													<br />
												</c:forEach>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</aside>
		</div>
	</div>
</body>
</html>