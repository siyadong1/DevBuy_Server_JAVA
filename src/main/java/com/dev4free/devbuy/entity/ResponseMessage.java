package com.dev4free.devbuy.entity;

/**
 * 消息返回的实体类
 * @author syd
 *
 */
public class ResponseMessage {
	

	private String code = "000000";
	private Object content = "正常!";
	
	
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", content=" + content + "]";
	}

	
	
	
	
}
