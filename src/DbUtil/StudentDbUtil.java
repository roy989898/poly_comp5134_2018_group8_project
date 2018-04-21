package DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sun.xml.internal.ws.Closeable;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from student order by last_name";
			myStatement = myConnection.createStatement();

			// execute SQL query

			myResultSet = myStatement.executeQuery(sql);

			// process the result set
			while (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				String firstName = myResultSet.getString("first_name");
				String lastName = myResultSet.getString("last_name");
				String email = myResultSet.getString("email");

				Student student = new Student(id, firstName, lastName, email);

				students.add(student);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return students;
	}

	public void addStudent(Student theStudent) throws Exception {

		Connection myConnection = null;
		PreparedStatement myStatement = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "insert into student" + "(first_name,last_name,email)" + "values(?,?,?)";
			myStatement = myConnection.prepareStatement(sql);

			// set the param values for the student
			myStatement.setString(1, theStudent.getFirstName());
			myStatement.setString(2, theStudent.getLastname());
			myStatement.setString(3, theStudent.getEmail());

			myStatement.execute();

		} finally {
			close(myConnection, myStatement, null);
		}

	}

	private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {
		try {

			if (myConnection != null)
				myConnection.close();

			if (myStatement != null)
				myStatement.close();

			if (myResultSet != null)
				myResultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Student getStudent(String studentID) throws Exception {

		Student theStudent = null;
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from student where id=?";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setInt(1, Integer.parseInt(studentID));

			myResultSet = myStatement.executeQuery();

			// process the result set
			if (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				String firstName = myResultSet.getString("first_name");
				String lastName = myResultSet.getString("last_name");
				String email = myResultSet.getString("email");

				theStudent = new Student(id, firstName, lastName, email);

			} else {
				throw new Exception("no found" + studentID);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return theStudent;
	}

	public void updateStudent(Student theStudent) throws Exception {
		Connection myConnection = null;
		PreparedStatement myStatement = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "update  student " + "set first_name=?,last_name=?,email=? " + "where id=?";
			myStatement = myConnection.prepareStatement(sql);

			// set the param values for the student
			myStatement.setString(1, theStudent.getFirstName());
			myStatement.setString(2, theStudent.getLastname());
			myStatement.setString(3, theStudent.getEmail());
			myStatement.setInt(4, theStudent.getId());
			myStatement.execute();

		} finally {
			close(myConnection, myStatement, null);
		}

	}

	public void deleteStudent(String studentId) throws Exception {

		Connection myConnection = null;
		PreparedStatement myStatement = null;

		try {

			int id = Integer.parseInt(studentId);
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "delete from student " + "where id=?";
			myStatement = myConnection.prepareStatement(sql);

			// set the param values for the student
			myStatement.setInt(1, id);

			myStatement.execute();

		} finally {
			close(myConnection, myStatement, null);
		}
	}

}
