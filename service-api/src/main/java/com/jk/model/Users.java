package com.jk.model;

import java.util.Date;
import java.util.List;

public class Users {
    private String id;

    private String name;

    private String loginnumber;

    private String password;

    private String deptId;
    
    private String dept;

    private Integer status;

    private Integer sex;

    private Date birthday;
    
    private String atime1;
    
    private String atime2;
    

    private String email;

    private String roleId;
    
    private String role;

    private String detail;
    
    private List<UserToRole> myrole;
    
    
   
	public List<UserToRole> getMyrole() {
		return myrole;
	}

	public void setMyrole(List<UserToRole> myrole) {
		this.myrole = myrole;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAtime1() {
		return atime1;
	}

	public void setAtime1(String atime1) {
		this.atime1 = atime1;
	}

	public String getAtime2() {
		return atime2;
	}

	public void setAtime2(String atime2) {
		this.atime2 = atime2;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    

    public String getLoginnumber() {
		return loginnumber;
	}

	public void setLoginnumber(String loginnumber) {
		this.loginnumber = loginnumber;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}