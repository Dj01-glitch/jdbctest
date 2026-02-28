package DAO;

import db.DBConnection;
import model.Student;
import java.sql.*;

public class MySQLStudentDAO implements StudentDAO {

    @Override
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, email, age, mobile) VALUES (?, ?, ?, ?)";
        	Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getMobile());
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student added successfully!" : "Failed to add student.");
    }

    @Override
    public void viewAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
            System.out.println("\n========== All Students ==========");
            if(rs.isBeforeFirst()) {
            while (rs.next()) {
                
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("mobile")
                );
                System.out.println(s);
            }
            }else {
            	System.out.println("NO DATA FOUND!!!!!!!!!!!!!!!!!!!!!!!!");
            }
    }

    @Override
    public void updateEmailByMobile(String mobile, String newEmail) throws SQLException {
        	String sql = "UPDATE students SET email = ? WHERE mobile = ?";
        	Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setString(1, newEmail);
            ps.setString(2, mobile);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Email updated successfully!" : "No student found with that mobile number.");
        
    }

    @Override
    public void deleteStudentById(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted successfully!" : "No student found with that ID.");
        
    }
}