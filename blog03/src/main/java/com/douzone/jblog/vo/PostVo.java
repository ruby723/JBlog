package com.douzone.jblog.vo;

public class PostVo {
	private int no;
	private String title;
	private String reg_date;
	private String contents;
	private int category_no;
	
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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", reg_date=" + reg_date + ", contents=" + contents
				+ ", category_no=" + category_no + "]";
	}
}
