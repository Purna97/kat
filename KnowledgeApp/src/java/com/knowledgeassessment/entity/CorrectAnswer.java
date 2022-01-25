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
@Table(name = "TBL_ASMT_correctanswer")
public class CorrectAnswer
{
    @Id
    @GeneratedValue
    @Column(name = "sno")
    private int sno;
    @ManyToOne
    @JoinColumn(name = "questionid")
    private QuestionMaster questionMaster;
    @Column(name = "correct_ans")
    private String correctAns;
    
    public int getSno() {
        return this.sno;
    }
    
    public void setSno(final int sno) {
        this.sno = sno;
    }
    
    public QuestionMaster getQuestionMaster() {
        return this.questionMaster;
    }
    
    public void setQuestionMaster(final QuestionMaster questionMaster) {
        this.questionMaster = questionMaster;
    }
    
    public String getCorrectAns() {
        return this.correctAns;
    }
    
    public void setCorrectAns(final String correctAns) {
        this.correctAns = correctAns;
    }
}
