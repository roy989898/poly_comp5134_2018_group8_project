package cn.com.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DbUtil.StudentTeacherDBUtil;
import Model.TeacherStudent;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/db")
	private DataSource dataSource;

	private StudentTeacherDBUtil studentTeacherDBUtil;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			studentTeacherDBUtil = new StudentTeacherDBUtil(dataSource);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String password = request.getParameter("pwd");

		try {
			TeacherStudent user = studentTeacherDBUtil.getStudentSteacherByUserNamePassword(name,password);

		
			
			if (user!=null) {
				out.println("login success");
				
			}else {
				out.println("login faile");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		}

		/*
		 * out.println(name);
		 * 
		 * out.println(password);
		 */

	}

}
