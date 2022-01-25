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
					<h1><i class="fa fa-edit"></i>Add New Candidate</h1>
			   </div>
			      <div>
					<ul class="breadcrumb">
						<li><i class="fa fa-home fa-lg"></i></li>
						<li>Add New Candidate</li>							
					</ul>
				</div>
			</div>
		       <!--   <form action="Add_Test" method="GET"> -->
		         <div class="row" id="addcandidate">
				<div class="col-md-12">
					<div class="card">
						<div class="row">
							<div class="col-lg-12">
		                   <fieldset>
										<form action="saveCandidateDetails" method="post" id="saveCandidateForm" class="form-horizontal" enctype="multipart/form-data" autocomplete="off">
	                                        <h4><b>Candidate Details</b></h4> <br><br>
								<div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;">*</Span>First Name</label>
									    <div class="col-lg-10">
							      <input type="text" id="fname" name="fname" class="form-control" placeholder="Enter First Name"
							      minlength="2" maxlength="100" style="width: 360px; height:30px; " pattern="[a-zA-Z][a-zA-Z]*" title="Only alphabets are allowed." required/>
					                </div>
				              </div>
				              <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;">*</Span>Last Name</label>
									    <div class="col-lg-10">
							      <input type="text" id="lname" name="lname" class="form-control" placeholder="Enter Last Name" style="width: 360px; height:30px; " pattern="[a-zA-Z][a-zA-Z]*" minlength="2" title="Only alphabets are allowed." required/>
					                </div>
				              </div>
				               
				               <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;">*</Span>Mobile No</label>
									    <div class="col-lg-10">
							      <input type="text" id="phne_no" name="phne_no" class="form-control" placeholder="Enter Mobile No" style="width: 360px; height:30px; "
							      minlength="10" maxlength="10" pattern="^[0-9]{10}$"title="Numbers are allowed." required/>
							       <span id="phoneshow" style="color: red;"></span>
					                </div>
				              </div>
				              
				              <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;">*</Span>E-mail ID</label>
									    <div class="col-lg-10">
							      <input type="text" id="emailid" name="emailid" class="form-control" placeholder="Enter E-mail" style="width: 360px; height:30px; "
							        minlength="8" maxlength="80"  title="Enter Valid e-mail format ex:xyz@gmail.com" required/>
							         <span id="emailshow" style="color: red;"></span>
					                </div>
				              </div>
				              
				              <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;">*</Span>Test Name</label>
									    <div class="col-lg-10">
							            <select name="testName" id="testName" autoComplete="off" 
							            class="form-control text-left" required style="width: 360px; height:30px; ">
											<option value="">--Select Test Name--</option>
											<c:forEach var="testList" items="${testList}">
											<option value="${testList.testid}"> ${testList.testname}</option>
											</c:forEach>
										</select>
					                </div>
				              </div>
											 <br>
				       						<div class="form-group">									
												<div class="col-lg-11 col-lg-offset-1">													
												<button type="submit" class="btn btn-primary" style="background-color:#20a5d4;" id="submitid">Submit</button>&nbsp&nbsp&nbsp&nbsp
												<button type="reset" class="btn btn-default" onClick="window.location.reload()"> Reset</button>
												</div>
												</div>	 
										</form>
									</fieldset> 
			   </div> 
			   </div> 
			   </div> 
			   </div>
			   </div> 
			   
			    <div class="row" id="updatecandidate" style="display:none;">
				<div class="col-md-12">
					<div class="card">
						<div class="row">
							<div class="col-lg-12">
		                   <fieldset>
								 <form action="updateCandidateDetails" method="post" id="updateCandidateForm" class="form-horizontal" enctype="multipart/form-data" autocomplete="off">
	                             <input type="hidden" id="eduserid" name="eduserid"/>
	                             <h4><b>Candidate Details</b></h4> <br><br>
								<div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;"></Span>First Name</label>
									    <div class="col-lg-10">
							      <input type="text" id="edfname" name="edfname" class="form-control" placeholder="Enter First Name"
							      minlength="2" maxlength="100" style="width: 360px; height:30px; " pattern="[a-zA-Z][a-zA-Z]*" 
							      title="Only alphabets are allowed." required readonly/>
					                </div>
				              </div>
				              <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;"></Span>Last Name</label>
									    <div class="col-lg-10">
							      <input type="text" id="edlname" name="edlname" class="form-control" placeholder="Enter Last Name" style="width: 360px; height:30px; " 
							       pattern="[a-zA-Z][a-zA-Z]*" minlength="2" title="Only alphabets are allowed." required readonly/>
					                </div>
				              </div>
				               
				               <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;"></Span>Mobile No</label>
									    <div class="col-lg-10">
							      <input type="text" id="edphne_no" name="edphne_no" class="form-control" placeholder="Enter Mobile No" style="width: 360px; height:30px; "
							      minlength="10" maxlength="10" pattern="^[0-9]{10}$"title="Numbers are allowed." required readonly/>
							       <span id="phoneshow" style="color: red;"></span>
					                </div>
				              </div>
				              
				              <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;"></Span>E-mail ID</label>
									    <div class="col-lg-10">
							      <input type="text" id="edemailid" name="edemailid" class="form-control" placeholder="Enter E-mail" style="width: 360px; height:30px; "
							        minlength="8" maxlength="80"  title="Enter Valid e-mail format ex:xyz@gmail.com" required readonly/>
							         <span id="emailshow" style="color: red;"></span>
					                </div>
				              </div>
				              
				              <div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label"><Span style="color:red;">*</Span>Test Name</label>
									    <div class="col-lg-10">
									   <input type="hidden" name="testName" id="testName" value="${testId}">
							            <select name="edtestName" id="edtestName" autoComplete="off" onchange="checkTestGiven()"
							            class="form-control text-left" required style="width: 360px; height:30px; ">
											<option value="">--Select Test Name--</option>
											<c:forEach var="testList" items="${testList}">
											<option value="${testList.testid}"> ${testList.testname}</option>
											</c:forEach>
										</select>
					                </div>
				              </div>
											 <br>
				       						<div class="form-group">									
												<div class="col-lg-11 col-lg-offset-1">													
												<button type="submit" class="btn btn-primary"  style="background-color:#20a5d4;"  id="edsubmitid">Update</button>&nbsp&nbsp&nbsp&nbsp
												<a href="candidateReg" class="btn btn-default">Cancel</a>
												</div>
												</div>	 
										</form>
									</fieldset> 
			   </div> 
			   </div> 
			   </div> 
			   </div>
			   </div> 
			   
			  <!--   </form>		 -->
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">	
						   <div style="text-align: center;">
					       <center><span><strong style="font-size:20px;color:red;">Candidate Details</strong></span></center>
					       </div>			
							<table id="sampleTable" class="table table-hover table-bordered" style="text-align:center;">
								<thead style="background-color:lavender;">
							 		<tr>	
							 				<th style="display:none;">Full Name</th>
							 		    	<th style="text-align: center">Full Name</th>
							 		    	<th style="text-align: center">User Name</th>
							     			<th style="text-align: center">Phone</th>
							     			<th style="text-align: center">Email</th>
							     			<th style="text-align: center">Test Name</th>
							     			<th style="text-align: center">Action</th>
							     			<th style="text-align: center">History</th>
									 </tr>
							    </thead>
								<tbody>	
								  <c:forEach var="userlist" items="${candidateList}">									
						         	<tr>
						         	 	<td style="display:none;">${userlist.userID}</td>	
						         	    <td class="left">${userlist.firstName} ${userlist.lastName}</td>			
						         	    <td class="left">${userlist.userName}</td>	
						         	    <td class="left">${userlist.contactNo}</td> 
						         	    <td class="left">${userlist.email}</td> 
						         	     <td class="left">${userlist.test.testname}</td> 
						         	     <td class="left">
						         	     <button	style="letter-spacing: 0px; background-color: #15aabf; color: white;" class="btn btn-info btn-sm addButton"
											onclick="editCandidate('${userlist.userID}','${userlist.firstName}','${userlist.lastName}','${userlist.contactNo}','${userlist.email}','${userlist.test.testid}');">
											<i class="fa fa-edit" aria-hidden="true"></i>
										</button>
						         	     </td> 
						         	     
						         	     <td class="left">
						         	     
						         	     <button	style="letter-spacing: 0px;background-color: #15aabf;  color: white;" class="btn btn-info btn-sm" data-toggle="modal" data-target="#historymodalid"
											onclick="getExamHistoryById('${userlist.userID}','${userlist.firstName}','${userlist.lastName}')">
											<i class="fa fa-history" aria-hidden="true"></i>
										</button>
						         	     </td> 
								 	</tr>	
								  </c:forEach>								 				 							     																												   
								</tbody>
							 </table>												
						 </div>
					</div>
				  </div>			  						  
			   </div>	
			   
			   	<div class="modal fade" id="historymodalid" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg" 
						role="document">
						<div class="modal-content" style="width: 114%;">
							<div class="modal-header" style="padding: .5rem;background-color: #2e88b5;">
								<button class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<center>
									<h5 class="modal-title" id="exampleModalLabel"
										style="font-size: 15px;color:#fff;font-weight:25px;font-size:20px;">
										<b>Assessment History</b>
									</h5>
								</center>
							</div>
							<div class="modal-body">
								<table id="assesmenthisorytable"
										class="table table-hover table-bordered"
										style="text-align: center;">
										<thead>
											<tr>
												<th>Candidate Name</th>
												<th>Test Name</th>
												<th>Attempted On</th>
												<th>Total Questions</th>
												<th>Attempted Questions</th>
												<th>Correctly Answered</th>
											</tr>
										</thead>
										<tbody >

										</tbody>
									</table>
							</div>
						</div>
					</div>
				</div>
		</div>	
   <script src="resources/assets/js/plugins/dataTables.bootstrap.min.js" type="text/javascript"></script> 
   <script type="text/javascript">$('#sampleTable').DataTable();
	var tableh1bch = $('#assesmenthisorytable').DataTable({
		"order" : [ [ 0, "desc" ] ],
		lengthMenu : [ [ 5, 10, 15, -1 ], [ 5, 10, 15, "All" ] ],
		bLengthChange : true,
		paging : true
	  });
	 $('#assesmenthisorytable').dataTable().fnClearTable();
	  function getExamHistoryById(candid,fname,lname){
	    	 var fullname="";
	    	 var tcount;
	    	 $.ajax({
	    	        type : "GET",
	    	        asyn : false,
	    	        url  : "getExamHistoryById?candid="+candid,
	    	        success  : function(response){
	    	        	 if (tableh1bch) tableh1bch.clear();
	    	        	 
	    	        	  	if(response!=""){
	    	            	  for(var i=0;i<response.length;i++){
	    	            		//  alert(response[i].startdate);
	    	            			tcount=response[i].correctlyAns+response[i].wronglyAns;
	    	            			fullname=fname+" "+lname;
	    		 		          	tableh1bch.row.add([
	    	                    	''+fullname+'',
	    	                    	''+response[i].testName+'',
	    	                    	''+response[i].testDate+'',
	    	                    	''+tcount+'',
	    	                    	''+response[i].attemptedQue+'',
	    							''+response[i].correctlyAns+''
	    	                     ]).draw();                  
	    	                   }
	    	          	}else{
	    	          	  // alert("No data found");
	    	             } 
	    	        $('h5').text(fullname+" Assessment History");	 	  	
	    	        $('#historymodalid').modal('show');
	    	      },
	    	  //	''+response[i].docStatus+''
	    	     error: function(){  
	    	     alert('Error while request..');
	    	    }
	    	  });
	     }
	  $('#historymodalid').on('hidden.bs.modal', function (){
		  window.location.href="candidateReg";
		});
   </script> 
   
   <script src="resources/assets/js/jquery-2.1.4.min.js"></script>  
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="resources/assets/css/jquery-ui.css" type="text/javascript"></script> 
<script language="JavaScript" type="text/javascript">
var textInput = document.getElementById('phne_no');
var timeout = null;
textInput.onkeyup = function (e) {
clearTimeout(timeout);
timeout = setTimeout(function () {
	/*  getsesVal().done(function(data){  
     if(data!=""){ */
	 var phne_no=$('#phne_no').val();
	    if(phne_no==""){
	    	 $('#phoneshow').text('');
	    	 $("#submitid").attr("disabled", false);
	    }else{
	    	   $.ajax({
	   	    	type : "GET",
	   	        asyn : false,
	   	        contentType: "text/plain", // NOT dataType!
	   	        url  : "checkMobileNo?phne_no="+phne_no+"",
	   	        //data: JSON.stringify(data),
	   	        success  : function(response){
	   	        	//alert(response);
	   	        	if(response!=""){
	   	        	     $('#phoneshow').html("Mobile No Already Exists.");
  	        		     $("#submitid").attr("disabled", true);
	   	        	}
	   	        	else{
	   	        		$('#phoneshow').text('');
	   	        		$("#submitid").attr("disabled", false);
	   	        	} 
	   	          }
	   		 });
	  }
/* }else{
          window.location.href="loginpage";
            }
          }); */
}, 5);
};

