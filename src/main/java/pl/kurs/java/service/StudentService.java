package pl.kurs.java.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.kurs.java.model.Degree;
import pl.kurs.java.model.Student;

public class StudentService {
	public static Student bestAverage(List<Student> students) {

		if (students == null) {
			throw new NullPointerException();
		}
		return students.stream()
				.max((s1, s2) -> Double.compare(s1.averageDegree(), s2.averageDegree()))
				.orElse(null);
	}

	public static long studentsOutsideTheRegion(List<Student> studentsList) {
		if (studentsList == null) {
			throw new NullPointerException();
		}
		return studentsList.stream()
				.filter(s -> s.getStreet().equals("Worcella") || s.getStreet().equals("Sportowa"))
				.count();
	}

	public static List<Integer> degreesStudentfromSubject(List<Student> studentsList, String name,
			String surname, String subjectName) {
		if (studentsList == null) {
			throw new NullPointerException();
		}
		Student student = studentsList
				.stream()
				.filter(s -> s.getName().equals(name) && s.getSurname().equals(surname))
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException("Student : " + name + surname
								+ " not found"));
		return student.getDegrees().stream()
				.filter(d -> d.getSubject().getSubjectName().equals(subjectName))
				.map(Degree::getDegree).collect(Collectors.toList());
	}

	public static void setGirlsAndBoysInClass(List<Student> studentsList) {
		if (studentsList == null) {
			throw new NullPointerException();
		}
		List<String> classes = new ArrayList<String>();
		for (Student s : studentsList) {
			if (!classes.contains(s.getClassName()))
				classes.add(s.getClassName());
		}
		Collections.sort(classes);
		System.out.println(" Class " + " " + " Girls " + " " + " Boys ");
		for (int i = 0; i < classes.size(); i++) {
			int girlsQuantity = 0;
			int boysQuantity = 0;
			for (Student s : studentsList) {
				if (s.getClassName().equals(classes.get(i)) && s.getName().endsWith("a")) {
					girlsQuantity++;
				}
				if (s.getClassName().equals(classes.get(i)) && !s.getName().endsWith("a")) {
					boysQuantity++;
				}
			}
			System.out.println("   " + classes.get(i) + "      " + girlsQuantity + "      "
					+ boysQuantity);
		}
	}

	public static void averagesFromAllSubjectsAnyClass(List<Student> studentsList, String clas) {
		if (studentsList == null) {
			throw new NullPointerException();
		}
		List<Student> studentsFromAnyClass = getAllStudentsFromClass(studentsList, clas);
		List<Degree> degreesListofClass = getAllDegreesFromClass(studentsFromAnyClass);
		List<String> subjects = getStringSubjectName(degreesListofClass);
		Map<String, Double> subjectsAveragesSet = getMapSubjectAverage(degreesListofClass, subjects);
		sortAndViewofMap(subjectsAveragesSet);

	}

	public static void setStudensWithDegree1FromAnyClassAndDate(List<Student> studentsList,
			String clas, String date) {
		if (studentsList == null) {
			throw new IllegalArgumentException();
		}
		List<Student> studentsFromClass = getAllStudentsFromClass(studentsList, clas);
		List<Degree> degreesStudents = getAllDegreesFromClass(studentsFromClass);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<String> result = new ArrayList<String>();

		for (Degree d : degreesStudents) {
			if (d.getDegree() == 1 && formatter.format(d.getDate()).contains(date)) {
				String name = d.getStudent().getName() + " " + d.getStudent().getSurname();
				result.add(name + " -> " + d.getSubject().getSubjectName());
			}

		}
		System.out.println("");

		result.forEach((v) -> System.out.println(v));
	}

	private static void sortAndViewofMap(Map<String, Double> subjectsAveragesSet) {

		System.out.println("");
		System.out.println("  Przedmiot  " + "  Średnia");
		System.out.println(" ");
		subjectsAveragesSet
				.entrySet()
				.stream()
				.sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
				.forEach(
						(v) -> System.out.format("  " + v.getKey() + "       " + "%.2f%n",
								v.getValue()));
	}

	private static Map<String, Double> getMapSubjectAverage(List<Degree> degreeListofClass,
			List<String> subjects) {

		Map<String, Double> subjectsAveragesSet = new HashMap<String, Double>();
		for (int i = 0; i < subjects.size(); i++) {
			double temp = 0;
			double sum = 0;
			double average = 0;
			for (Degree d : degreeListofClass) {
				if (d.getSubject().getSubjectName().equals(subjects.get(i))) {
					sum += d.getDegree();
					temp++;
				}

			}
			average = sum / temp;
			subjectsAveragesSet.put(subjects.get(i), average);
		}
		return subjectsAveragesSet;
	}

	private static List<Degree> getAllDegreesFromClass(List<Student> studentFromAnyClass) {

		List<Degree> degreeListofClass = new ArrayList<Degree>();

		for (Student s : studentFromAnyClass) {
			for (int i = 0; i < s.getDegrees().size(); i++) {
				degreeListofClass.add(s.getDegrees().get(i));
			}
		}
		return degreeListofClass;
	}

	private static List<Student> getAllStudentsFromClass(List<Student> studentList, String clas) {

		List<Student> studentFromAnyClass = studentList.stream()
				.filter(s -> s.getClassName().equals(clas)).collect(Collectors.toList());
		return studentFromAnyClass;
	}

	private static List<String> getStringSubjectName(List<Degree> degreeListofClass) {

		List<String> subjects = new ArrayList<String>();
		for (Degree d : degreeListofClass) {
			if (!subjects.contains(d.getSubject().getSubjectName())) {
				subjects.add(d.getSubject().getSubjectName());
			}
		}
		return subjects;
	}

}
