package com.dev4free.devbuy.po;

public class Feedback {

	//Feedback类的属性对于feedback表的列名
	private String feedback_id;
	private String user_id;
	private String content;
	private String createtime;
	public String getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(String feedback_id) {
		this.feedback_id = feedback_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Feedback [feedback_id=" + feedback_id + ", user_id=" + user_id + ", content=" + content
				+ ", createtime=" + createtime + "]";
	}
	

	
}
