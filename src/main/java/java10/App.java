package java10;

import java10.model.Student;
import java10.service.StudentService;
import java10.service.serviceImpl.StudentServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        StudentService studentService = new StudentServiceImpl();
        //studentService.createStudent();
        //studentService.saveStudent(new Student());
        //System.out.println(studentService.getStudentById(3L));
        //System.out.println(studentService.getAllStudents());
        //studentService.updateStudent(1L, new Student("Aijamal","Basytova","ajash@gmail.com"));
        //studentService.deleteStudentById(3L);
        studentService.deleteAllStudents();
    }
}









