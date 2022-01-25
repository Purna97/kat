// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Temp_TBL_ATTENDANCE_USERS")
public class Users
{
    @Id
    @GeneratedValue
    @Column(name = "UserID")
    private int userID;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Pwd")
    private String pwd;
    @Column(name = "FirstName")
    private String firstName;
  
    @Column(name = "LastName")
    private String lastName;
    
    @Column(name = "Email")
    private String email;
    @Column(name = "ContactNo")
    private String contactNo;
   
    @Column(name = "Status")
    private int status;
  
    @Column(name = "designation")
    private String designation;
    @Column(name = "dept_id")
    private String dept_id;
    @Column(name = "emp_type")
    private String emp_type;
    @Column(name = "employee_role")
    private String employee_role;
   
    @Column(name = "submit_date")
    private String submit_date;
    
	@Column(name = "emp_number")
    private int emp_number;
    
    @Column(name="flag")
	private Integer flag;
    
    @ManyToOne
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    private TestDetails test;
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public TestDetails getTest() {
		return test;
	}

	public void setTest(TestDetails test) {
		this.test = test;
	}
    
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getEmp_type() {
		return emp_type;
	}

	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}

	public String getEmployee_role() {
		return employee_role;
	}

	public void setEmployee_role(String employee_role) {
		this.employee_role = employee_role;
	}

	public String getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}

	public int getEmp_number() {
		return emp_number;
	}

	public void setEmp_number(int emp_number) {
		this.emp_number = emp_number;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
  
}
