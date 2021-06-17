package ucb.microservice.springboot.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ADMISSION_EXAM")
public class AdmissionExam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "career")
	private String career;
	
	@Column(name = "course")
	private String course;
	
	@Column(name = "date_exam")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateExam;
	
	@Column(name = "faculty")
	private String faculty;
	
	@Column(name = "period")
	private String period;
	
	@Column(name = "semester")
	private int semester;

	public AdmissionExam() {
		super();
	}

	public AdmissionExam(String career, String course, Date dateExam, String faculty, String period, int semester) {
		super();
		this.career = career;
		this.course = course;
		this.dateExam = dateExam;
		this.faculty = faculty;
		this.period = period;
		this.semester = semester;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getDateExam() {
		return dateExam;
	}

	public void setDateExam(Date dateExam) {
		this.dateExam = dateExam;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "AdmissionExam [id=" + id + ", career=" + career + ", course=" + course + ", dateExam=" + dateExam
				+ ", faculty=" + faculty + ", period=" + period + ", semester=" + semester + "]";
	}
	
}
