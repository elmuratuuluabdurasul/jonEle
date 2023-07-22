package java10.dao.daoImpl;

import java10.config.MyConfiguration;
import java10.dao.StudentDao;
import java10.model.Student;

import java.sql.*;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final Connection connection = MyConfiguration.getConnection();
    @Override
    public void createStudent() {
        try (Statement statement = connection.createStatement()) {
            String sql = "create table if not exists students("+
                    "id serial primary key,"+
                    "first_name varchar(20),"+
                    "last_name varchar,"+
                    "email varchar unique)";
            statement.executeUpdate(sql);
            System.out.println("created table students...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveStudent(Student student) {
        String sql = "insert into students("+
                            "first_name, last_name, email)"+
                            "values (?,?,?)"
                ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.executeUpdate();
            System.out.println(student.getFirstName() + " is sucsesfully saved...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student getStudentById(Long id) {
        Student student = new Student();
        String sql = "select * from students where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student.setId(resultSet.getLong("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {

    }

    @Override
    public void deleteStudentById(Long id) {

    }

    @Override
    public void deleteAllStudents() {

    }
}
