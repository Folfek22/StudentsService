 package pl.kurs.java.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.kurs.java.model.Degree;
import pl.kurs.java.model.Student;
import pl.kurs.java.model.Subject;

public class DegreeDao {

	public static List<Degree> readDegreesFromFile(String fileName, List<Student> students,
			List<Subject> subjects) throws IOException, NumberFormatException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Degree> degreesList = new ArrayList<Degree>();
		BufferedReader br = new BufferedReader(new FileReader("oceny.txt"));
		String line = null;
		br.readLine(); 
		while ((line = br.readLine()) != null) {
			String[] args = line.split(";");
			int studentId = Integer.parseInt(args[0]);
			int subjectId = Integer.parseInt(args[3]);
			degreesList.add(new Degree(findStudent(students, studentId),
					Integer.parseInt(args[1]),
					formatter.parse(args[2]),
					findSubject(subjects, subjectId)));

		}
		br.close();
		return degreesList;
	}

	private static Subject findSubject(List<Subject> subjects, int subjectId) {
		return subjects
				.stream()
				.filter(s -> s.getId() == subjectId)
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException("Subject wtih id: " + subjectId
								+ " not found"));
	}

	private static Student findStudent(List<Student> students, int studentId) {
		return students
				.stream()
				.filter(s -> s.getId() == studentId)
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException("Student with id: " + studentId
								+ " not found"));
	}
} 