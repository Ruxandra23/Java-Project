package elearning_app.models;

import java.util.*;

public class Category {
    private final String id;
    private String name;
    private List<Course> courses;

    public Category(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coursesCount=" + courses.size() +
                '}';
    }
}