package Controller;

import Model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentUtil {
    private DataSource source;
    public StudentUtil(DataSource source){
        this.source = source;
    }

    public void addStudent(Student student){
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try{
            myConnection = source.getConnection();

            String sql = "insert into student (first_name, last_name, email) values(?,?,?)";
            myStatement = myConnection.prepareStatement(sql);

            myStatement.setString(1, student.getFirstName());
            myStatement.setString(2, student.getLastName());
            myStatement.setString(3, student.geteMail());

            myStatement.execute();

        }catch (Exception e){
            e.fillInStackTrace();
        } finally {
            close(myConnection, myStatement, null);
        }

    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myResult = null;

        try{
            myConn = source.getConnection();
            String sqlQuery = "SELECT * FROM student";
            myStmt = myConn.createStatement();
            myResult = myStmt.executeQuery(sqlQuery);

            while(myResult.next()){
                students.add(new Student(myResult.getInt("id"), myResult.getString("first_name"),
                        myResult.getString("last_name"), myResult.getString("email")));
            }

        }catch (Exception exception){
            exception.fillInStackTrace();
        } finally {
            close(myConn, myStmt, myResult);
        }

        return students;
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myResult){
        try{
            if(myConn != null){
                myConn.close();
            }
            if(myStmt != null){
                myStmt.close();
            }
            if(myResult != null){
                myResult.close();
            }
        }catch (Exception exception){
            exception.fillInStackTrace();
        }
    }

    public Student getStudentWithId(String studentID) {

        Student student = null;

        Connection myConnection = null;
        PreparedStatement myStatement = null;
        ResultSet myResult = null;

        try{

            myConnection = source.getConnection();
            String sql = "SELECT * FROM student where id = ?";

            myStatement = myConnection.prepareStatement(sql);
            myStatement.setInt(1, Integer.parseInt(studentID));

            myResult = myStatement.executeQuery();

            if(myResult.next()){
                student = new Student(myResult.getInt("id"), myResult.getString("first_name"),
                        myResult.getString("last_name"), myResult.getString("email"));
            } else {
                throw new Exception("Could not find this student in the database!");
            }

        }catch (Exception exception){
            exception.fillInStackTrace();
        } finally {
            close(myConnection, myStatement, myResult);
        }
        return student;
    }

    public void update(Student updatedStudent) {
        Connection myConn = null;
        PreparedStatement myStatement = null;

        try{
            myConn = source.getConnection();
            String sql = "update student set first_name = ? , last_name = ?, email = ? where id = ?";
            myStatement = myConn.prepareStatement(sql);
            myStatement.setString(1, updatedStudent.getFirstName());
            myStatement.setString(2, updatedStudent.getLastName());
            myStatement.setString(3, updatedStudent.geteMail());
            myStatement.setInt(4, updatedStudent.getId());
            myStatement.execute();

        }catch (Exception exception){
            exception.fillInStackTrace();
        }finally {
            close(myConn, myStatement, null);
        }
    }

    public void delete(String studentId) {
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try{
            myConnection = source.getConnection();
            String sql = "delete from student where id = ?";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setInt(1, Integer.parseInt(studentId));
            myStatement.execute();
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            close(myConnection, myStatement, null);
        }
    }
}
