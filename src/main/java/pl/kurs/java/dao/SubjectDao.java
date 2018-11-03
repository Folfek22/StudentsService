package pl.kurs.java.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.kurs.java.model.Degree;
import pl.kurs.java.model.Subject;


public class SubjectDao {
	public static List<Subject> readSubjectFromFile(String fileName) throws IOException {
		List<Subject> subjectList = new ArrayList<Subject>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = null;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] args = line.split(";");
			subjectList.add(new Subject(Integer.parseInt(args[0]), args[1], args[2], args[3], new ArrayList<Degree>()));
		}
		br.close();
		return subjectList;
	}
}
