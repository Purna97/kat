// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DTO;

import java.util.Date;

public class ApplicationTestDTO
{
    private String applicationName;
    private int testid;
    private String testName;
    private Date endDate;
    private Date startDate;
    
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getTestName() {
        return this.testName;
    }
    
    public void setTestName(final String testName) {
        this.testName = testName;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
    
    public int getTestid() {
        return this.testid;
    }
    
    public void setTestid(final int testid) {
        this.testid = testid;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }
}