var textInput = document.getElementById('emailid');
var timeout = null;
textInput.onkeyup = function (e) {
clearTimeout(timeout);
timeout = setTimeout(function () {
	/*  getsesVal().done(function(data){  
         if(data!=""){ */
	 var email=$('#emailid').val();
	    if(email==""){
	    	 $('#emailshow').text('');
	    	 $("#submitid").attr("disabled", false);
	    }else{
	    	var regex = new RegExp("^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$");
	        if(!regex.test(email)){
	    	   $('#emailshow').html("<font color=red>Not a valid e-mail Id</font>");
	    	   $("#submitid").attr("disabled", true);
	        }else{
	    	   $.ajax({
	   	    	type : "GET",
	   	        asyn : false,
	   	        contentType: "text/plain", // NOT dataType!
	   	        url  : "checkEmail?email="+email+"",
	   	        //data: JSON.stringify(data),
	   	        success  : function(response){
	   	        	//alert(response);
	   	        	if(response!=""){
	   	        	     $('#emailshow').html("e-mail Already Exists.");
  	        		     $("#submitid").attr("disabled", true);
	   	        	}
	   	        	else{
	   	        		$('#emailshow').text('');
	   	        		$("#submitid").attr("disabled", false);
	   	        	} 
	   	          }
	   		 });
	      }
	  }
/* }else{
    window.location.href="loginpage";
            }
          }); */
}, 5);
};

