package java10.service;

import java10.model.Student;

import java.util.List;

public interface StudentService {
    //createStudent
    void createStudent();

    //save Student
    void saveStudent(Student student);

    //getById
    Student getStudentById(Long id);

    //get All
    List<Student> getAllStudents();

    //update
    void updateStudent(Long id, Student newStudent);

    // delete by id
    void deleteStudentById(Long id);

    //delete all
    void deleteAllStudents();
}
