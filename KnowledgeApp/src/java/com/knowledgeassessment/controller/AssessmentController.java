// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knowledgeassessment.DAO.AssessmentDao;
import com.knowledgeassessment.DAO.loginDao;
import com.knowledgeassessment.DTO.ApplicationMasterDto;
import com.knowledgeassessment.DTO.ApplicationTestDTO;
import com.knowledgeassessment.DTO.QuestionsAndAnsDTO;
import com.knowledgeassessment.DTO.TestHistoryDto;
import com.knowledgeassessment.entity.AnswerDetails;
import com.knowledgeassessment.entity.ApplicationDept;
import com.knowledgeassessment.entity.ApplicationMaster;
import com.knowledgeassessment.entity.CorrectAnswer;
import com.knowledgeassessment.entity.Department;
import com.knowledgeassessment.entity.QuestionMaster;
import com.knowledgeassessment.entity.RoleName;
import com.knowledgeassessment.entity.ScoreDetails;
import com.knowledgeassessment.entity.TestDetails;
import com.knowledgeassessment.entity.UserTestDetails;
import com.knowledgeassessment.entity.Users;

@Controller
public class AssessmentController
{
    @Autowired
    private loginDao logindao;
    @Autowired
    private AssessmentDao assessmentDao;
    
    @RequestMapping(value = { "/Login" }, method = { RequestMethod.GET })
    public String loginForm(HttpServletRequest req) {
        return "login";
    }
    
    @RequestMapping(value = { "/LoginValidations" }, method = { RequestMethod.POST })
    public String loginvalidate(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        Users userlist = logindao.validateUser(username, password);
        List<RoleName> roleNames = (List<RoleName>)logindao.getEmpRole();
        if (userlist == null) {
            model.addAttribute("error", "Invalid Credentials");
            return "login";
        }
        session.setAttribute("roleNames", roleNames);
        session.setAttribute("department", userlist.getDept_id());
        session.setAttribute("username", userlist.getUserName());
        session.setAttribute("designation", userlist.getDesignation());
        session.setAttribute("empNumber", userlist.getEmp_number());
        if (userlist.getDept_id().equalsIgnoreCase("12")) {
            return "Dashboard";
        }
        if (userlist.getDept_id() != "12") {
            String deptid = (String)req.getSession().getAttribute("department");
            Integer departmentId = Integer.parseInt(deptid);
            List<ApplicationDept> applicationList =assessmentDao.getApplicationlist(departmentId);
            List<ApplicationMasterDto> applicationDtoList = new ArrayList<ApplicationMasterDto>();
            for (ApplicationDept applicationDept : applicationList) {
                ApplicationMaster applications = assessmentDao.applicationDetails(applicationDept.getApplicationMaster().getApplicationid());
                ApplicationMasterDto applicationDto = new ApplicationMasterDto();
                applicationDto.setApplicationid(Integer.valueOf(applications.getApplicationid()));
                applicationDto.setApplicationName(applications.getApplicationname());
                applicationDtoList.add(applicationDto);
            }
            model.addAttribute("applicationDtoList", applicationDtoList);
            
            model.addAttribute("testId", userlist.getTest().getTestid());
            
            return "TakeTest";
        }
        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }
    
    @RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
    public String logout(HttpServletRequest req) {
        return "redirect:Login";
    }
    
    @RequestMapping(value = { "/dashboard" }, method = { RequestMethod.GET })
    public String redirectToDashBoard(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        return "Dashboard";
    }
    
