package pl.kurs.java.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.kurs.java.dao.DegreeDao;
import pl.kurs.java.dao.StudentDao;
import pl.kurs.java.dao.SubjectDao;
import pl.kurs.java.model.Degree;
import pl.kurs.java.model.Student;
import pl.kurs.java.model.Subject;
import pl.kurs.java.service.StudentService;

public class PlKursJavaTest {

	@Test
	public void shouldReadDataAndReturnSuitableSize() throws IOException, NumberFormatException,
			ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		// then
		Assert.assertEquals(16965, degreesList.size());
		Assert.assertEquals(414, studentsList.size());
		Assert.assertEquals(11, subjectsList.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenSubjectsNotFound() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = new ArrayList<Subject>();
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		// then
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenStudentNotFound() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = new ArrayList<Student>();
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		// then
	}

	@Test
	public void shouldReturnStudentWithBestAverage() throws IOException, NumberFormatException,
			ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		Student s = StudentService.bestAverage(studentsList);
		// then
		Assert.assertEquals(253, s.getId());
	}

	@Test(expected = NullPointerException.class)
	public void shouldReturnStudentWithBestAverageException() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> nullList = null;
		// when
		Student s = StudentService.bestAverage(nullList);
		// then
	}

	@Test
	public void shouldReturn15WithMethodStudentsOutsideTheRegion() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		long result = StudentService.studentsOutsideTheRegion(studentsList);
		// then
		Assert.assertEquals(15, result);
	}

	@Test(expected = NullPointerException.class)
	public void shouldReturn15WithMethodStudentsOutsideTheRegionException() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = null;
		// when
		long result = StudentService.studentsOutsideTheRegion(studentsList);
		// then
	}

	@Test
	public void shouldReturnDegreesOfStudent() throws IOException, NumberFormatException,
			ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowe.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		List<Integer> result = StudentService.degreesStudentfromSubject(studentsList, "Jan",
				"Augustyniak", "polski");
		// then
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.containsAll(Arrays.asList(3, 1, 1)));
	}

	@Test(expected = NullPointerException.class)
	public void shouldReturnDegreesOfStudentNullPionterException() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> nullList = null;
		// when
		List<Integer> result = StudentService.degreesStudentfromSubject(nullList, "Jan",
				"Augustyniak", "polski");
		// then

	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnDegreesOfStudentIllegalArgumentException() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		List<Integer> result = StudentService.degreesStudentfromSubject(studentsList, "Jajco",
				"sasa", "polski");
		// then

	}

	@Test
	public void shouldDrawSetBoysAndGirlsInClass() throws IOException, NumberFormatException,
			ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		StudentService.setGirlsAndBoysInClass(studentsList);
		// then
	}

	@Test(expected = NullPointerException.class)
	public void shouldDrawSetBoysAndGirlsInClassNullPointerException() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> nullList = null;
		// when
		StudentService.setGirlsAndBoysInClass(nullList);
		// then
	}

	@Test
	public void shouldDrawAveragesFromAllSubjectsAnyClass() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		// when
		StudentService.averagesFromAllSubjectsAnyClass(studentsList, "2c");
	}

	@Test(expected = NullPointerException.class)
	public void shouldDrawAveragesFromAllSubjectsAnyClassNullPointerException() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = null;

		// when
		StudentService.averagesFromAllSubjectsAnyClass(studentsList, "2c");
	}

	@Test
	public void shouldDrawSetWithStudentsDegrees1OfAnyClassAndDate() throws IOException,
			NumberFormatException, ParseException {
		// given
		List<Student> studentsList = StudentDao.readStudentFromFile("uczniowie.txt");
		List<Subject> subjectsList = SubjectDao.readSubjectFromFile("przedmioty.txt");
		List<Degree> degreesList = DegreeDao.readDegreesFromFile("oceny.txt", studentsList,
				subjectsList);
		StudentService.setStudensWithDegree1FromAnyClassAndDate(studentsList, "2c", "2009-04");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldDrawSetWithStudentsDegrees1OfAnyClassAndDateNullPointerException()
			throws IOException, NumberFormatException, ParseException {
		// given
		List<Student> studentsList = null;

		StudentService.setStudensWithDegree1FromAnyClassAndDate(studentsList, "2c", "2009-04");
	}
}
