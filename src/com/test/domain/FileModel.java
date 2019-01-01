package com.test.domain;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
	
	private MultipartFile file;
	private String os_id;
	private String exam_date;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getOs_id() {
		return os_id;
	}
	public void setOs_id(String os_id) {
		this.os_id = os_id;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}
	
}