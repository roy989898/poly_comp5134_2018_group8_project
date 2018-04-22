<%@page import="Model.Couse"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>select courses</title>
<script type="text/javascript" src="jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="css/style2.css">


<%
	List<Couse> stuIdList = (List<Couse>) request.getAttribute("stuIdList");
%>
</head>

<body style="text-align: center; margin-left: auto; margin-right: auto;">
	<a style="float: right; margin-right: 30px;" href="login.jsp">Log
		out</a>
	<br>
	<div style="margin-top: 50px;">

		<h1>${message}</h1>
		<%-- <div style="margin-top: 30px;">
			<span>Name:${name} </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>course:${mycourse}</span>
		</div> --%>



		<div style="margin-top: 50px;">
			<p id="student">your courses</p>
			<table class="custom_tb">
				<thead>
					<tr>
						<th>Courses</th>
					</tr>
				</thead>

				<%-- 	<c:forEach var="stu" items="${stuIdList}">
					<tr>
						<td style="margin-left: 40px">student${stu.studentid}</td>
					</tr>
				</c:forEach> --%>


				<%
					for (Couse student : stuIdList) {
				%>
				<tr>
					<td style="margin-left: 40px"><%=student.getTeachercourse()%></td>
				</tr>

				<%
					}
				%>

			</table>
		</div>



		<div style="margin-top: 50px;">
			<p id="student">your students</p>
			<table class="custom_tb">
				<thead>
					<tr>
						<th>students</th>
					</tr>
				</thead>

				<%-- 	<c:forEach var="stu" items="${stuIdList}">
					<tr>
						<td style="margin-left: 40px">student${stu.studentid}</td>
					</tr>
				</c:forEach> --%>


				<%
					for (Couse student : stuIdList) {
				%>
				<tr>
					<td style="margin-left: 40px"><%=student.getStudentid()%></td>
				</tr>


				<%
					}
				%>

			</table>
		</div>

	</div>
</body>
<script>
	
</script>
</html>
