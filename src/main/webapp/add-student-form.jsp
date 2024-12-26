<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26/12/2024
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
<%--    <link rel="stylesheet" type="text/css" href="css/style.css">--%>
    <link rel="stylesheet" type="text/css" href="css/add-student-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Student's Information System</h2>
    </div>
</div>

<div id="container">
    <h3>Add New Student</h3>
    <form action="StudentControllerServlet" method="get">

        <input type="hidden" name="command" value="ADD">

        <table>
            <tbody>
            <tr>
                <td><label>First Name:</label></td>
                <td><input type="text" name="firstName"></td>
            </tr>

            <tr>
                <td><label>Last Name:</label></td>
                <td><input type="text" name="lastName"></td>
            </tr>

            <tr>
                <td><label>e-Mail:</label></td>
                <td><input type="email" name="email"></td>
            </tr>

            <tr>
                <td><input type="button" value="Back" onclick="window.location.href = 'StudentControllerServlet';
                    return false" class="add-student-button"/></td>
                <td><input type="submit" value="Save" class="save"></td>
            </tr>
            </tbody>
        </table>


    </form>
</div>

</body>
</html>
