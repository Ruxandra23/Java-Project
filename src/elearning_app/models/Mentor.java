package elearning_app.models;

import java.util.*;

public class Mentor extends User {
    private List<Course> createdCourses;
    private String specialization;
    private String bio;

    public Mentor(String name, String email, String password, String specialization, String bio) {
        super(name, email, password);
        this.createdCourses = new ArrayList<>();
        this.specialization = specialization;
        this.bio = bio;
    }

    public void addCourse(Course course) {
        createdCourses.add(course);
    }

    public List<Course> getCreatedCourses() {
        return createdCourses;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "name=" + getName() +
                ", email=" + getEmail() +
                ", specialization='" + specialization + '\'' +
                ", coursesCreated=" + createdCourses.size() +
                '}';
    }
}