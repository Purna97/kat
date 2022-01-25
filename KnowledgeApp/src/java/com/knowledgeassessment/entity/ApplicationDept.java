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
@Table(name = "TBL_ASMT_ApplicationDept")
public class ApplicationDept
{
    @Id
    @GeneratedValue
    @Column(name = "sno")
    private int sno;
    @ManyToOne
    @JoinColumn(name = "applicationid", referencedColumnName = "applicationid")
    private ApplicationMaster applicationMaster;
    @ManyToOne
    @JoinColumn(name = "deptid", referencedColumnName = "dept_id")
    private Department department;
    
    public int getSno() {
        return this.sno;
    }
    
    public void setSno(final int sno) {
        this.sno = sno;
    }
    
    public ApplicationMaster getApplicationMaster() {
        return this.applicationMaster;
    }
    
    public void setApplicationMaster(final ApplicationMaster applicationMaster) {
        this.applicationMaster = applicationMaster;
    }
    
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(final Department department) {
        this.department = department;
    }
}
