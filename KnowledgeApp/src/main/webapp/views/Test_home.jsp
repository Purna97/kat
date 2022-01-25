<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    
    <title>Knowledge Assessment</title>
  
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
</style>
<body >	 
		
		<div class="content-wrapper" >
			<div class="page-title">
				<div>
					<h1><i class="fa fa-edit"></i>Add Application</h1>
			   </div>
			      <div>
					<ul class="breadcrumb">
						<li><i class="fa fa-home fa-lg"></i></li>
						<li>Add Application</li>							
					</ul>
				</div>
			</div>
		         <form action="AddCategory" method="Get">
		         <div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="row">
							<div class="col-lg-12">
		            
								<br><br> 
		                       <div class="form-group">
									<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Department Name</label>
									    <div class="col-lg-11">
											   <select name="departments" id="departments" style="width:45%;" autoComplete="off" class="form-control text-left" required>
										            <!--   <option value="">--Select Department --</option>		 -->
										              <c:forEach items="${alldepartments}" var="alldepartments">			              
										               <option value="${alldepartments.getDept_id()}">${alldepartments.getDept_name()}</option>										             
										              </c:forEach>							               
										       </select><br>        
									    </div><br> <br> <br>		                          			
						<div class="form-group">
									<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Application Name</label>
									    <div class="col-lg-11">
							      <input type="text" id="application" name="application" class="form-control" style="width: 360px; height:30px; " pattern="[a-zA-Z0-9][a-zA-Z0-9\s]*" minlength="2" title="special characters are not allowed." required/>
				       		     
					          </div>
				       </div><br> <br>
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
		   </form>
		   <div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">				
							<table id="sampleTable" class="table table-hover table-bordered" style="text-align:center;">
								<thead style="background-color:lavender;">
							 		<tr>
							 		    <!-- <th style="text-align:center;" style="visibility:none">testid</th> -->
							 		    <th style="text-align:center;">Application Name</th> 
										<!-- <th style="text-align:center;">End Date</th>  -->									
									 </tr>
							    </thead>
								<tbody>	
								  <c:forEach var="testList" items="${applicationList}" >									
						         	<tr>
						         	 	<%-- <td class="left" style="visibility:none">${testList.testid}</td> --%>	
						         	    <td class="left">${testList.applicationname}</td>			
								 	</tr>	
								  </c:forEach>									 				 							     																												   
								</tbody>
							 </table>												
						 </div>
					</div>
				  </div>			  						  
			   </div>			  		  	 	  
		</div>	
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
     <script type="text/javascript">$('#sampleTable').DataTable();</script> 
     <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    </body>
</html>