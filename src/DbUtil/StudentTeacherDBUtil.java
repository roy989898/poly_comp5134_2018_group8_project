package DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Model.TeacherStudent;

public class StudentTeacherDBUtil extends BasicDBUtil {

	public StudentTeacherDBUtil(DataSource dataSource) {
		super(dataSource);

	}

	public List<TeacherStudent> getStudentSteachers() throws SQLException {

		List<TeacherStudent> teacherStudents = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from student_teacher order by id";
			myStatement = myConnection.createStatement();

			// execute SQL query

			myResultSet = myStatement.executeQuery(sql);

			// process the result set
			while (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				String name = myResultSet.getString("name");
				String pwd = myResultSet.getString("pwd");
				String chance = myResultSet.getString("chance");
				String time = myResultSet.getString("time");
				String days = myResultSet.getString("days");
				boolean isTeacher = myResultSet.getBoolean("is_teacher");
				float student_per_course_price = myResultSet.getFloat("student_per_course_price");

				TeacherStudent teacherStudent = new TeacherStudent(name, id, pwd, chance, days, time, isTeacher,
						student_per_course_price);

				teacherStudents.add(teacherStudent);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return teacherStudents;
	}

	public TeacherStudent getStudentSteacherByUserNamePassword(String userName, String password) throws SQLException {

		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		TeacherStudent teacherStudent = null;
		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from student_teacher where name=? AND pwd=?";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setString(1, userName);
			myStatement.setString(2, password);

			myResultSet = myStatement.executeQuery();

			// process the result set

			if (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				String name = myResultSet.getString("name");
				String pwd = myResultSet.getString("pwd");
				String chance = myResultSet.getString("chance");
				String time = myResultSet.getString("time");
				String days = myResultSet.getString("days");
				boolean isTeacher = myResultSet.getBoolean("is_teacher");
				float student_per_course_price = myResultSet.getFloat("student_per_course_price");

				teacherStudent = new TeacherStudent(name, id, pwd, chance, days, time, isTeacher,
						student_per_course_price);

			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return teacherStudent;
	}

	public TeacherStudent getStudentSteacherByUserName(String userName) throws SQLException {

		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		TeacherStudent teacherStudent = null;
		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from student_teacher where name=?";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setString(1, userName);

			myResultSet = myStatement.executeQuery();

			// process the result set

			if (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				String name = myResultSet.getString("name");
				String pwd = myResultSet.getString("pwd");
				String chance = myResultSet.getString("chance");
				String time = myResultSet.getString("time");
				String days = myResultSet.getString("days");
				boolean isTeacher = myResultSet.getBoolean("is_teacher");
				float student_per_course_price = myResultSet.getFloat("student_per_course_price");

				teacherStudent = new TeacherStudent(name, id, pwd, chance, days, time, isTeacher,
						student_per_course_price);

			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return teacherStudent;
	}

	public TeacherStudent getStudentSteacherByID(int id) throws SQLException {

		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		TeacherStudent teacherStudent = null;
		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from student_teacher where id=?";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setInt(1, id);

			myResultSet = myStatement.executeQuery();

			// process the result set

			if (myResultSet.next()) {
				int infID = myResultSet.getInt("id");
				String name = myResultSet.getString("name");
				String pwd = myResultSet.getString("pwd");
				String chance = myResultSet.getString("chance");
				String time = myResultSet.getString("time");
				String days = myResultSet.getString("days");
				boolean isTeacher = myResultSet.getBoolean("is_teacher");
				float student_per_course_price = myResultSet.getFloat("student_per_course_price");

				teacherStudent = new TeacherStudent(name, infID, pwd, chance, days, time, isTeacher,
						student_per_course_price);

			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return teacherStudent;
	}

	public void updateStudentTeacher(TeacherStudent teacherStudent) throws SQLException {
		Connection myConnection = null;
		PreparedStatement myStatement = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "update  student_teacher "
					+ "set name=?,pwd=?,chance=? ,days=?,time=?,is_teacher=?,student_per_course_price=?"
					+ " where id=?";
			myStatement = myConnection.prepareStatement(sql);

			// set the param values for the student
			myStatement.setString(1, teacherStudent.getName());
			myStatement.setString(2, teacherStudent.getPwd());
			myStatement.setString(3, teacherStudent.getChance());
			myStatement.setString(4, teacherStudent.getDays());
			myStatement.setString(5, teacherStudent.getTime());
			myStatement.setBoolean(6, teacherStudent.isTeacher());
			myStatement.setFloat(7, teacherStudent.getStudent_per_course_price());
			myStatement.setInt(8, teacherStudent.getId());

			myStatement.execute();

		} finally {
			close(myConnection, myStatement, null);
		}

	}

}
