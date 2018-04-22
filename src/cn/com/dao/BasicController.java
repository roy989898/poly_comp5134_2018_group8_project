package cn.com.dao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public abstract class BasicController extends HttpServlet {
	
	@Resource(name = "jdbc/db")
	private DataSource dataSource;

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	
	

}
