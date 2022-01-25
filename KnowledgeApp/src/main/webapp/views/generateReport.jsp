<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reports</title>
</head>
<body>
    <center><div class="content-wrapper" style="align:middle;">
        
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                    
                        <div class="row">
                        
                            <div class="col-lg-12">
                            <div class="well bs-component">
                        <fieldset>
                        <form action="" method="get">
                        <legend stlyle="align:left"><i class="fa fa-bar-chart" aria-hidden="true"></i>&nbsp&nbspAssessment Reports</legend>
                                <!-- <br>
                                <br> -->
                                <div class="form-group">
                                    <label for="inputPassword" class="col-lg-2 control-label">Select Report <Span
                                        style="color: red;padding-left=-20px;">*</Span></label>
                                    <div class="col-lg-4">
                                        <select name="applicationName" id="applicationName"
                                            autoComplete="off" class="form-control text-left" onchange="location.href=this.value;" required>
                                            <option value="">--Select Report--</option>
                                            <option value="test_attendedReport"> Test Attended Users Report </option>
                                            <option value="test_not_attendedReport"> Test Not Attended Users Report </option>
                                            
                                        </select><br>
                                    </div>
                                  
                                    
                                    
                                    <br> <br>
                                     

                                </div>
                                </form>
                                </fieldset>
                            </div>
                            
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        
    </div></center>

</body>


</html>