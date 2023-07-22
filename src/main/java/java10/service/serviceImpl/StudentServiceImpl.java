package java10.service.serviceImpl;

import java10.dao.StudentDao;
import java10.dao.daoImpl.StudentDaoImpl;
import java10.model.Student;
import java10.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void createStudent() {
        studentDao.createStudent();
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        studentDao.updateStudent(id,newStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();
    }
}



