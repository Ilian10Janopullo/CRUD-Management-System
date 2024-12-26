package Controller;

import Model.Student;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.util.List;


@WebServlet(name = "StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

    private StudentUtil students;

    @Resource(name = "jdbc/students")
    private DataSource source;

    @Override
    public void init() throws ServletException {
        try{
            students = new StudentUtil(source);
        }catch (Exception exception){
            throw new ServletException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String theCommand = request.getParameter("command");

        if(theCommand == null){
            theCommand = "LIST";
        }

        switch (theCommand){
            case "LIST" :
                listStudents(request, response);
                break;
            case "ADD":
                addStudent(request, response);
                break;
            case "LOAD":
                loadStudent(request, response);
                break;
            case "UPDATE":
                updateStudent(request, response);
                break;
            case "DELETE":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }


    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        students.delete(request.getParameter("studentId"));
        listStudents(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student updatedStudent = new Student(Integer.parseInt(request.getParameter("studentId")), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"));
        students.update(updatedStudent);
        listStudents(request, response);

    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String studentID = request.getParameter("studentId");
        Student student = students.getStudentWithId(studentID);
        request.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-student-form.jsp");
        dispatcher.forward(request, response);

    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"));
        students.addStudent(student);
        listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentsList = students.getStudents();
        request.setAttribute("studentsList", studentsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list_students.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}