<%@ page import="java.util.List" %>
<%@ page import="Model.Student" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26/12/2024
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Students Data</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>Student's Information System</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">
            <table>

                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>e-Mail</th>
                    <th>Action</th>
                </tr>


                <c:forEach var="student" items="${studentsList}">

                    <c:url var="link" value="StudentControllerServlet">
                        <c:param name="command" value="LOAD"/>
                        <c:param name="studentId" value="${student.id}"/>
                    </c:url>

                    <c:url var="linkToDelete" value="StudentControllerServlet">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="studentId" value="${student.id}"/>
                    </c:url>

                    <tr>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.eMail}</td>
                        <td><input type="button" value="Update" onclick="window.location.href = '${link}'; return false" class="update-student-button"/>
                            <input type="button" value="Delete" onclick="if(!confirm('Are you sure you want to delete this student?')) return false; else window.location.href = '${linkToDelete}';" class="delete-student-button"/>
                        </td>
                    </tr>
                </c:forEach>


            </table>

            <input type="button" value="Add Student" onclick="window.location.href = 'add-student-form.jsp';
                    return false" class="add-student-button"/>
        </div>
    </div>
</body>
</html>
