package DAO;

import model.Student;
import java.sql.SQLException;

public interface StudentDAO {
    void addStudent(Student student) throws SQLException;
    void viewAllStudents() throws SQLException;
    void updateEmailByMobile(String mobile, String newEmail) throws SQLException;
    void deleteStudentById(int id) throws SQLException;
}