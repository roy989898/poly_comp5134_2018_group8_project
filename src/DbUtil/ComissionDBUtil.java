package DbUtil;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import Model.Course;
import Model.TeacherStudent;

public class ComissionDBUtil extends BasicDBUtil {
	private StudentTeacherDBUtil studentTeacherDBUtil;

	private ChooseTableDBUtil ChooseTableDBUtil;

	public ComissionDBUtil(DataSource dataSource) {
		super(dataSource);
		studentTeacherDBUtil = new StudentTeacherDBUtil(dataSource);

		ChooseTableDBUtil = new ChooseTableDBUtil(dataSource);

	}

	public float getComissionBYTeacherID(int teacherID) throws SQLException {

		List<Course> courseInfList = ChooseTableDBUtil.getChooseTablesByTeacherID(teacherID);

		float totlaComission = 0;
		for (Course courseInf : courseInfList) {
			// use the courses get the student id
			int studentID = courseInf.getStudentid();
			// use studetn id get the per course price
			TeacherStudent student = studentTeacherDBUtil.getStudentSteacherByID(studentID);
			float aStudentCommision = student.getStudent_per_course_price() * 50 / 100;
			totlaComission = totlaComission + aStudentCommision;
		}

		return totlaComission;

	}

}
