<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start Test</title>
<style type="text/css">
/* ol.li {
   margin-right:9%;
} */
.inst li {
        width: 400px;
        }
</style>
</head>

<body>
	<center><div class="content-wrapper" style="align:middle;">
		<Marquee><h2 style="color:navy">Welcome to Online Assessment Test !!</h2></Marquee>
		
					<div class="card">
					
						<div class="row">
						
							<div class="col-lg-12">
							<div class="well bs-component">
						<fieldset>
						<form action="getTest" method="Post">
						<legend stlyle="align:left"><i class="fa fa-list-alt" aria-hidden="true"></i>Set of Instructions</legend>
								<!-- <br>
								<br> -->
								<input type="hidden" name="applicationName" id="applicationName" value="1">
								<input type="hidden" name="testName" id="testName" value="${testId}">
								<div class="form-group">
							<div class="form-control" > 
								<label style="color:tomato;font-weight:20px;font-size:20px;"> Please read instructions carefully before starting test</label>
								<ol class="inst">
								<li >Total time allocated will be 30 minutes</li>
								<li >Test will be in Full screen mode only.Do not press ESC key</li>
								<li>All questions must be answered within time line</li>
								<li>Carry your own pen and paper</li>
								<li>Do not close the browser untill test gets completed</li>
								<li>Do not click the "BACK" button of browser during exam</li>
								<li>Keep an eye on the TIMER on top right of page</li>
								<li>click the "SUBMIT"  button only after attempting all questions</li>
							
								</ol>
							 </div> 
									<%-- <label for="inputPassword" class="col-lg-4 control-label"><Span
										style="color: red;">*</Span>Application Name</label>
									<div class="col-lg-11">
										<select name="" id=""
											autoComplete="off" class="form-control text-left" onchange="getTests();" required>
											<option value="">--Select Application--</option>
											<c:forEach var="application" items="${applicationDtoList}">
											<option value="${application.applicationid}"> ${application.applicationName}</option>
											</c:forEach>
										</select><br>
									</div>
									<br> <br> <br> --%>
									
									<!-- <div class="form-group">
										<label for="inputPassword" class="col-lg-3 control-label"><Span
											style="color: red;">*</Span>Test Name</label>
										<div class="col-lg-11">
											<select name="" id=""
                                            autoComplete="off" class="form-control text-left" required onchange="checkTestGiven();">
                                           
                                        </select>
                                            <br>
										</div>
									</div> -->
									
								
									<br> 
									 <div class="form-group"> 
										<div class="col-lg-11 col-lg-offset-1">
											<input type="submit" id="submitid"  style="background-color:#20a5d4;"  value="Start test"class="btn btn-primary" >
										</div>
								 </div> 

								</div>
								</form>
								</fieldset>
							</div>
							
							</div>
							
					
				</div>
			</div>
		
	</div></center>

</body>
<script>


window.onload=function checkTestGiven(){
    var testid =document.getElementById("testName").value;
    if(testid!=0){
        $.ajax({
            type : "GET",
            asyn : false,
            url  : "checkTestGivenBytestid?testid="+testid,
            success  : function(response){
                 // alert(response);
                  if(response=='Exist'){
                	  swal.fire({
              			position : 'top',
              			icon : 'warning',
              			width:'600px',
              			title : 'You Have already attempted this test',
              				 showConfirmButton: false,
              				  timer: 2500
              		});
                        // alert("You Have already written this exam ,So please select other Test Type");
                         $('#submitid').attr("disabled",true);
                  }else{
                         $('#submitid').attr("disabled",false);
                  }
                  
            }
        });
    }
    } 


$(document).ready(function() {
	getTests();
});
function getTests(){
	//var applicationid =document.getElementById("applicationName").value;
	var applicationid=1;
	if(applicationid!=0){
		$('#applicationName').val(applicationid);
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

</html>