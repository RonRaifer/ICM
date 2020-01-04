package entity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import common.MyFile;

public class Document implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iddocument; // this attribute will be set in server side
	private String fileName;
	private String fileType;
	private String path;
	private String idrequest;
	private MyFile myFile;

	public void setMyFile(MyFile myFile) {
		this.myFile = myFile;
	}

	public String getIddocument() {
		return iddocument;
	}

	public void setIddocument(String iddocument) {
		this.iddocument = iddocument;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIdrequest() {
		return idrequest;
	}

	public void setIdrequest(String idrequest) {
		this.idrequest = idrequest;
	}

	public MyFile getMyFile() {
		MyFile msg = new MyFile(path);
		String LocalfilePath = path;

		try {
			File newFile = new File(LocalfilePath);
			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			msg.initArray(mybytearray.length);
			msg.setSize(mybytearray.length);

			bis.read(msg.getMybytearray(), 0, mybytearray.length);
		} catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		}
		return msg;
	}

}
