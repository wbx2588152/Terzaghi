package com.jk.model;

public class UserToRole {
    private String id;

    private String userId;

    private String roleId;
    
    private String role;
    
    

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}