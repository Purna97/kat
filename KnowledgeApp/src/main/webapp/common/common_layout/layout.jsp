 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-/W3C/DTD HTML 4.01 Transitional/EN" "http:/www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="utf-8">
    
    <link id="base-style-responsive" href="resources/assets/css/css/style-responsive.css" rel="stylesheet">
    <!-- CSS-->
 
<title>Knowledge Assessment</title>
<link  href="resources/assets/css/css1/main.css"  rel="stylesheet" type="text/css">
<link  href="resources/assets/css/main.css"  rel="stylesheet" type="text/css">
<link type="text/css" href="resources/assets/css/jquery-ui.css" rel="stylesheet" />

<%-- 
<tilesx:useAttribute name="cssitems" />
<c:forEach var="href" items="${cssitems}">
	<link type="text/css" rel="stylesheet" href="${href}" />
</c:forEach>
<tilesx:useAttribute name="jsitems" />
<c:forEach var="src" items="${jsitems}">
<script src="${src}" type="text/javascript"></script>
</c:forEach>
 --%>

<script src="resources/assets/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="resources/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="resources/assets/js/essential-plugins.js" type="text/javascript"></script>
<script src="resources/assets/js/plugins/pace.min.js" type="text/javascript"></script>
<script src="resources/assets/js/main.js" type="text/javascript"></script>
<script src="resources/assets/js/plugins/dataTables.bootstrap.min.js" type="text/javascript"></script> 
<script src="resources/assets/js/plugins/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="resources/assets/js/jquery-ui.min.js" type="text/javascript"></script>
 <script src="resources/assets/js/custom.js" type="text/javascript"></script> 
<script src="resources/assets/css/jquery-ui.css" type="text/javascript"></script> 
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<style type="text/css">
body {

	background-color: #fff;
}
</style>
<body>
	    <tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="left_bar" />
		<tiles:insertAttribute name="body" />
		<%-- <tiles:insertAttribute name="footer" /> --%>
		

	</body>
</html> 