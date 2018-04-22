package cn.com.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbUtil.ChooseTableDBUtil;
import DbUtil.StudentTeacherDBUtil;
import Model.ChooseTable;
import Model.TeacherStudent;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends BasicController {
	private static final long serialVersionUID = 1L;

	private StudentTeacherDBUtil studentTeacherDBUtil;

	private ChooseTableDBUtil ChooseTableDBUtil;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();

	}

	@Override
	public void init() throws ServletException {

		super.init();

		try {
			studentTeacherDBUtil = new StudentTeacherDBUtil(getDataSource());

			ChooseTableDBUtil = new ChooseTableDBUtil(getDataSource());
		} catch (Exception e) {

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
			TeacherStudent user = studentTeacherDBUtil.getStudentSteacherByUserNamePassword(name, password);

			if (user != null) {
				// out.println("login success: " + user.isTeacher());

				if (user.isTeacher()) {

					// teacher

					List<ChooseTable> stuIdList = ChooseTableDBUtil.getChooseTablesBuTeacherName(name);
					request.setAttribute("message", "Welcome  " + name + " !");
					request.setAttribute("name", name);
					request.setAttribute("stuIdList", stuIdList);
					// TODO handle the my course
					request.setAttribute("mycourse", "temp my course");
					request.getRequestDispatcher("/teacher.jsp").forward(request, response);

				} else {
					// student
				}

			} else {
				// out.println("login faile");
				response.sendRedirect("login.jsp");
			}

		} catch (SQLException e) {

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
