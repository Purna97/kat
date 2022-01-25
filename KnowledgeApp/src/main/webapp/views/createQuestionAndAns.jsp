<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <!-- <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta HTTP-EQUIV="Pragma" content="no-cache">  
    <meta HTTP-EQUIV="Expires" content="-1"> 
    CSS
    <link rel="stylesheet" type="text/css" href="css/main.css"> -->
    <title>Knowledge Assessment</title>
  
</head>
<!-- <style>
.form-control {
    display: block;
    width: 40%;
    padding: 5px 12px;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.44;
    }
</style> style="margin-top:-760px"-->
<body >	 

		<div class="content-wrapper" >
			<div class="page-title">
				<div>
					<h1><i class="fa fa-edit"></i>Create Question&Ans</h1>
			   </div>
			      <div>
					<ul class="breadcrumb">
						<li><i class="fa fa-home fa-lg"></i>Assessment</li>
						<li>Create Question&Ans</li>							
					</ul>
				</div>
			</div>
		       <form action="saveQuestion" method="post" onsubmit="return validateForm();">
		         <div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="row">
							<div class="col-lg-12">
								<!-- <div class="well bs-component">  -->
								<div class="form-group" style="background-color:red">
								<span id="checkQuest" >${checkQuest_msg}</span>
								</div>
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
									       <%--  <option value="">--Select Test--</option>
									             <c:forEach var="test" items="${testlist}">
										            <option value="${test.testid}">${test.testname}</option>
										         </c:forEach>	 --%>						             							               
										      </select><!-- <br> -->        
									  </div>
								 </div><br><br>
		                              <div class="form-group"><br>
												<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span> Add Question</label>
												<div class="col-lg-6">
													<!-- <input id="Question" type="text" pattern="^[a-zA-Z\s]+$" data-minlength="3" title="only alphabets are allowed ie..abc,minimum 3 charcters should be entered" autocomplete="off" placeholder="Task Name" name="Question" onchange="checklen()" class="form-control"  required> -->
												<textarea id="quest" rows="2" name="quest" class="form-control" required></textarea>
												<span id="quest_msg"></span>
												</div>
											</div><br><br><br>
											<div class="form-group"><br>
												<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Option1</label>
												<div class="col-lg-4">
													<input id="option1"  name="option1" type="text" data-minlength="3" title="only alphabets are allowed ie..abc,minimum 3 charcters should be entered" autocomplete="off" placeholder="Answer"   class="form-control"  required>
												<!--  pattern="^[a-zA-Z\s]+$" onclick="Qcheck();"-->
												</div>
												<input type="checkbox" value="a" id="c_option1" name="c_option" />
											</div><br>
											 <div class="form-group"><br>
												<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Option2</label>
												<div class="col-lg-4">
													<input id="option2" name="option2"  type="text"  data-minlength="3" title="only alphabets are allowed ie..abc,minimum 3 charcters should be entered" autocomplete="off" placeholder="Answer"   class="form-control"  required>
												
												</div>
												<input type="checkbox"  value="b" id="c_option2" name="c_option"/>
											</div><br>
											<div class="form-group"><br>
												<label for="inputPassword" class="col-lg-1 control-label"><Span style="color:red;">*</Span>Option3</label>
												<div class="col-lg-4">
													<input id="option3" name="option3"  type="text"  data-minlength="3" title="only alphabets are allowed ie..abc,minimum 3 charcters should be entered" autocomplete="off" placeholder="Answer"  class="form-control"  required>
												    
												</div>
												<input type="checkbox" value="c"  id="c_option3" name="c_option"/>
											</div>
											<br><br>
											 <div class="form-group">									
												<div class="col-lg-11 col-lg-offset-1">													
													<button type="submit" class="btn btn-primary">Submit</button>&nbsp&nbsp&nbsp&nbsp
													<button type="reset" class="btn btn-default"> Reset</button>
												<!-- <a href="addTasks.jsp" class="btn btn-default" style="padding:7px;"> Reset </a> -->
												</div>										
											</div>	
				       			
				                       </div>
				       			 </div>
					        </div>
				       </div>			  						  
			        </div>	
			        </form>	
			   <!-- </div> -->
			   	
			  
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">				
							<table id="sampleTable" class="table table-hover table-bordered table-striped" style="text-align:center;">
								<thead style="background-color:lavender;">
							 		<tr>
							 		    <th style="text-align:center;">Application</th>
							 		    <th style="text-align:center;">Question</th> 	
										<th style="text-align:center;">Option 1</th> 	
										<th style="text-align:center;">Option 2</th> 	
										<th style="text-align:center;">Option 3</th>
										<th style="text-align:center;">Answer</th> 									
									 </tr>
							    </thead>
								<tbody>	
										<c:forEach var="anslist" items="${qadtoList}">							
						         	<tr>
						         		
						         	    <td class="left">${anslist.applicationName}</td>
						         	    <td class="left">${anslist.question}</td>	
						         	    <%-- <td class="left">${anslist.answer}</td>	 --%>
						         	    <c:forEach var="ans" items="${anslist.answer}">
						         	    	<td class="left">${ans}</td>	
						         	    </c:forEach> 
						         	    <td class="left">${anslist.correctAnsOption}</td>
						            </tr>	
								 		 </c:forEach>       						 				 							     																												   
								</tbody>
							 </table>												
						 </div>
					</div>
				  </div>			  						  
			   </div>			  		  	 	  
		</div>	
    </div>
