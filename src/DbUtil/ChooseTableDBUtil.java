package DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Model.ChooseTable;
import Model.TeacherStudent;

public class ChooseTableDBUtil extends BasicDBUtil {

	public ChooseTableDBUtil(DataSource dataSource) {
		super(dataSource);
		
	}
	
	
	public List<ChooseTable> getChooseTables() throws SQLException {

		List<ChooseTable> chooseTables = new ArrayList<>();

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
				String teachername=myResultSet.getString("teachername");
				String teachercourse=myResultSet.getString("teachercourse");
				

				
				ChooseTable	chooseTable=new ChooseTable(id, studentid, teacherid, teachername, teachercourse);
				
				

				chooseTables.add(chooseTable);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return chooseTables;
	}
	
	public List<ChooseTable> getChooseTablesBuTeacherName(String teacherName) throws SQLException {

		List<ChooseTable> chooseTables = new ArrayList<>();

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
				String teachername=myResultSet.getString("teachername");
				String teachercourse=myResultSet.getString("teachercourse");
				

				
				ChooseTable	chooseTable=new ChooseTable(id, studentid, teacherid, teachername, teachercourse);
				
				

				chooseTables.add(chooseTable);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return chooseTables;
	}
	
	
	public List<ChooseTable> getChooseTablesByStudentID(int studentID) throws SQLException {

		List<ChooseTable> chooseTables = new ArrayList<>();

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
				String teachername=myResultSet.getString("teachername");
				String teachercourse=myResultSet.getString("teachercourse");
				

				
				ChooseTable	chooseTable=new ChooseTable(id, studentid, teacherid, teachername, teachercourse);
				
				

				chooseTables.add(chooseTable);
			}
		} finally {
			close(myConnection, myStatement, myResultSet);
		}

		return chooseTables;
	}
	
	
	
	

}
