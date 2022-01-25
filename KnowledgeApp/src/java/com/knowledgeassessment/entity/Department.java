// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TBL_OBT_EMPLOYEE_DEPARTMENT")
public class Department
{
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private int dept_id;
    @Column(name = "dept_name")
    private String dept_name;
    
    public int getDept_id() {
        return this.dept_id;
    }
    
    public void setDept_id(final int dept_id) {
        this.dept_id = dept_id;
    }
    
    public String getDept_name() {
        return this.dept_name;
    }
    
    public void setDept_name(final String dept_name) {
        this.dept_name = dept_name;
    }
}
