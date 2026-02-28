package com;

import DAO.MySQLStudentDAO;
import DAO.StudentDAO;
import DAO.StudentValidator;
import exception.InvalidStudentDataException;
import model.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
    	StudentDAO dao = new MySQLStudentDAO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Student Management System");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choiceInput = sc.nextLine();

            if (!choiceInput.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            int choice = Integer.parseInt(choiceInput);
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        dao.viewAllStudents();
                        break;
                    case 3:
                        updateEmail();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InvalidStudentDataException e) {
                System.out.println("[Validation Error] " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("[Database Error] " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--------- MENU ---------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Email by Mobile");
        System.out.println("4. Delete Student by ID");
        System.out.println("5. Exit");
        System.out.println("------------------------");
    }

    private static void addStudent() throws InvalidStudentDataException, SQLException {
    	Scanner sc = new Scanner(System.in);
    	StudentDAO dao = new MySQLStudentDAO();
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter Name   : ");
        String name = sc.nextLine();

        System.out.print("Enter Email  : ");
        String email = sc.nextLine();

        System.out.print("Enter Age    : ");
        String ageInput = sc.nextLine();
        if (!ageInput.matches("\\d+")) {
            throw new InvalidStudentDataException("Age must be a positive number.");
        }
        int age = Integer.parseInt(ageInput);

        System.out.print("Enter Mobile : ");
        String mobile = sc.nextLine();

        Student student = new Student(0, name, email, age, mobile);

        StudentValidator.validate(student);

        dao.addStudent(student);
    }

    private static void updateEmail() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	StudentDAO dao = new MySQLStudentDAO();
        System.out.println("\n--- Update Email ---");
        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();
        System.out.print("Enter New Email    : ");
        String newEmail = sc.nextLine();
        dao.updateEmailByMobile(mobile, newEmail);
    }

    private static void deleteStudent() throws SQLException {
    	StudentDAO dao = new MySQLStudentDAO();
    	Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID: ");
        String idInput = sc.nextLine();
        if (!idInput.matches("\\d+")) {
            System.out.println("Invalid ID. Please enter a numeric value.");
            return;
        }
        dao.deleteStudentById(Integer.parseInt(idInput));
    }
}