package elearning_app.services;

import elearning_app.models.*;
import java.util.*;

public class UserService {
    private Map<String, User> users;
    private Map<String, Student> students;
    private Map<String, Mentor> mentors;

    public UserService() {
        this.users = new HashMap<>();
        this.students = new HashMap<>();
        this.mentors = new HashMap<>();
    }

    public Student registerStudent(String name, String email, String password) {
        // daca emailul e deja folosit
        if (users.values().stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new IllegalArgumentException("Email already in use");
        }

        Student student = new Student(name, email, password);
        users.put(student.getId(), student);
        students.put(student.getId(), student);
        return student;
    }

    public Mentor registerMentor(String name, String email, String password,
                                 String specialization, String bio) {

        if (users.values().stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new IllegalArgumentException("Email already in use");
        }

        Mentor mentor = new Mentor(name, email, password, specialization, bio);
        users.put(mentor.getId(), mentor);
        mentors.put(mentor.getId(), mentor);
        return mentor;
    }

    public User authenticateUser(String email, String password) {

        Optional<User> user = users.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }

    public Student getStudentById(String id) {
        return students.get(id);
    }

    public Mentor getMentorById(String id) {
        return mentors.get(id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Mentor> getAllMentors() {
        return new ArrayList<>(mentors.values());
    }
}