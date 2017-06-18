<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>edit your profile</title>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/resources/js/summernote.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/js/profile.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/js/main.js"> </script>
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/profile.css" />
<link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/resources/css/summernote.css" />

</head>
<body>

<!-- header starts -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Email</a>
    </div>
    
   <form class="navbar-form navbar-left">
 <div class="input-group">
    <input type="text" class="form-control"/>
    <span class="input-group-addon">
        <i class="fa fa-search"></i>
    </span>
</div>
</form>
<%-- <c:url value="${pageContext.request.contextPath}/logout" var="logout" /> --%>
<ul class="nav navbar-nav navbar-right">

<li><a href="${pageContext.request.contextPath}/profile"><span class="glyphicon glyphicon-user"></span> profile </a></li>
      <li><%-- <a href="${logout}" > <span class="glyphicon glyphicon-log-in"></span> Logout </a> --%>
      
							<form action="${pageContext.request.contextPath}/logout" method="post">
			<button type="submit" class="btn btn-link mylink" >
			<span class="glyphicon glyphicon-log-in" > </span> logout</button>
			
			
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
		<a class="inbox-avatar" href="javascript:;"> <img width="64"
			height="60"
			src="${profile.profilePic}">
		</a>
		<div class="user-name">
			<h5>
				${profile.firstname} ${profile.lastname}
			</h5>
			<span> ${profile.email} </span>
		</div>

	</div>
	<!-- message editor starts -->
	<div class="inbox-body">
		<button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal" id="compose">compose</button>
		<!-- Modal -->
		<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"
			tabindex="-1" id="myModal" class="modal fade" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button aria-hidden="true" data-dismiss="modal" class="close"
							type="button">x</button>
						<h4 class="modal-title">Compose</h4><span id="dynamic"></span>
					</div>
					<div class="modal-body">
						<form  role="form" action="${pageContext.request.contextPath}/sendmessage" enctype="multipart/form-data" class="form-horizontal"
							 id="messageForm" method="post">
							<div class="form-group">
								<label class="col-lg-2 control-label">To</label>
								<div class="col-lg-10">
									<input type="text" placeholder="" id="receiverMail"
										name="receiverId" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">Subject</label>
								<div class="col-lg-10">
									<input type="text" placeholder="" id="subject" name="subject"
										class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">Message</label>
								<div class="col-lg-10">
									<textarea rows="10" cols="30" 
										name="messageBody" class="form-control" id="summernote">
										</textarea>
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<span class="btn green fileinput-button"> <i
										class="fa fa-plus fa fa-white"></i> <span>Attachment</span> 
										<input	type="file" id="myFile" multiple="multiple" />
									</span>
									<button class="btn btn-send" type="submit">Send</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">Documents</label>
								<div class="col-lg-10">
									<div class="row" id="uploadFileSection">
										
									</div>
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
		<li class="active"><a href="${pageContext.request.contextPath}/inbox"><i class="fa fa-inbox"></i> Inbox <span class="label label-danger pull-right">${inboxCount}</span></a></li>
		<li><a href="${pageContext.request.contextPath}/outbox"><i class="fa fa-envelope-o"></i> Sent Mail <span class="label label-danger pull-right">${outboxCount}</span></a></li>
		<li><a href="${pageContext.request.contextPath}/flagged"><i class="fa fa-bookmark-o"></i> Important <span class="label label-danger pull-right">${flaggedCount}</span></a></li>
		<li><a href="${pageContext.request.contextPath}/spam"><i class=" fa fa-external-link"></i> spam <span	class="label label-danger pull-right">${spamCount}</span></a></li>
		<li><a href="${pageContext.request.contextPath}/trash"><i class=" fa fa-trash-o"></i> Trash <span class="label label-danger pull-right">${trashCount}</span></a></li>
	</ul>
	<ul class="nav nav-pills nav-stacked labels-info inbox-divider">
		<li>
			<h4>Labels</h4>
		</li>
		<li><a href="#"> <i class=" fa fa-sign-blank text-danger"></i>
				Work
		</a></li>
		<li><a href="#"> <i class=" fa fa-sign-blank text-info "></i>
				Family
		</a></li>
	</ul>
	

	<div class="inbox-body text-center">
		<div class="btn-group">
			<a class="btn mini btn-primary" href="javascript:;"> <i
				class="fa fa-plus"></i>
			</a>
		</div>
		<div class="btn-group">
			<a class="btn mini btn-success" href="javascript:;"> <i
				class="fa fa-phone"></i>
			</a>
		</div>
		<div class="btn-group">
			<a class="btn mini btn-info" href="javascript:;"> <i
				class="fa fa-cog"></i>
			</a>
		</div>
	</div>

</aside>
<!-- sidebar ends -->

	<aside>
	<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/profileupdate?${_csrf.parameterName}=${_csrf.token}"
        enctype="multipart/form-data">
        
        <fmt:formatDate value="${profile.dob}" var="formattedDate" 
                type="date" pattern="yyyy-MM-dd" />
                
                
    <!-- left column -->
    <div class="col-md-4 col-sm-6 col-xs-12">
      <div class="text-center">
        <img src="${profile.profilePic}" class="avatar img-circle img-thumbnail" alt="avatar"/>
        <h6>Upload a different photo...</h6>
                
                <div class="center-block">
                <div class="custom1">
                 <div class="col-lg-12 col-md-12 col-sm-12">
                    <input type="file" id="main-input" class="form-control form-input form-style-base" name="file" />
                    <h4 id="fake-btn" class="form-input fake-styled-btn text-center truncate"><span class="margin"> Choose File</span></h4>
                </div>                   
                </div>
                </div>

      
      </div>
    </div>
    <!-- edit form column -->
    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
      <div id="updateinfo" class="alert alert-info alert-dismissable">
        ${updated}
      </div>
      <h3>Personal info</h3>
      
        <div class="form-group">
          <label class="col-lg-3 control-label">First name:</label>
          <div class="col-lg-8">
            <input class="form-control" value="${profile.firstname}" name="firstname" type="text" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Last name:</label>
          <div class="col-lg-8">
            <input class="form-control" value="${profile.lastname}" type="text" name="lastname" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Company:</label>
          <div class="col-lg-8">
            <input class="form-control" value="${profile.company}" type="text" name="company" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Date of birth:</label>
          <div class="col-lg-8">
            <input class="form-control" value="${formattedDate}" type="date" placeholder="yyyy-MM-dd" name="dob" />
          </div>
        </div>
        
         <!-- <div class="form-group">
          <label class="col-lg-3 control-label">Time Zone:</label>
          <div class="col-lg-8">
            <div class="ui-select">
              <select id="user_time_zone" class="form-control">
                <option value="Central Time (US &amp; Canada)" selected="selected">(GMT-06:00) Central Time (US &amp; Canada)</option>
              </select>
            </div>
          </div>
        </div> --> 
        
        <div class="form-group">
          <label class="col-lg-3 control-label">new password:</label>
          <div class="col-lg-8">
            <input class="form-control"  placeholder="***********" type="password" name="password" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">confirm password:</label>
          <div class="col-lg-8">
            <input class="form-control" placeholder="***********" type="password" name="confirm" />
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-md-8">
            <input class="btn btn-primary" value="Save Changes" type="submit" />
            <span></span>
            <input class="btn btn-default" value="Cancel" type="reset">
          </div>
        </div>
        
        	
        	</div>
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
      </aside>
      
      
      
      
    </div>
  </div>
          
</body>
</html>