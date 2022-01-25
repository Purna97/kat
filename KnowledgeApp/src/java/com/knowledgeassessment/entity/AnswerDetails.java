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
@Table(name = "TBL_ASMT_answersdetails")
public class AnswerDetails
{
    @Id
    @GeneratedValue
    @Column(name = "answerid")
    private int answerid;
    @Column(name = "answername")
    private String answername;
    @Column(name = "optionval")
    private String option;
    @ManyToOne
    @JoinColumn(name = "questionid", referencedColumnName = "questionid")
    private QuestionMaster question;
    
    public int getAnswerid() {
        return this.answerid;
    }
    
    public void setAnswerid(final int answerid) {
        this.answerid = answerid;
    }
    
    public String getAnswername() {
        return this.answername;
    }
    
    public void setAnswername(final String answername) {
        this.answername = answername;
    }
    
    public QuestionMaster getQuestion() {
        return this.question;
    }
    
    public void setQuestion(final QuestionMaster question) {
        this.question = question;
    }
    
    public String getOption() {
        return this.option;
    }
    
    public void setOption(final String option) {
        this.option = option;
    }
}
