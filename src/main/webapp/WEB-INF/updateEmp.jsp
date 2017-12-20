<%@page import="entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
		<div id="wrap">
			<div id="top_content">
					<%@ include file="/WEB-INF/header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						修改员工:
					</h1>
					<%
						Employee e=(Employee)request.getAttribute("e");
					%>
					<form action="update.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							
							<tr>
								<td valign="middle" align="right">
									id:
								</td>
								<td valign="middle" align="left">
									<%=e.getId()%>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="ename" value="<%=e.getEname()%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									薪水:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="salary" value="<%= e.getSalary()%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									年龄:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="age" value="<%= e.getAge()%>"/>
								</td>
							</tr>
							
						</table>
						<p>
							<input type="submit" class="button" value="确定" />
							<input type="hidden" name="id" value="<%= e.getId()%>"/>
						</p>
					</form>
				</div>
			</div>
			<%@ include file="/WEB-INF/footer.jsp" %>
		</div>
</body>
</html>