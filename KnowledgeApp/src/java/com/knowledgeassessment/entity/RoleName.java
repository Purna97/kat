// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TBL_ATTENDANCE_EMP_ROLENAME")
public class RoleName
{
    @Id
    @Column(name = "r_id")
    private int r_id;
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "role_name")
    private String role_name;
    
    public int getR_id() {
        return this.r_id;
    }
    
    public void setR_id(final int r_id) {
        this.r_id = r_id;
    }
    
    public int getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(final int role_id) {
        this.role_id = role_id;
    }
    
    public String getRole_name() {
        return this.role_name;
    }
    
    public void setRole_name(final String role_name) {
        this.role_name = role_name;
    }
}
