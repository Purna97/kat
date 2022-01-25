  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<!-- Side-Nav-->
		  <aside class="main-sidebar hidden-print">
			<section class="sidebar">
				<div class="user-panel"><!-- unknown_user -->
					<div class="pull-left image"><img src="resources/assets/images/unknown_user.png" alt="User Image" class="img-circle"></div>
					<div class="pull-left info">
						<p>${username}</p>
						<p class="designation">${designation}</p>
					</div>
				</div>
				<c:choose>
              <c:when test="${department == '12'}">
				<ul class="sidebar-menu">
					 <li class="active"><a href="dashboard"><i class="fa fa-dashboard"></i><span>Dashboard</span></a></li> 
					 <li class="active"><a href="#"><i class="fa fa-random"></i><span>Assessment</span><i class="fa fa-angle-right"></i></a>
						<ul class="treeview-menu active">
						 <li><a href="candidateReg"><i class="fa fa-circle-o"></i>Candidate Registration</a></li> 
						<li><a href="createCategory"><i class="fa fa-circle-o"></i>Add Application</a></li>
						<li><a href="createTest"><i class="fa fa-circle-o"></i>Add Test</a></li>
						<li><a href="AddQuetions"><i class="fa fa-circle-o"></i>Add Questions</a></li>
						 <li><a href="generateReports"><i class="fa fa-circle-o"></i>Reports</a></li> 
						</ul>
					</li> 
				</ul>
				</c:when>
				<c:otherwise>
				<ul class="sidebar-menu" >
                       <!--  <li><a href="displaytest"><i class="fa fa-circle-o"></i>Start Test</a></li> -->
                        </ul>
				</c:otherwise>
				</c:choose>
			</section>
		</aside>  

</body> 



</html>