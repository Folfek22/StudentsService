package pl.kurs.java.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
	private int id;
	private String subjectName;
	private String teacherSurname;
	private String teacherName;
	private List<Degree> degrees;

}