    @RequestMapping(value = { "/displaytest" }, method = { RequestMethod.GET })
    public String TakeTest(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        String deptid = (String)req.getSession().getAttribute("department");
        Integer departmentId = Integer.parseInt(deptid);
        List<ApplicationDept> applicationList = assessmentDao.getApplicationlist(departmentId);
        List<ApplicationMasterDto> applicationDtoList = new ArrayList<ApplicationMasterDto>();
        for (ApplicationDept applicationDept : applicationList) {
            ApplicationMaster applications = assessmentDao.applicationDetails(applicationDept.getApplicationMaster().getApplicationid());
            ApplicationMasterDto applicationDto = new ApplicationMasterDto();
            applicationDto.setApplicationid(Integer.valueOf(applications.getApplicationid()));
            applicationDto.setApplicationName(applications.getApplicationname());
            applicationDtoList.add(applicationDto);
        }
        model.addAttribute("applicationDtoList", applicationDtoList);
        return "TakeTest";
    }
    
    @RequestMapping(value = { "/getTest" }, method = { RequestMethod.POST })
    public String testDisplay(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        Integer applicationId = Integer.parseInt(req.getParameter("applicationName"));
        Integer testId = Integer.parseInt(req.getParameter("testName"));
        List<AnswerDetails> answers = null;
        List<QuestionMaster> questions = assessmentDao.getQuestions(applicationId, testId);
        answers = assessmentDao.getAswers();
        model.addAttribute("answerDetails", answers);
       /* for (QuestionMaster questionMaster : questions) {
        }*/
        model.addAttribute("questiondetails", questions);
        model.addAttribute("applicationid", applicationId);
        model.addAttribute("testid", testId);
        return "displayTest";
    }
    
