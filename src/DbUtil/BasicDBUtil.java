package DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public abstract class BasicDBUtil {
	
	protected DataSource dataSource;
	
	
	
	
	
	public BasicDBUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}





	protected void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {
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

}
