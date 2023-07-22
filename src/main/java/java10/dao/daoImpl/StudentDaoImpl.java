package java10.dao.daoImpl;

import java10.config.MyConfiguration;
import java10.dao.StudentDao;
import java10.model.Student;

import java.sql.*;
import java.util.ArrayList;
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
        List<Student> studentList = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from students");
            while (resultSet.next()){
                studentList.add(new Student(resultSet.getLong("id"),
                                            resultSet.getString("first_name"),
                                            resultSet.getString("last_name"),
                                            resultSet.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        String sql = "update students set first_name = ?, last_name = ?, email = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1,newStudent.getFirstName());
                preparedStatement.setString(2,newStudent.getLastName());
                preparedStatement.setString(3,newStudent.getEmail());
                preparedStatement.setLong(4,id);
                preparedStatement.executeUpdate();
                System.out.println("student with id: " + id + "updated");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteStudentById(Long id) {
        String sql = "delete from students where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("student with id: " + id + " deleted");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteAllStudents() {
        String sql = "delete from students";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("All students deleted...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
