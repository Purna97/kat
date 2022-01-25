<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start Test</title>
</head>
<body>
	<center>
		<div class="content-wrapper">
			<!-- style="margin-top:-760px" -->

			<div class="row">
				<div class="col-md-12">
					<div class="page-title">
						<div>
							<h1>
								<i class="fa fa-edit"></i>Reports
							</h1>
						</div>
						<div>
							<ul class="breadcrumb">
								<li><i class="fa fa-home fa-lg"></i>Reports</li>
								<li>Test Attended users</li>
							</ul>
						</div>
					</div>
					<div class="card">
						<div class="row">
							<div class="col-lg-12">

								<div class="well bs-component">
									<fieldset>
										<form action="getReportData">
											<div class="form-group">
												<label for="inputPassword" class="col-lg-2 control-label"><Span
													style="color: red;">*</Span>Application Name</label>
												<div class="col-lg-4">
													<select name="applicationid" id="applicationName"
														autoComplete="off" class="form-control text-left"
														onchange="getTests();" required>
														<option value="">--Select Application--</option>
														<c:forEach var="application" items="${appList}">
															<option value="${application.applicationid}">
																${application.applicationname}</option>
														</c:forEach>
													</select><br>
												</div>
											</div>
											<br> <br> <br>
											<div class="form-group">
												<label for="inputPassword" class="col-lg-2 control-label"><Span
													style="color: red;">*</Span>Test Name</label>
												<div class="col-lg-4">
													<select name="testid" id="testName" autoComplete="off"
														class="form-control text-left" required>

													</select> <br>
												</div>
											</div>
											<br> <br>
											<div class="form-group">
												<div class="col-lg-6 col-lg-offset-1">
													<!-- <button type="button" value="Get Report" class="btn btn-primary" ">Generate</button> -->
													<!-- onclick="showGrid(); -->
													<input type="submit" class="btn btn-primary"
														value="Generate" /> &nbsp&nbsp <a href="generateReports"
														class="btn btn-default">back</a>
												</div>
											</div>
										</form>
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
							<table id="sampleTable" class="table table-hover table-bordered"
								style="text-align: center;">
								<thead style="background-color: lavender;">
									<tr>
										<th style="text-align: center;">Employee Number</th>
										<th style="text-align: center;">Employee Name</th>
										<th style="text-align: center;">Application Name</th>
										<th style="text-align: center;">Test Name</th>
										<th style="text-align: center;">Score</th>
										<th style="text-align: center;">Test Result</th>
									</tr>
								</thead>
								<tbody>
									<input type="text" style="display: none" id="tbl_report"
										name="tbl_report" value="${listSize}" />
									<c:forEach var="attList" items="${reportlist}">

										<tr>
											<td>${attList.empNumber }</td>
											<td>${attList.empName }</td>
											<td>${attList.applicationName}</td>
											<td>${attList.testName }</td>
											<td>${attList.score }</td>
											<c:if test="${attList.status=='pass'}">
												<td style="color: green">${attList.status }</td>
											</c:if>
											<c:if test="${attList.status=='fail'}">
												<td style="color: red">${attList.status }</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- ?applicationid=${applicationId}&testid=${testId} -->
						<c:if test="${reportlist.size() gt 0}">
						<center>
							<a onclick="return validate();" href="downloadTestReport"
								id="export_excel_button" class="btn btn-default submit-button"
								style="background-color: forestgreen;">Download</a>
						</center>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-info">
						<strong>Total Pass: ${passcount }</strong>

					</div>
					<div class="alert alert-danger">
						<strong>Total Fail: ${failcount }</strong>
					</div>
				</div>
			</div>

		</div>

	</center>


	<script src="resources/assets/js/plugins/dataTables.bootstrap.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">$('#sampleTable').DataTable();</script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script>
   function validate(){
	 
	   var valid=true;
	   var tbl_report =document.getElementById("tbl_report").value;
	   if(tbl_report==null||tbl_report==""){
		alert("Please, generate report before download!");
		valid=false;
		return valid;
	  
	  }
	   else if(tbl_report==0){
		   alert("No data available for this Application & Test!!");
			valid=false;
			return valid;
	   }
	  else{
		  return valid;
	  } 
	  }
	   
   
   
   </script>



	<script>
/* $('#tablegrid').hide(); */


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
var reportdtoList;
var reportdtoList;
function showGrid(){
	alert("ajax");
	var applicationid =document.getElementById("applicationName").value;
	var testid =document.getElementById("testName").value;
    $.ajax({
        type : "GET",
        asyn : false,
        url  : "getReportData?applicationid="+applicationid+"&testid="+testid,
       
        success  : function(response){
        	
        	 reportdtoList=response.reportDtoList;
        	
        	
        	   scorCountList=response.scoreCountList;
        	   
            for(var i=0;i<reportdtoList.length;i++){
        	var tr = '<tbody><tr>'+
            '<td> '+reportdtoList[i].empNumber+'</td>'+
            '<td>'+reportdtoList[i].empName+'</td>'+
            '<td>'+reportdtoList[i].applicationName+'</td>'+
            '<td>'+reportdtoList[i].testName+'</td>'+
            '<td>'+reportdtoList[i].score+'</td>'+
            '<td>'+reportdtoList[i].status+'</td></tr></tbody>';
           
            $("#sampleTable").append(tr);
           
            }
       
                $('#pass').html(scorCountList[1]);
                $('#fail').html(scorCountList[0]);
            
    }
    });
	
	$('#tablegrid').show();
	
}

</script>

</body>
</html>