package com.bits.groupmembers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentInfoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentId;
	private String email;
	private String fullName;
	private List<String> electiveCourses;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<String> getElectiveCourses() {
		return electiveCourses;
	}
	public void setElectiveCourses(List<String> electiveCourses) {
		this.electiveCourses = electiveCourses;
	}
}
