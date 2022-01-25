// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knowledgeassessment.DTO.QuestionsAndAnsDTO;
import com.knowledgeassessment.DTO.TestHistoryDto;
import com.knowledgeassessment.entity.AnswerDetails;
import com.knowledgeassessment.entity.ApplicationDept;
import com.knowledgeassessment.entity.ApplicationMaster;
import com.knowledgeassessment.entity.CorrectAnswer;
import com.knowledgeassessment.entity.QuestionMaster;
import com.knowledgeassessment.entity.ScoreDetails;
import com.knowledgeassessment.entity.TestDetails;
import com.knowledgeassessment.entity.UserTestDetails;
import com.knowledgeassessment.entity.Users;

@Repository
public class AssessmentDao
{
    @Autowired
    private SessionFactory sessfact;
    
    @Transactional
    public List<ApplicationDept> getApplicationlist(Integer departmentId) {
        Session session = sessfact.getCurrentSession();
        List<ApplicationDept> applicationIds = null;
        Criteria criteria = session.createCriteria(ApplicationDept.class, "application");
        criteria.createAlias("application.department", "dept");
        criteria.add(Restrictions.eq("dept.dept_id", departmentId));
        applicationIds = (List<ApplicationDept>)criteria.list();
        return applicationIds;
    }
    
    @Transactional
    public ApplicationMaster applicationDetails(int applicationid) {
        Session session = sessfact.getCurrentSession();
        ApplicationMaster applications = null;
        Criteria criteria = session.createCriteria(ApplicationMaster.class);
        criteria.add(Restrictions.eq("applicationid", applicationid));
        applications = (ApplicationMaster)criteria.uniqueResult();
        return applications;
    }
    
