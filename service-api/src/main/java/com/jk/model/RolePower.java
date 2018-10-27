package com.jk.model;

import java.io.Serializable;

public class RolePower implements Serializable{
	
    private static final long serialVersionUID = -1682148011483869892L;

	private String id;

    private String roleId;

    private String powerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

   
}