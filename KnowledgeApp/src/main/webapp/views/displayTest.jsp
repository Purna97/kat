<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style type="text/css">
body {

	background-color: #fff;
}

article {
    width: 300px;
    text-align: center;
    background-color: #68b04d;
    border-radius: 10px;
    margin: 0px auto 20px;
    padding: 5px;
    overflow: hidden;
    box-shadow: 3px 3px 10px #ccc;

}

article h2 {
    padding: 2px;
    color: #fff;
}
article .count {
    padding: 5px;
}
article #timer{
    padding: 5px;
    color: red;
    background-color: #fff;
    border-radius: 48px;
    font-weight: bold;
    font-size:20px;
}
</style>

<div class="content-wrapper" style=" align: center;background-color: #fff;">
	<form class="form-inline" action="saveTest" method="post" id="saveresponseform" >
		<div class="row">
			<div class="col-md-12">

				<input type="hidden" id="testid" name="testid" value="${testid}" /> <input
					type="hidden" name="applicationid" value="${applicationid}" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<div style="float: right;position: fixed;right: 1px;">
<article class="clock" id="model3">
  <h2></h2>

  <div class="count">
    <div id="timer"></div>
  </div>
</article></div>
			    <c:if test="${questiondetails.size() eq 0}">
					<center><h4><strong style="color:#673AB7;">No Tests are available </strong> </h4></center>
					</c:if>
				<table>
					<tr>
					<c:if test="${questiondetails.size() gt 0}">
						<td><h4><strong style="color:#673AB7;">Please check the appropriate box for
								below questions: </strong> </h4></td> 
						<c:forEach var="questionDetails" items="${questiondetails}" varStatus="loop">
							<tr class="questions">
								<td> <br><label style="color: #610b4b;font-size:20px;"><c:out value="${loop.count}. "></c:out><c:out
											value="${questionDetails.getQuestionname()}"></c:out></label><span
									class="mandatory" style="color: #673AB7;font-size:20px;">&nbsp;?</span></td>
							</tr>

							<c:forEach var="answers" items="${answerDetails}">

								<c:if
									test="${questionDetails.getQuestionid()==answers.getQuestion().getQuestionid()}">
									
									<tr id="answer">
										<td><input type="radio"
											id="ques${questionDetails.getQuestionid()}"
											name="ques${questionDetails.getQuestionid()}"
											value="${answers.getAnswerid()}" required="required" onchange="getClicked(${questionDetails.getQuestionid()},${answers.getAnswerid()});">
											<input type="hidden" id="ansques${questionDetails.getQuestionid()}" name="ansques">
										<strong>&nbsp;<c:out value="${fn:toUpperCase(answers.getOption())}"></c:out>.&nbsp;
												<c:out value="${answers.getAnswername()}"></c:out>
										</strong></td>

									</tr>

								</c:if>
							</c:forEach>

						</c:forEach>
						</c:if>
				</table>
				<br> <br>
			</div>
		</div>
		<c:if test="${questiondetails.size() gt 0}">
		<input type="submit" id="frmsubmit" class="btn btn-success" value="SUBMIT">
		</c:if>
	</form>
	
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>
<script type="text/javascript">
window.addEventListener('mouseup', e => {

	if ((document.fullScreenElement && document.fullScreenElement !== null) ||    
			   (!document.mozFullScreen && !document.webkitIsFullScreen)) {
			   if (document.documentElement.webkitRequestFullScreen) {  
			      document.documentElement.webkitRequestFullScreen();  
			    }  
			  }
});
	var h2 = document.getElementsByTagName("h2");
	h2[0].innerHTML = "Time left";
	var sec         = 1800,
	    countDiv    = document.getElementById("timer"),
	    secpass,
	    countDown   = setInterval(function () {
	        'use strict';
	        secpass();
	    }, 1000);
	function secpass() {
	    'use strict';
	    var min     = Math.floor(sec / 60),
	        remSec  = sec % 60;
	    if (remSec < 10) {
	        remSec = '0' + remSec;
	    }
	    if (min < 10) {
	        min = '0' + min;
	    }
	    countDiv.innerHTML = min + ":" + remSec;
	    if (sec > 0) {
	        sec = sec - 1;
	    } else {
	        clearInterval(countDown);
	        countDiv.innerHTML = '00:00';
	        swal.fire({
				position : 'top',
				icon : 'info',
				width:'500px',
				title : 'Time Up',
					 showConfirmButton: false,
					  timer: 1500
			}).then(() => {
				 document.getElementById("saveresponseform").submit();
			});
	      //  document.getElementById("saveresponseform").submit();
	    }
	}
   function getClicked(qid,ansid){
	   $("#ansques"+qid).val('');
	   $("#ansques"+qid).val(ansid);
	}
   


</script>

