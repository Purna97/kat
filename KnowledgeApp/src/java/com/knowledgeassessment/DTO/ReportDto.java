// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DTO;

public class ReportDto
{
    private Integer empNumber;
    private String empName;
    private Integer testId;
    private String testName;
    private Integer applicationId;
    private String applicationName;
    private Integer score;
    private String status;
    
    public int getEmpNumber() {
        return this.empNumber;
    }
    
    public void setEmpNumber(final int empNumber) {
        this.empNumber = empNumber;
    }
    
    public String getEmpName() {
        return this.empName;
    }
    
    public void setEmpName(final String empName) {
        this.empName = empName;
    }
    
    public int getTestId() {
        return this.testId;
    }
    
    public void setTestId(final int testId) {
        this.testId = testId;
    }
    
    public String getTestName() {
        return this.testName;
    }
    
    public void setTestName(final String testName) {
        this.testName = testName;
    }
    
    public int getApplicationId() {
        return this.applicationId;
    }
    
    public void setApplicationId(final int applicationId) {
        this.applicationId = applicationId;
    }
    
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(final int score) {
        this.score = score;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
}