    @RequestMapping(value = { "/saveTest" }, method = { RequestMethod.POST })
   // @ResponseBody
    public String testResult(HttpServletRequest req, Model model, HttpServletResponse response) {
    	System.out.println("reach ctrl***************1");
    	 ScoreDetails scoreDetails1 =null;
        HttpSession session = req.getSession(true);
         if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        Integer applicationId = Integer.parseInt(req.getParameter("applicationid"));
        Integer testId = Integer.parseInt(req.getParameter("testid"));
        Integer empNumber = (Integer)req.getSession().getAttribute("empNumber");
        System.out.println("empNumber***"+empNumber+"  applicationId***"+applicationId+"   testId***"+testId);
        int score = 0;
        int cans = 0;
        int wans = 0;
        
        UserTestDetails usertest = new UserTestDetails();
        ApplicationMaster application = new ApplicationMaster();
        TestDetails test = new TestDetails();
        application.setApplicationid(applicationId);
        test.setTestid(testId);
        usertest.setTest(test);
        usertest.setApplicationMaster(application);
        usertest.setEmpnumber(empNumber);
        Date submitdate = new Date();
        usertest.setSubmitdate(submitdate);
        List<QuestionMaster> questions = assessmentDao.getQuestionsCount(applicationId, testId);
        Integer questionCount = questions.size();
        String[] answerVal = req.getParameterValues("ansques");
        System.out.println("answerVal****"+answerVal);
        if(answerVal!=null) {
        	 for (int i = 0; i < answerVal.length; i++) {
            	 Integer answerid =0;
                 if(!answerVal[i].isEmpty()) {
                 	answerid = Integer.parseInt(answerVal[i]);
                 }
                 System.out.println("answerid****"+answerid);
                 if(answerid!=0) {
                	 AnswerDetails answerDetailsDb= assessmentDao.getAnswersdDetailsById(answerid);
                	 AnswerDetails answerDetails = new AnswerDetails();
                     QuestionMaster question = new QuestionMaster();
                     answerDetails.setAnswerid(answerid);
                     question.setQuestionid(answerDetailsDb.getQuestion().getQuestionid());
                     usertest.setAnswer(answerDetails);
                     usertest.setQuestion(question);
                     assessmentDao.saveUserTestDetails(usertest);
                     CorrectAnswer correctAns = assessmentDao.getCorrectAnswer(answerDetailsDb.getQuestion().getQuestionid());
                     String correctAnsDb = correctAns.getCorrectAns();
                     System.out.println("correctAnsDb*****"+correctAnsDb+"answerDetails.getOption()****"+answerDetails.getOption());
                     if(answerDetails.getOption() == null) {
                         answerDetails = assessmentDao.getOption(answerid);
                         String correctAnsTest = answerDetails.getOption();
                         System.out.println("correctAnsTest***"+correctAnsTest);
                         if (!correctAnsDb.equalsIgnoreCase(correctAnsTest)) {
                             continue;
                         }
                         ++cans;
                         ++score;
                 }
    		}
        }
        	 System.out.println(testId+"testId--empNumber"+empNumber);
        	 Integer attempted= assessmentDao.getattemptedQns(testId,empNumber);
             System.out.println("attempted qs+++"+attempted);
        /*for (QuestionMaster questionMaster : questions) {
            Integer qid = questionMaster.getQuestionid();
          //  String answerVal = req.getParameter("ques"+qid);
            String answerVal = req.getParameter("ansques"+qid);
           // String[] names = request.getParameterValues("names");
            System.out.println("answerVal****"+answerVal);
            Integer answerid =0;
            if(!answerVal.isEmpty()) {
            	answerid = Integer.parseInt(answerVal);
            }
            System.out.println("answerid****"+answerid);
            
            AnswerDetails answerDetails = new AnswerDetails();
            QuestionMaster question = new QuestionMaster();
            answerDetails.setAnswerid(answerid);
            question.setQuestionid(qid);
            usertest.setAnswer(answerDetails);
            usertest.setQuestion(question);
            //assessmentDao.saveUserTestDetails(usertest);
            CorrectAnswer correctAns = assessmentDao.getCorrectAnswer(qid);
            String correctAnsDb = correctAns.getCorrectAns();
            System.out.println("correctAnsDb*****"+correctAnsDb+"answerDetails.getOption()****"+answerDetails.getOption());
            if(answerDetails.getOption() == null) {
                answerDetails = assessmentDao.getOption(answerid);
                String correctAnsTest = answerDetails.getOption();
                System.out.println("correctAnsTest***"+correctAnsTest);
                if (!correctAnsDb.equalsIgnoreCase(correctAnsTest)) {
                    continue;
                }
                ++cans;
                ++score;
            }
        }*/
       
        System.out.println("cans"+cans);
        wans = questionCount - cans;
        System.out.println(wans+"questionCount***"+questionCount+"questionCount***"+cans);
        ScoreDetails scoreDetailobj = new ScoreDetails();
        if (cans > wans) {
            scoreDetailobj.setTeststatus("pass");
        }
        else {
            scoreDetailobj.setTeststatus("fail");
        }
        System.out.println("attempted qs1+++"+attempted);
        scoreDetailobj.setScore(score);
        scoreDetailobj.setCorrectlyanswerd(cans);
        scoreDetailobj.setWronglyanswered(wans);
        scoreDetailobj.setApplicationMaster(application);
        scoreDetailobj.setTest(test);
        scoreDetailobj.setEmpnumber(empNumber);
        scoreDetailobj.setAttempted(attempted);
        ScoreDetails scoreDetails = assessmentDao.getScoreDetails(empNumber,testId);
        if (scoreDetails == null) {
            assessmentDao.saveScoreDetails(scoreDetailobj);
        }
        else {
            assessmentDao.updateScoreDetails(scoreDetailobj, Integer.valueOf(scoreDetails.getEmpnumber()));
         }
       }
	  // scoreDetails1 = assessmentDao.getScoreDetails(empNumber,testId);
      //  return scoreDetails;
        //return null;
        return "redirect:/scoreDisplay?testId="+testId+"";
        //return scoreDetails1;
    }

@RequestMapping(value = { "/checkTestGivenBytestid" }, method = { RequestMethod.GET })
    @ResponseBody
    public String checkTestGivenBytestid(HttpServletRequest req, Model model) {
       String message=null;
       Integer testid = Integer.parseInt(req.getParameter("testid"));
         Integer empNumber = (Integer)req.getSession().getAttribute("empNumber");
         Integer id=assessmentDao.checkTestGivenBytestid(empNumber,testid);
         if(id!=0) {
               message="Exist"; 
         }else {
               message="Not Exist";  
         }
        return message;
    }
@RequestMapping(value = { "/checkTestGivenBytestid1" }, method = { RequestMethod.GET })
@ResponseBody
public String checkTestGivenBytestid1(HttpServletRequest req, Model model) {
   String message=null;
   Integer testid = Integer.parseInt(req.getParameter("testid"));
     Integer empNumber = Integer.parseInt(req.getParameter("empno"));
     Integer id=assessmentDao.checkTestGivenBytestid(empNumber,testid);
     if(id!=0) {
           message="Exist"; 
     }else {
           message="Not Exist";  
     }
    return message;
}
 
