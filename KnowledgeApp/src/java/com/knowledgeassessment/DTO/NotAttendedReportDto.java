// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DTO;

public class NotAttendedReportDto
{
    private Integer empNumber;
    private String empName;
    private String testName;
    private String applicationName;
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
    
    public String getTestName() {
        return this.testName;
    }
    
    public void setTestName(final String testName) {
        this.testName = testName;
    }
    
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
}
