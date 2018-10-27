package com.jk.model;

import com.jk.model.Page;

public class Menu extends Page{
	private static final long serialVersionUID = 7360199168250588730L;

	private String id;

	private String powerid;

	private String name;

	private String url;

	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPowerid() {
		return powerid;
	}

	public void setPowerid(String powerid) {
		this.powerid = powerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