    @RequestMapping(value = { "/scoreDisplay" }, method = { RequestMethod.GET })
    public String scoreDisplay(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        Integer empNumber = (Integer)req.getSession().getAttribute("empNumber");
        Integer testid = Integer.parseInt(req.getParameter("testId"));
        ScoreDetails scoreDetails = assessmentDao.getScoreDetails(empNumber,testid);
        String msg = "Thank You";
        model.addAttribute("msg", msg);
        model.addAttribute("scoreDetails", scoreDetails);
        return "score";
    }
    
    @RequestMapping(value = { "/AddQuetions" }, method = { RequestMethod.GET })
    public String createQuestionAndAns(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<ApplicationMaster> appList = assessmentDao.getAllApplicationName();
        model.addAttribute("appList", appList);
        List<QuestionsAndAnsDTO> qadtoList = assessmentDao.getAllQuestandAns();
        model.addAttribute("qadtoList", qadtoList);
        return "createQuestionAndAns";
    }
    
    @RequestMapping(value = { "/saveQuestion" }, method = { RequestMethod.POST })
    public String saveQuestion(HttpServletRequest req, Model model, HttpServletResponse response) throws IOException {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        PrintWriter out = response.getWriter();
        String[] options = { "a", "b", "c" };
        int applicationid = Integer.parseInt(req.getParameter("applicationName"));
        int testid = Integer.parseInt(req.getParameter("test"));
        String question = req.getParameter("quest");
        Integer checkQuestObj = assessmentDao.checkQuestionByName(question);
        if (checkQuestObj == null || checkQuestObj.equals("")) {
            String val_opt1 = req.getParameter("option1");
            String val_opt2 = req.getParameter("option2");
            String val_opt3 = req.getParameter("option3");
            System.out.println("options--" + val_opt1 + val_opt2);
            String[] val_options = { val_opt1, val_opt2, val_opt3 };
            for (int i = 0; i < val_options.length; ++i) {
                System.out.println("array ---" + val_options[i]);
            }
            String cb_value = req.getParameter("c_option");
            System.out.println("check box value--" + cb_value);
            QuestionMaster qm = new QuestionMaster();
            qm.setQuestionname(question);
            ApplicationMaster am = new ApplicationMaster();
            am.setApplicationid(applicationid);
            qm.setApplicationMaster(am);
            TestDetails test = new TestDetails();
            test.setTestid(testid);
            qm.setTest(test);
            Integer qid = assessmentDao.saveQuestion(qm);
            System.out.println("quest id--" + qid);
            for (int j = 0; j < 3; ++j) {
                AnswerDetails details = new AnswerDetails();
                qm.setQuestionid(qid);
                details.setQuestion(qm);
                details.setAnswername(val_options[j]);
                details.setOption(options[j]);
                Integer ansid = assessmentDao.saveAnswerDetails(details);
                System.out.println("ans id---" + ansid + "quest id--" + qid);
            }
            CorrectAnswer correctAns = new CorrectAnswer();
            correctAns.setQuestionMaster(qm);
            correctAns.setCorrectAns(cb_value);
            Integer correctid = assessmentDao.saveCorrectAns(correctAns);
            System.out.println("correctid---" + correctid);
            req.setAttribute("checkQuest_msg", "Question Added Successfully!!");
            return "redirect:AddQuetions";
        }
        System.out.println("in else part--save question--questions exists" + checkQuestObj);
        req.setAttribute("checkQuest_msg", "Question Already Exists!!");
        return "redirect:AddQuetions";
    }
    
