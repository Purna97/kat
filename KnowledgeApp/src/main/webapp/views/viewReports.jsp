<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Knowledge Assessment</title>
</head>
<body>
       <div class="content-wrapper" ><!-- style="margin-top:-760px" -->
		     <div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="row">
							<div class="col-lg-12">
								<div class="well bs-component">
									<fieldset>
										<legend>Assessment Reports </legend>
				                          <div class="form-group">
									<label for="inputPassword" class="col-lg-1 control-label"><Span style="color: red;">*</Span>Application Name</label>
									<div class="col-lg-3">
										<select name="applicationName" id="applicationName"
											autoComplete="off" class="form-control text-left" onchange="getTests();" required>
											<option value="">--Select Application--</option>
											<c:forEach var="application" items="${appList}">
											<option value="${application.applicationid}"> ${application.applicationname}</option>
											</c:forEach>
										</select><br>
									</div>
								</div><br><br><br>
								 <div class="form-group">
								<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Test Name</label>
									  <div class="col-lg-3">
									       <select name="test" id="testName" autoComplete="off" class="form-control text-left" required>					             							               
										      </select><!-- <br> -->        
									  </div>
								 </div><br><br>
								 <div class="form-group">
								    <div class="col-lg-6">
								     <button onclick="generateReport();" class="btn btn-primary">Generate</button>
								    </div>
								 </div>
								   </fieldset>
				                  </div> 		
			   		           </div>
							</div>
						  </div>
                       </div>
				 </div>
				 
				 <div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">				
							<table id="sampleTable" class="table table-hover table-bordered table-striped" style="text-align:center;">
								<thead style="background-color:lightblue">
							 		<tr>
							 		    <th style="text-align:center;">Emp No</th>
							 		    <th style="text-align:center;">Employee</th> 	
										<th style="text-align:center;">Test Start Date</th> 	
										<th style="text-align:center;">Test End Date</th> 	
										<th style="text-align:center;">Result</th> 									
									 </tr>
							    </thead>
								<tbody>	
									<c:forEach var="anslist" items="${qadtoList}">							
						         	   <tr>
						         	    <td class="left">${anslist.applicationName}</td>
						         	    <td class="left">${anslist.question}</td>	
						         	    <c:forEach var="ans" items="${anslist.answer}">
						         	    	<td class="left">${ans}</td>	
						         	    </c:forEach> 
						         	   </tr>	
								 	</c:forEach> 
								 		 					 							     																												   
								</tbody>
							 </table>												
						 </div>
					</div>
				  </div>			  						  
			   </div>			  		  
			</div>	 
   <script src="/resourses/assets/js/plugins/dataTables.bootstrap.min.js" type="text/javascript"></script> 
   <script type="text/javascript">$('#sampleTable').DataTable();</script> 
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
   <script>
   function generateReport(){
	   var applicationid=document.getElementById("applicationName").value;
	   var testid=document.getElementById("testName").value
	   
	   $.ajax({
	        type : "POST",
	        asyn : false,
	        url  : "generateAssessmentReport?applicationid="+applicationid+"&testid="+testid,
	       
	        success  : function(response){
	        	alert(response);
	        },
	   error:function(response){
		   alert("error--"+response);
	   }
	        
	    });
   }
   
   
   </script>
   
   
   
   
<script>
function getTests(){
var applicationid =document.getElementById("applicationName").value;
   
    $.ajax({
        type : "GET",
        asyn : false,
        url  : "getTestByApplicationid?applicationid="+applicationid,
       
        success  : function(response){
        	$('#testName').empty();
            $('#testName').append($('<option/>').attr("value","").text("Select"));
            for (var i = 0, len = response.length; i < len; i=i+2) 
            {
                $('#testName').append($('<option/>').attr("value", response[i]).text(response[i+1]));
            }  
        }
        
    });
}


</script>
</body>

 
</html>