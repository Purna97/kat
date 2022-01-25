// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TBL_ASMT_testdetails")
public class TestDetails
{
    @Id
    @GeneratedValue
    @Column(name = "testid")
    private int testid;
    @Column(name = "testname")
    private String testname;
    @ManyToOne
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid")
    private ApplicationMaster applicationMaster;
    @Column(name = "teststatus")
    private String teststatus;
    @Temporal(TemporalType.DATE)
    @Column(name = "startdate")
    private Date startdate;
    @Temporal(TemporalType.DATE)
    @Column(name = "enddate")
    private Date enddate;
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "roleid")
    private int roleid;
    
    public int getTestid() {
        return this.testid;
    }
    
    public void setTestid(final int testid) {
        this.testid = testid;
    }
    
    public String getTestname() {
        return this.testname;
    }
    
    public void setTestname(final String testname) {
        this.testname = testname;
    }
    
    public ApplicationMaster getApplicationMaster() {
        return this.applicationMaster;
    }
    
    public void setApplicationMaster(final ApplicationMaster applicationMaster) {
        this.applicationMaster = applicationMaster;
    }
    
    public String getTeststatus() {
        return this.teststatus;
    }
    
    public void setTeststatus(final String teststatus) {
        this.teststatus = teststatus;
    }
    
    public Date getStartdate() {
        return this.startdate;
    }
    
    public void setStartdate(final Date startdate) {
        this.startdate = startdate;
    }
    
    public Date getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(final Date enddate) {
        this.enddate = enddate;
    }
    
    public String getCreatedby() {
        return this.createdby;
    }
    
    public void setCreatedby(final String createdby) {
        this.createdby = createdby;
    }
    
    public int getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(final int roleid) {
        this.roleid = roleid;
    }
}
