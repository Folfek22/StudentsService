package pl.kurs.java.model;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "degrees")
@EqualsAndHashCode(exclude = "degrees")
public class Student {
	private int id;
	private String surname;
	private String name;
	private String street;
	private String house;
	private String className;
	private List<Degree> degrees;

	public double averageDegree() {
		return degrees.stream().collect(Collectors.averagingDouble(Degree::getDegree));
	}

}
