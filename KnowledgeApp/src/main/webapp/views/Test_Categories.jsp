<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    
    <title>Assesment</title>
  
</head>
<style>
.form-control {
    display: block;
    width: 40%;
    padding: 5px 12px;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.44;
    }
    a {
    color: black;
    text-decoration: none;
}
</style>
<body >	 
		
		<div class="content-wrapper" >
			<div class="page-title">
				<div>
					<h1><i class="fa fa-edit"></i>Add Test</h1>
			   </div>
			      <div>
					<ul class="breadcrumb">
						<li><i class="fa fa-home fa-lg"></i></li>
						<li>Add Test</li>							
					</ul>
				</div>
			</div>
		         <form action="Add_Test" method="GET">
		         <div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="row">
							<div class="col-lg-12">
		                   <br><br> 
		                       <div class="form-group">
									<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Application Name</label>
									    <div class="col-lg-11">
											   <select name="applicationName" id="applicationName" autoComplete="off" class="form-control text-left" required >
										                    <option value="">--Select --</option>					              
										                <c:forEach var="application" items="${applicationDtoList}">
											                <option value="${application.getApplicationid()}"> ${application.getApplicationname()}</option>
											            </c:forEach>								               
										        </select><br>        
									    </div><br> <br> <br>		                          			
						<div class="form-group">
									<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Test Name</label>
									    <div class="col-lg-11">
							      <input type="text" id="Test_Name" name="testName" class="form-control" style="width: 360px; height:30px; " pattern="[a-zA-Z0-9][a-zA-Z0-9\s]*" minlength="2" title="special characters are not allowed." required/>
				       		     
					          </div>
				       </div><br><br> <br>
				       <div class="form-group">
									<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Select Role</label>
									    <div class="col-lg-11">
											   <select name="roleName" id="roleName" autoComplete="off" class="form-control text-left" required >
										                    <!-- <option value="">--Select --</option> -->					              
										                <c:forEach var="roleNames" items="${roleNames}">
											                <option value="${roleNames.role_id}"> ${roleNames.role_name}</option>
											            </c:forEach>								               
										        </select><br>        
									    </div><br> <br> <br>
				  <div class="form-group"> <br> <br>
									
									    <div class="col-lg-11">&nbsp&nbsp&nbsp
									    
							      <input type="text" id="datepicker1"  name="fromDate" style=" width: 155px; height:30px;" class="mytext"  required/>
							      <label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Start Date</label><Span style="color:red;">*</Span>End Date
							   
				       		        <input type="text" id="datepicker2"  name="toDate" style="width: 155px; height:30px;" class="mytext"  required/>
				       		        
					          </div>
				       </div><br>
				       <br>
				       <div class="form-group">									
												<div class="col-lg-11 col-lg-offset-1">													
												<button type="submit" class="btn btn-primary">Submit</button>&nbsp&nbsp&nbsp&nbsp
												<button type="reset" class="btn btn-default"> Reset</button>
												</div>										
											</div>	
				       			
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
							<table id="sampleTable" class="table table-hover table-bordered" style="text-align:center;">
								<thead style="background-color:lavender;">
							 		<tr>
							 		    <!-- <th style="text-align:center;" style="visibility:none">testid</th> -->
							 		    <th style="text-align:center;">Application Name</th> 
							 		    <th style="text-align:center;">Test Name</th>
							 		    <th style="text-align:center;">Start Date</th> 												
										<th style="text-align:center;">End Date</th> 									
									 </tr>
							    </thead>
								<tbody>	
								  <c:forEach var="testList" items="${testList}" >									
						         	<tr>
						         	 	<%-- <td class="left" style="visibility:none">${testList.testid}</td> --%>	
						         	    <td class="left">${testList.applicationName}</td>			
						         	    <td class="left">${testList.testName}</td>		         	
							     		<td  class="left" id="categoryName">${testList.startDate} </td>	
                                    	<td class="left">${testList.endDate}
                                        </td>                                                                                                                                                    
								 	</tr>	
								  </c:forEach>									 				 							     																												   
								</tbody>
							 </table>												
						 </div>
					</div>
				  </div>			  						  
			   </div>	
		   </form>		  		  	 	  
		</div>	
   <script src="resources/assets/js/plugins/dataTables.bootstrap.min.js" type="text/javascript"></script> 
   <script type="text/javascript">$('#sampleTable').DataTable();</script> 
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
    <script src="resources/assets/css/jquery-ui.css" type="text/javascript"></script> 
  
  <script language="JavaScript" type="text/javascript">
		$(document).ready(function(){
			var date = new Date();
			date.setDate(date.getDate());
			$("#datepicker1").datepicker({
				//dateFormat:"dd/mm/yy" ,
				minDate: date,
				changeMonth:true,
				changeYear:true,
				onSelect: function(selected) {
				  $("#datepicker2").datepicker("option","minDate", selected)
				  }
			});
			$("#datepicker2").datepicker({
				//dateFormat:"dd/mm/yy" ,
				changeMonth:true,
				changeYear:true,
				onSelect: function(selected) {
				   $("#datepicker1").datepicker("option","maxDate", selected)
				}
			});
		});
		</script> 

<script type="text/javascript">        
            function getConfirmation(){
               var retVal = confirm("Do you want to continue ?");
               var valid=true;
               if( retVal == true ){                      
                  return valid;
               }
               else{
            	   valid=false;
            	   window.location.href='Wiki_addCategories.jsp';
                  return valid;
               }
               return valid;
            }        
</script>
  <script type="text/javascript">
    function onCLickButton(name,usertype,type) {
    	var msg="Edit Values Appended to Add Category fields";
    	   alert(msg);   	  
           document.getElementById("text").value=name;
           document.getElementById("Categories").value=type;
           document.getElementById("Usertype").value=usertype;                 
    }
</script>
    </body>
</html>