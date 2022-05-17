package studentmanagement.dto;

import java.time.LocalDate;

public class StudentResponseDTO {

	private String student_id;
	private String student_name;
	private String class_name;
	private LocalDate date;
	private String year;
	private String month;
	private String day;
	private String status;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getDate() {
		return date.toString();
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "StudentResponseDTO [student_id=" + student_id + ", student_name=" + student_name + ", class_name="
				+ class_name + ", date=" + date + ", status=" + status + "]";
	}
}
