// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DTO;

public class TestHistoryDto
{
 
    private String candidateName;
    private String testName;
    private Integer totalQue;
    private Integer attemptedQue;
    private Integer correctlyAns;
    private Integer wronglyAns;
    private String testDate;
    
    public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Integer getTotalQue() {
		return totalQue;
	}
	public void setTotalQue(Integer totalQue) {
		this.totalQue = totalQue;
	}
	public Integer getAttemptedQue() {
		return attemptedQue;
	}
	public void setAttemptedQue(Integer attemptedQue) {
		this.attemptedQue = attemptedQue;
	}
	public Integer getCorrectlyAns() {
		return correctlyAns;
	}
	public void setCorrectlyAns(Integer correctlyAns) {
		this.correctlyAns = correctlyAns;
	}
		public Integer getWronglyAns() {
		return wronglyAns;
	}
	public void setWronglyAns(Integer wronglyAns) {
		this.wronglyAns = wronglyAns;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	
    
}