    @Transactional
    public List<TestDetails> getTestDetails(Integer applicationId, Date date) {
        List<TestDetails> testDetails = null;
        try{
			Session session = sessfact.getCurrentSession();
			Criteria criteria=session.createCriteria(TestDetails.class,"test");
			criteria.createAlias("test.applicationMaster", "application");
			criteria.add(Restrictions.eq("application.applicationid", applicationId));
		    criteria.add(Restrictions.ge("test.enddate", date));
			criteria.setProjection(
					Projections.projectionList()
					.add(Projections.property("test.testid"), "testid").add(Projections.property("test.testname"), "testname"));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(TestDetails.class));
			testDetails=criteria.list();
		   }
			catch (Exception ex) {
				ex.printStackTrace();
			}
        return testDetails;
    }
    
    @Transactional
    public List<QuestionMaster> getQuestions(Integer applicationId, Integer testId) {
        List<QuestionMaster> questions = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(QuestionMaster.class, "question");
        criteria.createAlias("question.applicationMaster", "application");
        criteria.createAlias("question.test", "testDetails");
        criteria.add(Restrictions.eq("application.applicationid", applicationId));
        criteria.add(Restrictions.eq("testDetails.testid", testId));
        criteria.setProjection(
				Projections.projectionList()
				.add(Projections.property("question.questionid"), "questionid").add(Projections.property("question.questionname"), "questionname"));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(QuestionMaster.class));
		questions=criteria.list();
       // questions = (List<QuestionMaster>)criteria.list();
        return questions;
    }
    
    @Transactional
    public List<AnswerDetails> getAswers() {
        List<AnswerDetails> answers = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(AnswerDetails.class, "answer");
        criteria.createAlias("answer.question", "ques");
        answers = (List<AnswerDetails>)criteria.list();
        return answers;
    }
    
    @Transactional
    public List<QuestionMaster> getQuestionsCount(Integer applicationId, Integer testId) {
        List<QuestionMaster> questions = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(QuestionMaster.class, "question");
        criteria.createAlias("question.applicationMaster", "application");
        criteria.createAlias("question.test", "testDetails");
        criteria.add(Restrictions.eq("application.applicationid", applicationId));
        criteria.add(Restrictions.eq("testDetails.testid", testId));
        questions = (List<QuestionMaster>)criteria.list();
        return questions;
    }
    
    @Transactional
    public void saveUserTestDetails(UserTestDetails usertest) {
        Session session = sessfact.getCurrentSession();
        session.save(usertest);
    }
    
    @Transactional
    public CorrectAnswer getCorrectAnswer(int i) {
        System.out.println("i value in dao=" + i);
        CorrectAnswer cAns = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(CorrectAnswer.class, "correct");
        criteria.createAlias("correct.questionMaster", "ques");
        criteria.add(Restrictions.eq("ques.questionid", i));
        cAns = (CorrectAnswer)criteria.uniqueResult();
        return cAns;
    }
    
    @Transactional
    public AnswerDetails getOption(Integer answerVal) {
        AnswerDetails answer = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(AnswerDetails.class, "ans");
        criteria.add(Restrictions.eq("ans.answerid", answerVal));
        answer = (AnswerDetails)criteria.uniqueResult();
        return answer;
    }
    
    @Transactional
    public void saveScoreDetails(ScoreDetails scoreDetails) {
        Session session = sessfact.getCurrentSession();
        session.save(scoreDetails);
    }
    
    @Transactional
    public ScoreDetails getScoreDetails(Integer empNumber, Integer testId) {
      /* Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(ScoreDetails.class, "score");
        criteria.add(Restrictions.eq("score.empnumber", empNumber));
        criteria.add(Restrictions.eq("test.testid", testId));
        score = (ScoreDetails)criteria.uniqueResult();
        return score;*/
    	 Session session = sessfact.getCurrentSession();
         ScoreDetails score = new ScoreDetails();
         String hql = "from ScoreDetails where testid=" + testId + " and empnumber=" + empNumber + "";
         score = (ScoreDetails)session.createQuery(hql).uniqueResult();
         return score;
    }
    
    @Transactional
    public Integer saveQuestion(QuestionMaster qm) {
        Session sess = sessfact.getCurrentSession();
        int i = (int)sess.save(qm);
        System.out.println("Question Saved Successfully!!");
        return i;
    }
    
    @Transactional
    public Integer saveAnswerDetails(AnswerDetails details) {
        Session sess = sessfact.getCurrentSession();
        int i = (int)sess.save(details);
        System.out.println("Answer details Saved Successfully!!");
        return i;
    }
    
    @Transactional
    public Integer saveCorrectAns(CorrectAnswer correctAns) {
        Session sess = sessfact.getCurrentSession();
        int i = (int)sess.save(correctAns);
        System.out.println("Correct Answer details Saved Successfully!!");
        return i;
    }
    
    @Transactional
    public List<QuestionsAndAnsDTO> getAllQuestandAns() {
        List<QuestionsAndAnsDTO> qadtoList = new ArrayList<QuestionsAndAnsDTO>();
        Session sess = sessfact.getCurrentSession();
        String sql = "select a.applicationname,q.questionname,q.questionid from TBL_ASMT_applicationmaster a,TBL_ASMT_questionmaster q where a.applicationid=q.applicationid order by q.questionid desc";
        List<Object> list = (List<Object>)sess.createSQLQuery(sql).list();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            QuestionsAndAnsDTO qadto = new QuestionsAndAnsDTO();
            Object[] obj = (Object[]) it.next();
            String applicationname = (String)obj[0];
            String questname = (String)obj[1];
            Integer qid = (Integer)obj[2];
            List<String> anslist = getAllAnswersById(qid);
            qadto.setApplicationName(applicationname);
            qadto.setQuestion(questname);
            qadto.setAnswer((List)anslist);
            String correctAnsOption = getAnswersById(qid);
            qadto.setCorrectAnsOption(correctAnsOption);
            qadtoList.add(qadto);
        }
        return qadtoList;
    }
    
    private String getAnswersById(Integer qid) {
    	 Session sess = sessfact.getCurrentSession();
         String sql = "select correct_ans from TBL_ASMT_correctanswer where questionid=" + qid;
         String option = (String)sess.createSQLQuery(sql).uniqueResult();
         return option;
	}

	@Transactional
    public List<String> getAllAnswersById(Integer id) {
        Session sess = sessfact.getCurrentSession();
        List<String> list = new ArrayList<String>();
        String hql = "select answername from AnswerDetails where question.questionid=" + id;
        list = (List<String>)sess.createQuery(hql).list();
        return list;
    }
    
    @Transactional
    public Integer checkQuestionByName(String question) {
        Session sess = sessfact.getCurrentSession();
        String hql = "select questionid from QuestionMaster where questionname='" + question + "'";
        Integer obj = (Integer)sess.createQuery(hql).uniqueResult();
        return obj;
    }
    
    @Transactional
    public List<ApplicationMaster> getAllApplicationName() {
        Session sess = sessfact.getCurrentSession();
        String hql = "from ApplicationMaster";
        List<ApplicationMaster> appList = sess.createQuery(hql).list();
        return appList;
    }
    
    @Transactional
    public Integer getApplicationId(int testid) {
        Session sess = sessfact.getCurrentSession();
        String sql = "select applicationid from TBL_ASMT_testdetails where testid=" + testid;
        Integer aid = (Integer)sess.createSQLQuery(sql).uniqueResult();
        return aid;
    }
    
    @Transactional
    public List<TestDetails> getTestDetails() {
        Session sess = sessfact.getCurrentSession();
        String hql = "from TestDetails";
        List<TestDetails> testlist = sess.createQuery(hql).list();
        return testlist;
    }
    
    @Transactional
    public List<TestDetails> getTestDetails(Integer applicationId) {
        Session session = sessfact.getCurrentSession();
        List<TestDetails> testDetails = null;
        Criteria criteria = session.createCriteria(TestDetails.class, "test");
        criteria.createAlias("test.applicationMaster", "application");
        criteria.add(Restrictions.eq("application.applicationid", applicationId));
        testDetails = (List<TestDetails>)criteria.list();
        return testDetails;
    }
    
    @Transactional
    public int addApplicationName(ApplicationMaster appMaster) {
        Session sess = sessfact.getCurrentSession();
        int applicationMaster = (int)sess.save(appMaster);
        System.out.println("inserted data sucessfully Dao");
        return applicationMaster;
    }
    
    @Transactional
    public ApplicationMaster ApplicationID(String name) {
        Session session = sessfact.getCurrentSession();
        String hql = "from ApplicationMaster where applicationname='" + name + "' ";
        ApplicationMaster applicationId = (ApplicationMaster)session.createQuery(hql).uniqueResult();
        System.out.println("Application master details found by application name");
        return applicationId;
    }
    
    @Transactional
    public int saveAppDepartment(ApplicationDept appDept) {
        Session session = sessfact.getCurrentSession();
        int saveAppDept = (int)session.save(appDept);
        return saveAppDept;
    }
    
    @Transactional
    public int addTestDetails(TestDetails test) {
        Session session = sessfact.getCurrentSession();
        int testInserted = (int)session.save(test);
        return testInserted;
    }
    
    @Transactional
    public List<ApplicationMaster> getAppNames() {
        Session session = sessfact.getCurrentSession();
        List<ApplicationMaster> applications = null;
        String hql = "from ApplicationMaster ";
        applications = (List<ApplicationMaster>)session.createQuery(hql).list();
        return applications;
    }
    
    @Transactional
    public TestDetails testValidation(int appId, String testName, int roleName) {
        Session session = sessfact.getCurrentSession();
        TestDetails testDetails = new TestDetails();
        String hql = "from TestDetails where testname='" + testName + "' and applicationid=" + appId + " and roleid=" + roleName;
        testDetails = (TestDetails)session.createQuery(hql).uniqueResult();
        return testDetails;
    }
    
    @Transactional
    public List<ApplicationMaster> viewApplicationMaster() {
        List<ApplicationMaster> applicationId = null;
        return applicationId;
    }
    
    @Transactional
    public List<TestDetails> viewTestDetails() {
        Session session = sessfact.getCurrentSession();
        Criteria crtiteria = session.createCriteria(TestDetails.class);
        List<TestDetails> list = (List<TestDetails>)crtiteria.list();
        return list;
    }
    
    @Transactional
    public String getApplicationName(int applicationid) {
        ApplicationMaster application = null;
        String appName = null;
        Session session = sessfact.getCurrentSession();
        Criteria crtiteria = session.createCriteria(ApplicationMaster.class);
        crtiteria.add(Restrictions.eq("applicationid", applicationid));
        application = (ApplicationMaster)crtiteria.uniqueResult();
        appName = application.getApplicationname();
        return appName;
    }
    
    @Transactional
    public void updateScoreDetails(ScoreDetails scoreObj, Integer empNumber) {
        ScoreDetails scoreObj2 = new ScoreDetails();
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(ScoreDetails.class, "score");
        criteria.add(Restrictions.eq("score.empnumber", empNumber));
        scoreObj2 = (ScoreDetails)criteria.uniqueResult();
        scoreObj2.setApplicationMaster(scoreObj.getApplicationMaster());
        scoreObj2.setTest(scoreObj.getTest());
        scoreObj2.setCorrectlyanswerd(scoreObj.getCorrectlyanswerd());
        scoreObj2.setWronglyanswered(scoreObj.getWronglyanswered());
        scoreObj2.setTeststatus(scoreObj.getTeststatus());
        scoreObj2.setScore(scoreObj.getScore());
        scoreObj2.setEmpnumber(scoreObj.getEmpnumber());
        session.update(scoreObj2);
    }
    @Transactional
	public int addCandidateDetails(Users newuserObj) {
		 Session session = sessfact.getCurrentSession();
	        int testInserted = (int)session.save(newuserObj);
	        return testInserted;
	    }
    
    @Transactional
	public Users checkUserName(String uname) {
    	 Session session = sessfact.getCurrentSession();
    	 Users userObj = new Users();
         String hql = "from Users where userName='" + uname + "'";
         userObj = (Users)session.createQuery(hql).uniqueResult();
         return userObj;
	}
    
    @Transactional
	public void updateCandidateDetails(Users userObj) {
    	Session session = sessfact.getCurrentSession();
        session.update(userObj);
	}

    @Transactional
	public Users getCandidateDetails(int userid) {
    	 Session session = sessfact.getCurrentSession();
    	 Users userObj = new Users();
         String hql = "from Users where userID=" + userid + "";
         userObj = (Users)session.createQuery(hql).uniqueResult();
         return userObj;
	}

    @Transactional
	public String checkMobileNo(String phne_no) {
		    Session sess = sessfact.getCurrentSession();
	        String sql = "select ContactNo from Temp_TBL_ATTENDANCE_USERS where ContactNo='"+phne_no+"'";
	        String aid = (String)sess.createSQLQuery(sql).uniqueResult();
	        return aid; 
	}
    
    @Transactional
	public String checkEmail(String email) {
		Session sess = sessfact.getCurrentSession();
        String sql = "select Email from Temp_TBL_ATTENDANCE_USERS where Email='"+email+"'";
        String aid = (String)sess.createSQLQuery(sql).uniqueResult();
        return aid;
	}

    @Transactional
	public AnswerDetails getAnswersdDetailsById(Integer answerid) {
    	 Session session = sessfact.getCurrentSession();
    	 AnswerDetails userObj = new AnswerDetails();
         String hql = "from AnswerDetails where answerid=" + answerid + "";
         userObj = (AnswerDetails)session.createQuery(hql).uniqueResult();
         return userObj;
	}
    @Transactional
    public Integer checkTestGivenBytestid(Integer empNumber, Integer testid) {
    	System.out.println(empNumber+"empNumber-----testid"+testid);
    Session sess = sessfact.getCurrentSession();
     String sql = "select count(*) empnumber from  TBL_ASMT_usertestdetails where testid="+testid+" and empnumber="+empNumber+"";
     Integer aid = (Integer)sess.createSQLQuery(sql).uniqueResult();
     return aid;
    }

    @Transactional
	public Integer getattemptedQns(Integer testId, Integer empNumber) {
		 Session sess = sessfact.getCurrentSession();
	        String sql = "select count(*) as count from TBL_ASMT_usertestdetails where testid="+testId+" and empnumber="+empNumber+"";
	        Integer count = (Integer)sess.createSQLQuery(sql).uniqueResult();
	        return count;
	}

	public List<TestHistoryDto> getExamHistoryById(Integer candid) {
	        List<TestHistoryDto>  docHistoryList = null;
			Session session = null;
				session = sessfact.openSession();
				String sql="select distinct (select testname from TBL_ASMT_testdetails where testid = u.testid) as testName,CONVERT(varchar, u.submitdate, 101) as testDate,s.correctlyanswerd as correctlyAns,s.wronglyanswered as wronglyAns,s.attempted as attemptedQue from TBL_ASMT_usertestdetails u join TBL_ASMT_scoredetails s ON u.testid=s.testid where s.empnumber="+candid+"";
				docHistoryList= (List<TestHistoryDto>) session.createSQLQuery(sql).setResultTransformer(new AliasToBeanResultTransformer(TestHistoryDto.class)).list();
			return docHistoryList;
	 }
}