    @RequestMapping(value = { "/createCategory" }, method = { RequestMethod.GET })
    public String createCategory(HttpServletRequest req, Model model, HttpServletResponse res) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<Department> alldepartments =logindao.getDepartments();
        System.out.println("list dept size--" + alldepartments.size());
        session.setAttribute("alldepartments", alldepartments);
        List<ApplicationMaster> applicationList =logindao.getApplicationMaster();
        session.setAttribute("applicationList", applicationList);

        return "Test_home";
    }
    
    @RequestMapping(value = { "/AddCategory" }, method = { RequestMethod.GET })
    public void AddCategory(HttpServletRequest req, Model model, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(true);
            if (session.getAttribute("username") == null) {
                model.addAttribute("session_msg", "Your Session has been expired,please login again!");
                req.getRequestDispatcher("Login").forward((ServletRequest)req, (ServletResponse)res);
            }
            PrintWriter out = res.getWriter();
            int applicationId = 0;
            String[] Departments = req.getParameterValues("departments");
            String application = req.getParameter("application");
            ApplicationMaster appMaster = new ApplicationMaster();
            appMaster = assessmentDao.ApplicationID(application);
            System.out.println("applicationIdnullpointer    " + appMaster + " application " + application);
            if (appMaster == null) {
                ApplicationMaster applMaster = new ApplicationMaster();
                applMaster.setApplicationname(application);
                int AppName = assessmentDao.addApplicationName(applMaster);
                applMaster = assessmentDao.ApplicationID(application);
                applicationId = applMaster.getApplicationid();
                System.out.println("inserted");
                System.out.println("applicationId" + applicationId);
                ApplicationDept appDept = new ApplicationDept();
                ApplicationMaster applicationMaster = new ApplicationMaster();
                Department dept = new Department();
                for (int i = 0; i < Departments.length; ++i) {
                    Integer deptId = Integer.parseInt(Departments[i]);
                    applicationMaster.setApplicationid(applicationId);
                    dept.setDept_id(deptId);
                    appDept.setDepartment(dept);
                    appDept.setApplicationMaster(applicationMaster);
                    int saveDept = assessmentDao.saveAppDepartment(appDept);
                    System.out.println("inserted1234..." + saveDept);
                }
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Application Name Added Successfully');");
                out.println("window.location.href='createCategory'");
                out.println("</script>");
            }
            else {
                System.out.println("already exists");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Application Name already exist please kindly check');");
                out.println("window.location.href='createCategory'");
                out.println("</script>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = { "/createTest" }, method = { RequestMethod.GET })
    public String createTest(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<ApplicationMaster> applications = assessmentDao.getAppNames();
        List<ApplicationTestDTO> appTestDto = new ArrayList<ApplicationTestDTO>();
        String applicationName = null;
        List<TestDetails> testList = assessmentDao.viewTestDetails();
        for (TestDetails testDetails : testList) {
            applicationName = assessmentDao.getApplicationName(testDetails.getApplicationMaster().getApplicationid());
            ApplicationTestDTO appDto = new ApplicationTestDTO();
            appDto.setApplicationName(applicationName);
            appDto.setTestid(testDetails.getTestid());
            appDto.setTestName(testDetails.getTestname());
            appDto.setEndDate(testDetails.getEnddate());
            appDto.setStartDate(testDetails.getStartdate());
            appTestDto.add(appDto);
        }
        model.addAttribute("testList", appTestDto);
        System.out.println("create test");
        model.addAttribute("applicationDtoList", applications);
        return "Test_Categories";
    }
    
    @RequestMapping(value = { "/Add_Test" }, method = { RequestMethod.GET})
    public void addTest(HttpServletRequest req, Model model, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(true);
            if (session.getAttribute("username") == null) {
                model.addAttribute("session_msg", "Your Session has been expired,please login again!");
                req.getRequestDispatcher("Login").forward((ServletRequest)req, (ServletResponse)res);
            }
            PrintWriter out = res.getWriter();
            List<ApplicationMaster> applications = assessmentDao.getAppNames();
            model.addAttribute("applicationDtoList", applications);
            int applicationId = Integer.parseInt(req.getParameter("applicationName"));
            String testName = req.getParameter("testName");
            int roleName = Integer.parseInt(req.getParameter("roleName"));
            String fromDate = req.getParameter("fromDate");
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date startdate = df.parse(fromDate);
            String toDate = req.getParameter("toDate");
            Date enddate = df.parse(toDate);
            String testStatus = "open";
            System.out.println("applicationId.." + applicationId + " testName..    " + testName + "  fromDate " + fromDate + "  startdate" + startdate + "  enddate " + enddate);
            TestDetails testValidation = new TestDetails();
            testValidation = assessmentDao.testValidation(applicationId, testName, roleName);
            if (testValidation == null) {
                TestDetails testDetails = new TestDetails();
                ApplicationMaster applicationMaster = new ApplicationMaster();
                applicationMaster.setApplicationid(applicationId);
                testDetails.setApplicationMaster(applicationMaster);
                testDetails.setTestname(testName);
                testDetails.setTeststatus(testStatus);
                testDetails.setStartdate(startdate);
                testDetails.setEnddate(enddate);
                testDetails.setRoleid(roleName);
                int addtestDetails = assessmentDao.addTestDetails(testDetails);
                System.out.println("addtestDetails new inserted" + addtestDetails);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Test Name Added Successfully');");
                out.println("window.location.href='createTest'");
                out.println("</script>");
            }
            else {
                System.out.println("alredy test exist ***");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Test Name already exist for this Application and Role');");
                out.println("window.location.href='createTest'");
                out.println("</script>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = { "/candidateReg" }, method = { RequestMethod.GET })
    public String tempreg(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<Users> candidateList = (List<Users>)logindao.getCandidateList();
		model.addAttribute("candidateList", candidateList);
		 Integer appiicationId=1;
	     Date date = new Date();
	     List<TestDetails> testList=assessmentDao.getTestDetails(appiicationId, date);
	     model.addAttribute("testList", testList);
        return "Temp_Registration";
    }
    
    @RequestMapping(value = { "/saveCandidateDetails" }, method = { RequestMethod.POST})
    public void saveCandidateDetails(HttpServletRequest req, Model model, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(true);
            if (session.getAttribute("username") == null) {
                model.addAttribute("session_msg", "Your Session has been expired,please login again!");
                req.getRequestDispatcher("Login").forward((ServletRequest)req, (ServletResponse)res);
            }
            PrintWriter out = res.getWriter();
            List<Users> candidateList = logindao.getCandidateList();
    		model.addAttribute("candidateList", candidateList);
            String firstname = req.getParameter("fname");
            String lastname = req.getParameter("lname");
            String phne_no = req.getParameter("phne_no");
            String emailid = req.getParameter("emailid");
            String testName = req.getParameter("testName");
            Integer testId = Integer.parseInt(testName);
            Users newuserObj = new Users();
            TestDetails test = new TestDetails();
            test.setTestid(testId);
           /* UserID	UserName	Pwd	FullName	Address	BarCodeNo	Email	ContactNo	ISNightShift	JoiningDate	PostedBy	Timings	Status	Disabled	recrutier_name	hr_name	fathers_name	blood_group	dob	designation	dept_id	emp_type	employee_role	emp_billing	bill_period	passport	bond_period	variable_pay	bd_name	pan_no	pf_no	remarks	submit_date	emp_number	deptid	ssn_no	country	ctc	cont_team_person	mgr_empid	middle_name	family_name	Street	City	ZipCode	currency	state	Location	c2c_type	bgc_type	inactive_date	candidateid
            257	admin.tek	YXNkQVNEMTIz	Admin	hyderabad	0	admin@gmail.com	90000000000	0	2017-07-04 00:00:00.000	200954	-9.00	1	0	Test	Test	Test									O+	07/04/2017	Admin	12	Internal	4	No			0	No		82B1CAACF763DADB08EF96F70B456B0B	No	NA	Jul  4 2017  2:41PM	200000	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL*/
                newuserObj.setFirstName(firstname);
                newuserObj.setLastName(lastname);
                newuserObj.setPwd("YXNkQVNEMTIz");
                newuserObj.setContactNo(phne_no);
                newuserObj.setEmail(emailid);
                newuserObj.setStatus(1);
                newuserObj.setEmployee_role("1");
                newuserObj.setDept_id("10");
                newuserObj.setDesignation("User");
                newuserObj.setEmp_type("External");
                newuserObj.setTest(test);
                int userid = assessmentDao.addCandidateDetails(newuserObj);
                if(userid>0) {
                	String unamenoSpace = null;
                	String firstLetter = String.valueOf(lastname.charAt(0));
        			String uname = firstname.trim() + "." + firstLetter;
        			Users userObj = assessmentDao.checkUserName(uname);
        			if (userObj != null) {
        				Integer flag = 0;
        				if (userObj.getFlag() != null) {
        					flag = userObj.getFlag() + 1;
        				} else {
        					flag = 1;
        				}
        				String uname1 = userObj.getUserName() + flag;
        				userObj.setFlag(1);
        				assessmentDao.updateCandidateDetails(userObj);
        				String userName = uname1;
        				unamenoSpace = userName.replaceAll("\\s", "");
        			} else {
        				unamenoSpace =uname.replaceAll("\\s", "");
        			}
        			
        			Users userObjNew = assessmentDao.getCandidateDetails(userid);
        			userObjNew.setUserName(unamenoSpace);
        			userObjNew.setEmp_number(userid);
        			assessmentDao.updateCandidateDetails(userObjNew);
        			
        			
        			
                	 out.println("<script type=\"text/javascript\">");
                     out.println("alert('New Candidate Added Successfully');");
                     out.println("window.location.href='candidateReg'");
                     out.println("</script>");
                }else {
                	 out.println("<script type=\"text/javascript\">");
                     out.println("alert('Candidate Not Added');");
                     out.println("window.location.href='candidateReg'");
                     out.println("</script>");
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = { "/checkMobileNo" }, method = { RequestMethod.GET })
    @ResponseBody
    public String checkMobileNo(HttpServletRequest req, Model model) {
    	String message=null;
             String phne_no = req.getParameter("phne_no");
             message=assessmentDao.checkMobileNo(phne_no);
        return message;
    }
    
    @RequestMapping(value = { "/checkEmail" }, method = { RequestMethod.GET })
    @ResponseBody
    public String checkEmail(HttpServletRequest req, Model model) {
    	String message=null;
             String email = req.getParameter("email");
             message=assessmentDao.checkEmail(email);
        return message;
    }
    
    @RequestMapping(value = { "/getTestByApplicationid" }, method = { RequestMethod.GET })
    @ResponseBody
    public List<TestDetails> getTestByApplicationid(HttpServletRequest req, Model model) {
        Integer appiicationId = Integer.parseInt(req.getParameter("applicationid"));
        Date date = new Date();
        List<TestDetails> testList=assessmentDao.getTestDetails(appiicationId, date);
        return testList;
    }
    
    
    @RequestMapping(value = { "/updateCandidateDetails" }, method = { RequestMethod.POST})
    public void updateCandidateDetails(HttpServletRequest req, Model model, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(true);
            if (session.getAttribute("username") == null) {
                model.addAttribute("session_msg", "Your Session has been expired,please login again!");
                req.getRequestDispatcher("Login").forward((ServletRequest)req, (ServletResponse)res);
            }
            PrintWriter out = res.getWriter();
            List<Users> candidateList = logindao.getCandidateList();
    		model.addAttribute("candidateList", candidateList);
            String firstname = req.getParameter("edfname");
            String eduserid = req.getParameter("eduserid");
            String lastname = req.getParameter("edlname");
            String phne_no = req.getParameter("edphne_no");
            String emailid = req.getParameter("edemailid");
            String testName = req.getParameter("edtestName");
            Integer testId = Integer.parseInt(testName);
            Integer userid = Integer.parseInt(eduserid);
            Users newuserObjDB=assessmentDao.getCandidateDetails(userid);
            TestDetails test = new TestDetails();
            test.setTestid(testId);
           /* UserID	UserName	Pwd	FullName	Address	BarCodeNo	Email	ContactNo	ISNightShift	JoiningDate	PostedBy	Timings	Status	Disabled	recrutier_name	hr_name	fathers_name	blood_group	dob	designation	dept_id	emp_type	employee_role	emp_billing	bill_period	passport	bond_period	variable_pay	bd_name	pan_no	pf_no	remarks	submit_date	emp_number	deptid	ssn_no	country	ctc	cont_team_person	mgr_empid	middle_name	family_name	Street	City	ZipCode	currency	state	Location	c2c_type	bgc_type	inactive_date	candidateid
            257	admin.tek	YXNkQVNEMTIz	Admin	hyderabad	0	admin@gmail.com	90000000000	0	2017-07-04 00:00:00.000	200954	-9.00	1	0	Test	Test	Test									O+	07/04/2017	Admin	12	Internal	4	No			0	No		82B1CAACF763DADB08EF96F70B456B0B	No	NA	Jul  4 2017  2:41PM	200000	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL	NULL*/
                newuserObjDB.setFirstName(firstname);
                newuserObjDB.setLastName(lastname);
                newuserObjDB.setContactNo(phne_no);
                newuserObjDB.setEmail(emailid);
                newuserObjDB.setTest(test);
               assessmentDao.updateCandidateDetails(newuserObjDB);
             if(userid>0) {
            	 /*if(!firstname.equals(newuserObjDB.getFirstName()) || !lastname.equals(newuserObjDB.getLastName())) {
                	String unamenoSpace = null;
                	String firstLetter = String.valueOf(lastname.charAt(0));
        			String uname = firstname.trim() + "." + firstLetter;
        			Users userObj = assessmentDao.checkUserName(uname);
        			if (userObj != null) {
        				Integer flag = 0;
        				if (userObj.getFlag() != null) {
        					flag = userObj.getFlag() + 1;
        				} else {
        					flag = 1;
        				}
        				String uname1 = userObj.getUserName() + flag;
        				userObj.setFlag(1);
        				assessmentDao.updateCandidateDetails(userObj);
        				String userName = uname1;
        				unamenoSpace = userName.replaceAll("\\s", "");
        			} else {
        				unamenoSpace =uname.replaceAll("\\s", "");
        			}
        			Users userObjNew = assessmentDao.getCandidateDetails(userid);
        			userObjNew.setUserName(unamenoSpace);
        			assessmentDao.updateCandidateDetails(userObjNew);
            	 }*/
                	 out.println("<script type=\"text/javascript\">");
                     out.println("alert('Candidate Details Updated Successfully');");
                     out.println("window.location.href='candidateReg'");
                     out.println("</script>");
                }else {
                	 out.println("<script type=\"text/javascript\">");
                     out.println("alert('Candidate Details Not Updated');");
                     out.println("window.location.href='candidateReg'");
                     out.println("</script>");
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = { "/getExamHistoryById" }, method = { RequestMethod.GET })
    @ResponseBody
    public List<TestHistoryDto> getExamHistoryById(HttpServletRequest req, Model model) {
        Integer candid = Integer.parseInt(req.getParameter("candid"));
        List<TestHistoryDto> testList=assessmentDao.getExamHistoryById(candid);
        return testList;
    }
}
