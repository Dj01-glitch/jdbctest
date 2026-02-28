package DAO;

import exception.InvalidStudentDataException;
import model.Student;

public class StudentValidator {
    public static void validate(Student student) throws InvalidStudentDataException {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new InvalidStudentDataException("Name must not be empty.");
        }
        if (student.getName().matches(".*\\d.*")) {
            throw new InvalidStudentDataException("Name must not contain numeric characters.");
        }
        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            throw new InvalidStudentDataException("Email must contain '@'.");
        }
        if (student.getAge() <= 0) {
            throw new InvalidStudentDataException("Age must be a positive number.");
        }
        if (student.getMobile() == null || !student.getMobile().matches("\\d{10}")) {
            throw new InvalidStudentDataException("Mobile number must contain exactly 10 digits.");
        }
    }
}