// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TBL_ASMT_questionmaster")
public class QuestionMaster
{
    @Id
    @GeneratedValue
    @Column(name = "questionid")
    private int questionid;
    @Column(name = "questionname")
    private String questionname;
    @ManyToOne
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid")
    private ApplicationMaster applicationMaster;
    @ManyToOne
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    private TestDetails test;
    
    public int getQuestionid() {
        return this.questionid;
    }
    
    public void setQuestionid(final int questionid) {
        this.questionid = questionid;
    }
    
    public String getQuestionname() {
        return this.questionname;
    }
    
    public void setQuestionname(final String questionname) {
        this.questionname = questionname;
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
}
