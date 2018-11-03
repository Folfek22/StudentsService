package pl.kurs.java.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.kurs.java.model.Degree;
import pl.kurs.java.model.Student;

public class StudentDao {
	public static List<Student> readStudentFromFile(String pattern) throws IOException {
		List<Student> studentsList = new ArrayList<Student>();
		BufferedReader br = new BufferedReader(new FileReader("uczniowie.txt"));
		String line = null;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] args = line.split(";");
			studentsList.add(new Student(Integer.parseInt(args[0]), args[1], args[2], args[3],
					args[4], args[5], new ArrayList<Degree>()));
		}
		br.close();
		return studentsList;
	}
}
