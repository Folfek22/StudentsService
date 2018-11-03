package pl.kurs.java.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Degree {
	private Student student;
	private int degree;
	private Date date;
	private Subject subject;
	 
	@Builder
	public Degree(Student student, int degree, Date date, Subject subject) {
		this.student = student;
		this.degree = degree;
		this.date = date;
		this.subject = subject;
		this.student.getDegrees().add(this);
		this.subject.getDegrees().add(this);
	}
	
	
	
}
