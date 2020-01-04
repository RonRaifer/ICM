package entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;




public class Messages implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idMessage;
	private String idUser;
	private String titleMessage;
	private String contentMessage;
	private String status;
	
	
	
	
	public String getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getTitleMessage() {
		return titleMessage;
	}
	public void setTitleMessage(String titleMessage) {
		this.titleMessage = titleMessage;
	}
	public String getContentMessage() {
		return contentMessage;
	}
	public void setContentMessage(String contentMessage) {
		this.contentMessage = contentMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<String> getAll(){
		List<String> list = new ArrayList<>();
	
		list.add(idMessage);
		list.add(idUser);
		list.add(titleMessage);
		list.add(contentMessage);
		list.add(status);
		
		return list;
	}
	
	
	
}

	
	
	

