// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DAO;

import com.knowledgeassessment.entity.RoleName;
import com.knowledgeassessment.entity.ApplicationMaster;
import com.knowledgeassessment.entity.Department;
import java.util.List;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.transaction.Transactional;
import org.hibernate.Session;
import com.knowledgeassessment.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class loginDao
{
    @Autowired
    private SessionFactory sessfact;
    
    @Transactional
    public Users validateUser(String name, String pwd) {
        String encoded = getEncodedPwd(pwd);
        Users userlist = null;
        Session session = sessfact.getCurrentSession();
        String hql = "from Users  where userName='" + name + "'and pwd='" + encoded + "'";
        userlist = (Users)session.createQuery(hql).uniqueResult();
        return userlist;
    }
    
    @Transactional
    public String getEncodedPwd(String pwd) {
        String encoded = Base64.encode(pwd.getBytes());
        System.out.println("encoded password--" + encoded);
        return encoded;
    }
    
    @Transactional
    public List<Department> getDepartments() {
        Session session = sessfact.getCurrentSession();
        String hql = "from Department where dept_name='Development'";
        List<Department> lead = session.createQuery(hql).list();
        return lead;
    }
    
    @Transactional
    public List<RoleName> getEmpRole() {
        Session session = sessfact.getCurrentSession();
        String hql = "from RoleName where role_name='User'";
        List<RoleName> roleName = session.createQuery(hql).list();
        return roleName;
    }
    @Transactional
	public List<Users> getCandidateList() {
		    Session session = sessfact.getCurrentSession();
	        List<Users> candidaeList = null;
	        String hql = "from Users where dept_id!=12";
	        candidaeList = (List<Users>)session.createQuery(hql).list();
	        return candidaeList;
	  }
    @Transactional
public List<ApplicationMaster> getApplicationMaster() {
Session session = sessfact.getCurrentSession();
       List<ApplicationMaster> applicationList = null;
       String hql = "from ApplicationMaster";
       applicationList = (List<ApplicationMaster>)session.createQuery(hql).list();
       return applicationList;
}


}
