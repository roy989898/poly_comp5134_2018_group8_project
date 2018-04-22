package cn.com.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
					request.setAttribute("mycourse", "temp my course");
					request.getRequestDispatcher("/teacher.jsp").forward(request, response);

				} else {
					// student

					List<ChooseTable> stuIdList = ChooseTableDBUtil.getChooseTablesByStudentID(user.getId()); // ѧ����ѡ�γ�list
					StringBuffer courseStr = new StringBuffer(); // �洢ѧ���Ѿ�ѡ�Ŀγ�
					for (int i = 0; i < stuIdList.size(); i++) {
						courseStr.append(stuIdList.get(i).getTeachercourse());
						if (i < stuIdList.size() - 1) {
							courseStr.append(","); // �ö��Ÿ�
						}
					}
					// �õ�����ѡ��Ŀγ��б�
					List<String> ableList = new ArrayList<String>();
					for (int i = 1; i <= 30; i++) {
						ableList.add("course" + i); // ��30���γ�
					}
					for (int i = 0; i < stuIdList.size(); i++) { // ��ѡ�Ŀγ̲������ڴ�ѡ�Ŀγ���
						for (int j = 0; j < ableList.size(); j++) {
							if (ableList.get(j).equals(stuIdList.get(i).getTeachercourse())) {
								ableList.remove(j);
								j--;
							}
						}
					}

					request.setAttribute("message", "Welcome  " + name + " !"); // ��request���з�����Ϣ
					request.setAttribute("name", user.getName());
					request.setAttribute("studentId", user.getId());
					request.setAttribute("chance", user.getChance());
					request.setAttribute("days", user.getDays());
					request.setAttribute("courseStr", courseStr);
					request.setAttribute("ableList", ableList);
					request.getRequestDispatcher("/selectCourse.jsp").forward(request, response);// ת�����ɹ�ҳ��

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
