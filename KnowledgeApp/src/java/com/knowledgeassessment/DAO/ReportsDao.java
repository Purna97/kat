// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DAO;

import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import com.knowledgeassessment.entity.Users;
import com.knowledgeassessment.entity.ScoreDetails;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import com.knowledgeassessment.entity.TestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ReportsDao
{
    @Autowired
    private SessionFactory sessfact;
    
    @Transactional
    public TestDetails getTestDetaisByTestId(Integer testId) {
        Session session = sessfact.getCurrentSession();
        TestDetails test = null;
        Criteria criteria = session.createCriteria(TestDetails.class);
        criteria.add(Restrictions.eq("testid", testId));
        test = (TestDetails)criteria.uniqueResult();
        return test;
    }
    
    @Transactional
    public List<ScoreDetails> getScoredetailsList(Integer applicationId, Integer testId) {
        List<ScoreDetails> scoreList = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(ScoreDetails.class, "score");
        criteria.createAlias("score.applicationMaster", "application");
        criteria.createAlias("score.test", "testobj");
        criteria.add(Restrictions.eq("application.applicationid", applicationId));
        criteria.add(Restrictions.eq("testobj.testid", testId));
        scoreList = (List<ScoreDetails>)criteria.list();
        return scoreList;
    }
    
    @Transactional
    public String getEmployeeName(int empnumber) {
        String userName = null;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(Users.class, "user");
        criteria.add(Restrictions.eq("user.emp_number", empnumber));
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.property("user.fullName"));
        criteria.setProjection(proList);
        userName = (String)criteria.uniqueResult();
        return userName;
    }
    
    @Transactional
    public int getPassCount(Integer applicationId, Integer testId) {
        List<ScoreDetails> scoreList = null;
        int passCount = 0;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(ScoreDetails.class, "score");
        criteria.createAlias("score.applicationMaster", "application");
        criteria.createAlias("score.test", "testobj");
        criteria.add(Restrictions.eq("application.applicationid", applicationId));
        criteria.add(Restrictions.eq("testobj.testid", testId));
        criteria.add(Restrictions.eq("teststatus", "pass"));
        scoreList = (List<ScoreDetails>)criteria.list();
        passCount = scoreList.size();
        System.out.println("passCount................." + passCount);
        return passCount;
    }
    
    @Transactional
    public int getFailCount(Integer applicationId, Integer testId) {
        List<ScoreDetails> scoreList = null;
        int failCount = 0;
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(ScoreDetails.class, "score");
        criteria.createAlias("score.applicationMaster", "application");
        criteria.createAlias("score.test", "testobj");
        criteria.add(Restrictions.eq("application.applicationid", applicationId));
        criteria.add(Restrictions.eq("testobj.testid", testId));
        criteria.add(Restrictions.eq("teststatus", "fail"));
        scoreList = (List<ScoreDetails>)criteria.list();
        failCount = scoreList.size();
        System.out.println("failCount................." + failCount);
        return failCount;
    }
    
    @Transactional
    public List<Users> getAllUsers() {
        Session session = sessfact.getCurrentSession();
        String hql = " from Users where emp_type='Internal' and employee_role!='4'";
        List<Users> userList = session.createQuery(hql).list();
        return userList;
    }
    
    @Transactional
    public boolean getScoreDetailsByEmpNo(int emp_number, Integer appid, Integer testid) {
        Session session = sessfact.getCurrentSession();
        Criteria criteria = session.createCriteria(ScoreDetails.class, "score");
        criteria.add(Restrictions.eq("empnumber", emp_number));
        criteria.createAlias("score.applicationMaster", "application");
        criteria.createAlias("score.test", "testobj");
        criteria.add(Restrictions.eq("application.applicationid", appid));
        criteria.add(Restrictions.eq("testobj.testid", testid));
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.property("empnumber"));
        criteria.setProjection(proList);
        Integer empnumber = (Integer)criteria.uniqueResult();
        return empnumber != null;
    }
}
