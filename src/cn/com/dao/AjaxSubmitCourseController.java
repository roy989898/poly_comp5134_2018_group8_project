package cn.com.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbUtil.ChooseTableDBUtil;
import DbUtil.StudentTeacherDBUtil;
import Model.Course;
import Model.TeacherStudent;

@WebServlet("/AjaxSubmitCourseController")
public class AjaxSubmitCourseController extends BasicController {
	private StudentTeacherDBUtil studentTeacherDB;
	private ChooseTableDBUtil chooseTableDB;

	// ??????HttpServlet ?????doGet doPost????
	public void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		doPost(request, response); // ????????doPost??????? ???jsp??????form?????method
	}

	@Override
	public void init() throws ServletException {

		super.init();
		studentTeacherDB = new StudentTeacherDBUtil(getDataSource());
		chooseTableDB = new ChooseTableDBUtil(getDataSource());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		String studentName = request.getParameter("studentName");
		String courses = request.getParameter("courses");
		String chance = request.getParameter("chance"); // ????????

		List<String> courseList = null;
		if (courses != null) {
			courseList = Arrays.asList(courses.split(","));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String DateStr = sdf.format(new Date());
		try {
			// buy chance
			TeacherStudent student = studentTeacherDB.getStudentSteacherByUserName(studentName);
			if (chance != null) { // ???????
				int newChance = Integer.parseInt(student.getChance()) + Integer.parseInt(chance);
				student.setChance(newChance + "");
				student.setDays(90 + "");
				student.setTime(DateStr);
				/*
				 * dao.update(studentId, null, 90+"", DateStr); dao.update(studentId,
				 * newChance+"", null, null);
				 */

				studentTeacherDB.updateStudentTeacher(student);

				out.print("succeed!"); // ??????�^???????????
				out.close();
			} else if (Integer.parseInt(student.getChance()) <= 0) {

				out.print("sorry, your chance is 0!");
				out.close();
			} else if (Integer.parseInt(student.getDays()) <= 0) {

				out.print("sorry, your days is 0!");
				out.close();
			} else if (Integer.parseInt(student.getChance()) - courseList.size() < 0) {

				out.print("sorry, your chance is not enough!"); // ??????�^???????????
				out.close();
			} else {

				//
				// TODO select course

				for (int i = 0; i < courseList.size(); i++) {

					String course = courseList.get(i);
					int teacherId = 0;
					if (course.length() == 7) {
						teacherId = Integer.parseInt(course.substring(course.length() - 1, course.length()));
					} else {
						teacherId = Integer.parseInt(course.substring(course.length() - 2, course.length()));
					}

					Course courseObject = new Course(studentId, teacherId, "teacher" + teacherId, course);
					chooseTableDB.insetCourse(courseObject);

				}
				int newChance = Integer.parseInt(student.getChance()) - courseList.size();
				student.setChance(newChance + "");

				studentTeacherDB.updateStudentTeacher(student);

				// response.setContentType("text/xml;charset=UTF-8"); PrintWriter out =
				response.getWriter();
				out.print("ok");
				out.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
