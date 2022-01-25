<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- CSS-->
		<!-- <link rel="stylesheet" type="text/css" href="css/main.css"> -->
		<title>Assesment</title>
<link  href="resources/assets/css/main.css"  rel="stylesheet" type="text/css">

	</head>
	<body>
		<section class="material-half-bg" Style="background-color:#b30000;">
			<div class="cover" Style="background-color:white;"></div>
		</section>
		<section class="login-content">
			<div class="logo">
				<h1><img src="resources/assets/images/homo_logo.png" /></h1>
			</div>
			<div class="login-box">
				<form action="LoginValidations" method="POST" class="login-form">
					<h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>SIGN IN</h3>
					<div class="form-group">
						<label class="control-label">USER NAME</label>
						<input type="text" name="username" id="username" placeholder="User Name" autofocus class="form-control" required>
					</div>
					<div class="form-group">
						<label class="control-label">PASSWORD</label>
						<input type="password" name="password" id="password"  placeholder="Password" class="form-control" required>
					</div>
					<div class="form-group btn-container">
						<button class="btn btn-primary btn-block" Style="background-color: #b30000;">SIGN IN <i class="fa fa-sign-in fa-lg"></i></button>
					</div>
					<span style="color:red">${error}</span>
					<span style="color:red">${session_msg}</span>
				</form>
			</div>
		</section>
		
		
	</body>
		

<script>
		history.pushState(null, document.title, location.href);
		window.addEventListener('popstate',function(event){
		history.pushState(null,document.title, location.href);
		});
</script>
	
</html>
