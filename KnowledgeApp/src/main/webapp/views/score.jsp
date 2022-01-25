<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start Test</title>
</head>
<body onmousemove="logout()">
    <center><div class="content-wrapper"style="align:center; height:100%;">
        <%-- <input type="hidden" id="department" name="department" value="${department}" > --%>
            <div class="row">
                <div class="col-md-12" style="align:center;margin-bottom:100px; height:100%;" >
                    <div class="card">
                        <div class="row">
                            <%-- <div class="col-lg-12" >
                              <h4 style="color: red;">Please Find Below Your Score Details</h4>
                                <br>
                              <div class="form-group">
                                        <center><label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Employee Number &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <span style="color:#673AB7;">${scoreDetails.empnumber} </span></label>
                                       </center> <div class="col-lg-11">
                                        </div> 
                                    </div>
                                   
                                <div class="form-group">
                                    <label for="inputPassword" class="col-lg-6 control-label"><Span
                                        style="color: red;"></Span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Score &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <span style="color:#673AB7;">${scoreDetails.score}</span></label>
                                    <div class="col-lg-11">
                                    </div>
                                </div>
                                    
                                    <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Correctly Answered &nbsp&nbsp&nbsp:  <span style="color:#673AB7;"> ${scoreDetails.correctlyanswerd}</span></label>
                                        <div class="col-lg-11">
                                        </div> 
                                    </div>
                                    <br>
                                <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Wrongly Answered &nbsp&nbsp&nbsp&nbsp:   <span style="color:#673AB7;">${scoreDetails.wronglyanswered}</span> </label>
                                      
                                    </div>
                                    <div class="form-group">
                                    <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Test Result &nbsp&nbsp&nbsp&nbsp:   <span style="color:#673AB7;">${scoreDetails.teststatus}</span> </label>
                                       
                                       <!--  <div class="col-lg-11">
                                        </div> -->
                                    </div>
                                  <br> <br>
                                    <div class="form-group">
                                          <h4
                                            style="color: red;align:center;">${msg}</h4>
                                    </div> 
                                </div> --%>
                                 <div class="col-lg-10" >
                             	 <h4 style="background-color:#8080c1;color:white;font-weight:bold;">Please Find Your Score Details</h4>
                                	<div class="form-group row row" >
												<label for="inputPassword" class="col-sm-4 control-label">Total  Questions</label>
												<div class="col-sm-4">
													<span style="color:#673AB7;font-weight:bold;font-size:20px;"> ${scoreDetails.correctlyanswerd+scoreDetails.wronglyanswered}</span>
												</div>
											</div>
											
												<div class="form-group row row" >
												<label for="inputPassword" class="col-sm-4 control-label">Attempted Questions</label>
												<div class="col-sm-4">
													<span style="color:#673AB7;font-weight:bold;font-size:20px;"> ${scoreDetails.attempted}</span>
												</div>
											</div>
											
											
												<div class="form-group row row" >
												
												<div class="col-sm-12">
												<h4 style="color: tomato;text-align:center;font-weight:bold;font-size:20px;">${msg}</h4>
													
												</div>
											</div>
									
											</div>
											
											
                              <%--   <div class="col-lg-12" >
                              <h4 style="background-color:#8080c1;color:white;font-weight:bold;width:800px;height:20px;">Please Find Your Score Details</h4>
                               <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="font-size:30px;"></Span>Total  Questions &nbsp&nbsp&nbsp:  <span style="color:#673AB7;"> ${scoreDetails.correctlyanswerd+scoreDetails.wronglyanswered}</span></label>
                                        <div class="col-lg-11">
                                        </div> 
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Correctly Answered &nbsp&nbsp&nbsp:  <span style="color:#673AB7;"> ${scoreDetails.correctlyanswerd}</span></label>
                                        <div class="col-lg-11">
                                        </div> 
                                    </div>
                                    <br> 
                                     <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Wrongly Answered &nbsp&nbsp&nbsp:  <span style="color:#673AB7;"> ${scoreDetails.wronglyanswered}</span></label>
                                        <div class="col-lg-11">
                                        </div> 
                                    </div>
                                    <br>  --%>
                              
                            <%--     <br>
                              <div class="form-group">
                                        <center><label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Employee Number &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <span style="color:#673AB7;">${scoreDetails.empnumber} </span></label>
                                       </center> <div class="col-lg-11">
                                        </div> 
                                    </div>
                                   
                                <div class="form-group">
                                    <label for="inputPassword" class="col-lg-6 control-label"><Span
                                        style="color: red;"></Span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Score &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <span style="color:#673AB7;">${scoreDetails.score}</span></label>
                                    <div class="col-lg-11">
                                    </div>
                                </div>
                                    
                                    <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Correctly Answered &nbsp&nbsp&nbsp:  <span style="color:#673AB7;"> ${scoreDetails.correctlyanswerd}</span></label>
                                        <div class="col-lg-11">
                                        </div> 
                                    </div>
                                    <br>
                                <div class="form-group">
                                        <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Wrongly Answered &nbsp&nbsp&nbsp&nbsp:   <span style="color:#673AB7;">${scoreDetails.wronglyanswered}</span> </label>
                                      
                                    </div>
                                    <div class="form-group">
                                    <label for="inputPassword" class="col-lg-6 control-label"><Span
                                            style="color: red;"></Span>Test Result &nbsp&nbsp&nbsp&nbsp:   <span style="color:#673AB7;">${scoreDetails.teststatus}</span> </label>
                                       
                                       <!--  <div class="col-lg-11">
                                        </div> -->
                                    </div> --%>
                                <%--   <br> <br>
                                    <div class="form-group">
                                          <h4
                                            style="color: red;align:center;">${msg}</h4>
                                    </div> --%> 

                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            
        </form>
    </div></center>

</body>
<script src="resources/js/js/jquery-2.1.4.min.js"></script>  
<script type="text/javascript">
function logout(){
	   window.location.href="Login";
}

</script>


</html>