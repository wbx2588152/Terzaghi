package com.jk.model;

import java.util.List;

public class Power {
    private String id;

    private String text;

    private String url;

    private String pid;
    
    private Boolean checked;
    
    private List<Power> children;
    
    

    public List<Power> getChildren() {
		return children;
	}

	public void setChildren(List<Power> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

  

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}