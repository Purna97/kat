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
@Table(name = "TBL_ASMT_scoredetails")
public class ScoreDetails
{
    @Id
    @GeneratedValue
    @Column(name = "sno")
    private int sno;
    @Column(name = "score")
    private int score;
    
	@Column(name = "correctlyanswerd")
    private int correctlyanswerd;
    @Column(name = "wronglyanswered")
    private int wronglyanswered;
    @Column(name = "empnumber")
    private int empnumber;
    @ManyToOne
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid")
    private ApplicationMaster applicationMaster;
    @ManyToOne
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    private TestDetails test;
    
    @Column(name = "teststatus")
    private String teststatus;
    
	@Column(name = "attempted")
    private int attempted;
    
 
    public int getAttempted() {
		return attempted;
	}

	public void setAttempted(int attempted) {
		this.attempted = attempted;
	}

    
    public int getSno() {
        return this.sno;
    }
    
    public void setSno(final int sno) {
        this.sno = sno;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(final int score) {
        this.score = score;
    }
    
    public int getCorrectlyanswerd() {
        return this.correctlyanswerd;
    }
    
    public void setCorrectlyanswerd(final int correctlyanswerd) {
        this.correctlyanswerd = correctlyanswerd;
    }
    
    public int getWronglyanswered() {
        return this.wronglyanswered;
    }
    
    public void setWronglyanswered(final int wronglyanswered) {
        this.wronglyanswered = wronglyanswered;
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
    
    public String getTeststatus() {
        return this.teststatus;
    }
    
    public void setTeststatus(final String teststatus) {
        this.teststatus = teststatus;
    }
    
    public int getEmpnumber() {
        return this.empnumber;
    }
    
    public void setEmpnumber(final int empnumber) {
        this.empnumber = empnumber;
    }
}
