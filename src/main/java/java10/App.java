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
        System.out.println(studentService.getStudentById(3L));
    }
}









