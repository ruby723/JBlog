package com.douzone.jblog.vo;

public class CategoryVo {
	private int no;
	private String title;
	private String desc;
	private String reg_date;
	private String user_id;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", title=" + title + ", desc=" + desc + ", reg_date=" + reg_date + ", user_id="
				+ user_id + "]";
	}
	
}
