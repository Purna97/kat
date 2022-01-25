// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TBL_ASMT_usertestdetails")
public class UserTestDetails
{
    @Id
    @GeneratedValue
    @Column(name = "sno")
    private int sno;
    @Column(name = "empnumber")
    private int empnumber;
    @ManyToOne
    @JoinColumn(name = "questionid")
    private QuestionMaster question;
    @ManyToOne
    @JoinColumn(name = "answerid")
    private AnswerDetails answer;
    @ManyToOne
    @JoinColumn(name = "applicationid")
    private ApplicationMaster applicationMaster;
    @ManyToOne
    @JoinColumn(name = "testid")
    private TestDetails test;
    @Column(name = "submitdate")
    private Date submitdate;
    
    public int getSno() {
        return this.sno;
    }
    
    public void setSno(final int sno) {
        this.sno = sno;
    }
    
    public QuestionMaster getQuestion() {
        return this.question;
    }
    
    public void setQuestion(final QuestionMaster question) {
        this.question = question;
    }
    
    public AnswerDetails getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(final AnswerDetails answer) {
        this.answer = answer;
    }
    
    public ApplicationMaster getApplicationMaster() {
        return this.applicationMaster;
    }
    
    public void setApplicationMaster(final ApplicationMaster applicationMaster) {
        this.applicationMaster = applicationMaster;
    }
    
    public TestDetails getTest() {
        return this.test;
    }
    
    public void setTest(final TestDetails test) {
        this.test = test;
    }
    
    public Date getSubmitdate() {
        return this.submitdate;
    }
    
    public void setSubmitdate(final Date submitdate) {
        this.submitdate = submitdate;
    }
    
    public int getEmpnumber() {
        return this.empnumber;
    }
    
    public void setEmpnumber(final int empnumber) {
        this.empnumber = empnumber;
    }
}
