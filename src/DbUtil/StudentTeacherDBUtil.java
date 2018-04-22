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

				TeacherStudent teacherStudent = new TeacherStudent(name, id, pwd, chance, days, time, isTeacher);

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

				teacherStudent = new TeacherStudent(name, id, pwd, chance, days, time, isTeacher);

			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return teacherStudent;
	}

}
