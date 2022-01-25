// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import java.util.LinkedList;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TBL_ASMT_applicationmaster")
public class ApplicationMaster
{
    @Id
    @GeneratedValue
    @Column(name = "applicationid")
    private int applicationid;
    
    @Column(name = "applicationname")
    private String applicationname;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicationMaster")
    private List<ApplicationDept> applicationDept;
    
    public ApplicationMaster() {
        this.applicationDept = new LinkedList<ApplicationDept>();
    }
    
    public int getApplicationid() {
        return this.applicationid;
    }
    
    public void setApplicationid(final int applicationid) {
        this.applicationid = applicationid;
    }
    
    public String getApplicationname() {
        return this.applicationname;
    }
    
    public void setApplicationname(final String applicationname) {
        this.applicationname = applicationname;
    }
    
    public List<ApplicationDept> getApplicationDept() {
        return this.applicationDept;
    }
    
    public void setApplicationDept(final List<ApplicationDept> applicationDept) {
        this.applicationDept = applicationDept;
    }
}
