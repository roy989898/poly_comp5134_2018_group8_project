package DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Model.Couse;
import Model.TeacherStudent;

public class ChooseTableDBUtil extends BasicDBUtil {

	public ChooseTableDBUtil(DataSource dataSource) {
		super(dataSource);

	}

	public List<Couse> getChooseTables() throws SQLException {

		List<Couse> chooseTables = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from choose_table order by id";
			myStatement = myConnection.createStatement();

			// execute SQL query

			myResultSet = myStatement.executeQuery(sql);

			// process the result set
			while (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				int studentid = myResultSet.getInt("studentid");
				int teacherid = myResultSet.getInt("teacherid");
				String teachername = myResultSet.getString("teachername");
				String teachercourse = myResultSet.getString("teachercourse");

				Couse chooseTable = new Couse(id, studentid, teacherid, teachername, teachercourse);

				chooseTables.add(chooseTable);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return chooseTables;
	}

	public List<Couse> getChooseTablesBuTeacherName(String teacherName) throws SQLException {

		List<Couse> chooseTables = new ArrayList<>();

		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from choose_table where teachername=? order by id";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setString(1, teacherName);

			myResultSet = myStatement.executeQuery();

			// process the result set
			while (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				int studentid = myResultSet.getInt("studentid");
				int teacherid = myResultSet.getInt("teacherid");
				String teachername = myResultSet.getString("teachername");
				String teachercourse = myResultSet.getString("teachercourse");

				Couse chooseTable = new Couse(id, studentid, teacherid, teachername, teachercourse);

				chooseTables.add(chooseTable);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return chooseTables;
	}

	public List<Couse> getChooseTablesByStudentID(int studentID) throws SQLException {

		List<Couse> chooseTables = new ArrayList<>();

		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "select * from choose_table where studentid=? order by id";
			myStatement = myConnection.prepareStatement(sql);
			myStatement.setInt(1, studentID);

			myResultSet = myStatement.executeQuery();

			// process the result set
			while (myResultSet.next()) {
				int id = myResultSet.getInt("id");
				int studentid = myResultSet.getInt("studentid");
				int teacherid = myResultSet.getInt("teacherid");
				String teachername = myResultSet.getString("teachername");
				String teachercourse = myResultSet.getString("teachercourse");

				Couse chooseTable = new Couse(id, studentid, teacherid, teachername, teachercourse);

				chooseTables.add(chooseTable);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return chooseTables;
	}

	public void insetCourse(Couse course) throws Exception {

		Connection myConnection = null;
		PreparedStatement myStatement = null;

		try {
			myConnection = dataSource.getConnection();
			// create a SQL statment
			String sql = "insert into choose_table" + "(studentid, teacherid,teachername,teachercourse)"
					+ "values(?,?,?,?)";
			myStatement = myConnection.prepareStatement(sql);

			// set the param values for the student
			myStatement.setInt(1, course.getStudentid());
			myStatement.setInt(2, course.getTeacherid());
			myStatement.setString(3, course.getTeachername());
			myStatement.setString(4, course.getTeachercourse());

			myStatement.execute();

		} finally {
			close(myConnection, myStatement, null);
		}

	}

}
