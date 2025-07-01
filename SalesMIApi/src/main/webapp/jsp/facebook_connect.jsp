<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Example of Twitter Bootstrap 3 Linked List Groups</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style type="text/css">
.list-group {
	width: 200px;
}

.connect {
	margin: 20px;
}

.img {
	margin: 10px;
}

.bs-example {
	margin: 20px;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function loopforProgress() {
			poll(10, 1);
		}
		function poll(x, i) {
			document.getElementById("import").style.visibility = "hidden";
			document.getElementById("progress").style.visibility = "visible";
			setTimeout(function() {
				document.getElementById("progressbar").style.width = x + "%";
				i++;
				if (i < 10) {
					poll(x + 10, i);
				}
			}, 5000);
		}
	</script>
	<div class="image">
		<img alt="" src="../images/head.png" width="100%" height="10%"
			style="padding-bottom: 3%"></img>
	</div>
	<div class="col-xs-offset-1 col-xs-12">
		<ul class="nav nav-tabs">
			<li><a href="home">Home</a></li>
			<li class=active><a href="connect">Connect!</a></li>
			<li><a href="#">Friends</a></li>
			<li class="drop-down"><a href="#" data-toggle="drop-down"
				class="dropdown-toggle">Settings<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#">Profile</a></li>
					<li class="divider"></li>
					<li><a href="#">Logout</a></li>
				</ul></li>
			<div class="cols-xs-offset-10 col-xs-6">
				<p style="margin-top: 1%; margin-left: 60%">Welcome
					${user.lastName} ${user.firstName}</p>
			</div>
		</ul>
		<div class="connect">
			<div class="list-group">
				<a href="facebook_connect" class="list-group-item active"> <span
					class="icon-twitter"></span> Facebook <span class="badge">${facebook}</span>
				</a> <a href="#" class="list-group-item"> <span
					class="fa fa-linkedin"></span> Twitter <span class="badge">${twitter}</span>
				</a> <a href="#" class="list-group-item"> <span
					class="fa fa-facebook"></span> LinkedIn <span class="badge">${linkedin}</span>
				</a>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${not empty facebook}">
			<div class="col-xs-offset-6 col-xs-12">
				<button type="submit" class="btn btn-primary" id="import"
					style="visibility: visible;" onclick="loopforProgress()">Import
					Friends</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-xs-offset-6 col-xs-3">
				<button type="submit" class="btn btn-primary" id="import"
					style="visibility: visible;">Connect With Facebook</button>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