<script src="resources/assets/js/plugins/dataTables.bootstrap.min.js" type="text/javascript"></script> 
   <script type="text/javascript">$('#sampleTable').DataTable();</script> 
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
 
  
<script>
function getTests(){
var applicationid =document.getElementById("applicationName").value;
   if(applicationid!=0){
    $.ajax({
        type : "GET",
        asyn : false,
        url  : "getTestByApplicationid?applicationid="+applicationid,
        success  : function(response){
        	$('#testName').empty();
        	$('#testName').append($('<option/>').attr("value","").text("Select Test Name"));   
            for (var i = 0; i < response.length; i++) 
            {
                $('#testName').append($('<option/>').attr("value", response[i].testid).text(response[i].testname));
                
            } 
        	/* $('#testName').empty();
            $('#testName').append($('<option/>').attr("value","").text("Select"));
            for (var i = 0, len = response.length; i < len; i=i+2) 
            {
                $('#testName').append($('<option/>').attr("value", response[i]).text(response[i+1]));
            }   */
        }
     });
   }
}


</script>
   
  <script type="text/javascript">
  function validateForm(){
	  var valid=false;
	  var crct_ans;
	  var quest=document.getElementById("quest").value;
	  var o1=document.getElementById("c_option1").checked;
	  var o2=document.getElementById("c_option2").checked;
	  var o3=document.getElementById("c_option3").checked;
	   if(quest==null||quest==""){
		   alert("please, Enter Question!");
		  return false;
	  }
	  else{
		  
	 
	  if(o1==true&&o2==true&&o3==true){
		  alert("Pick only one correct answer!");
		  return valid;
	  }
	  else if(o1==true&&o2==true){
		  alert("Pick only one correct answer!");
		  return valid;
	  }
	  else if(o2==true&&o3==true){
		  alert("Pick only one correct answer!");
		  return valid;
	  }
	  else if(o1==true&&o3==true){
		  alert("Pick only one correct answer!");
		  return valid;
	  }
	  
	  else if(o1==true){
		  crct_ans=document.getElementById("option1").value;
		  alert("selected Answer is:"+crct_ans);
		  valid=true;
		  return valid;
	 }
	 else if(o2==true){
		  crct_ans=document.getElementById("option2").value;
		  alert("selected Answer is:"+crct_ans);
		  valid=true;
		  return valid;
	 }
	 else if(o3==true){
		  crct_ans=document.getElementById("option3").value;
		  alert("selected Answer is:"+crct_ans);
		  valid=true;
		  return valid;
	 }
	 else{
		 alert("please select one correct answer!!");
		 return valid;
	 } 
	
	  }
  }
  
  
 </script>
    </body>
</html>