function editCandidate(userid,fname,lname,phone,email,testid){
	//alert("erer")
	$('#addcandidate').hide();
	$('#updatecandidate').show();
	$('#eduserid').val(userid);
	$('#edfname').val(fname);
	$('#edlname').val(lname);
	$('#edphne_no').val(phone);
	$('#edemailid').val(email);
	var $dropdown2 = $("select[name='edtestName']");
    $dropdown2.find('option[value="' + testid + '"]').attr('selected', true);	
  }
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
         function checkTestGiven(){
        	
        	 var empno=$('#eduserid').val();
                var testid =document.getElementById("edtestName").value;
                if(testid!=0){
                    $.ajax({
                        type : "GET",
                        asyn : false,
                        url  : "checkTestGivenBytestid1",
                        data : {
                        	testid : testid,
                        	empno : empno
                		    },
                        success  : function(response){
                              if(response=='Exist'){
                            	  swal.fire({
                          			position : 'top',
                          			icon : 'info',
                          			width:'700px',
                          			title : 'Candidate already attempted this test..Please choose another test',
                          				 showConfirmButton: false,
                          				  timer: 2500
                          		});
                                    // alert("You Have already written this exam ,So please select other Test Type");
                                     $('#edsubmitid').attr("disabled",true);
                              }else{
                                     $('#edsubmitid').attr("disabled",false);
                              }
                              
                        }
                    });
                }
                }   
     
   
    </script>
    </body>
</html>