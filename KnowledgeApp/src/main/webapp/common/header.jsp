 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<!-- <body class="sidebar-mini fixed">	 -->
<div class="wrapper">
<header class="main-header hidden-print">
			<a href="" class="logo" >
				<img src="resources/assets/images/logo-web.png" />
			</a>
			 <nav class="navbar navbar-static-top"> 
				<!-- <a href="#" data-toggle="offcanvas" class="sidebar-toggle"></a> -->
				<div class="navbar-custom-menu">
					<ul class="top-nav">
						
						<li class="dropdown"><a href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-user fa-lg"></i>&nbsp;&nbsp; <strong>Hello,${username}</strong></a>
							<ul class="dropdown-menu settings-menu">
								<li><a href="logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
							</ul>
						</li>
					</ul>
				</div>
			 </nav> 
		</header>
		




</html> 