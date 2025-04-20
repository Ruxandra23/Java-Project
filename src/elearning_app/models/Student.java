package elearning_app.models;

import java.util.*;

public class Student extends User {
    private List<Course> enrolledCourses;
    private Map<String, Double> courseProgress; // courseId -> procent completare
    private List<String> reviews;

    public Student(String name, String email, String password) {
        super(name, email, password);
        this.enrolledCourses = new ArrayList<>();
        this.courseProgress = new HashMap<>();
        this.reviews = new ArrayList<>();
    }


    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            courseProgress.put(course.getId(), 0.0);
        }
    }


    public void updateProgress(String courseId, double percent) {
        if (courseProgress.containsKey(courseId)) {
            courseProgress.put(courseId, Math.min(100.0, percent));
        }
    }


    public double getProgress(String courseId) {
        return courseProgress.getOrDefault(courseId, 0.0);
    }


    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }


    public void addReview(String review) {
        reviews.add(review);
    }


    public List<String> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + getName() +
                ", email=" + getEmail() +
                ", enrolledCourses=" + enrolledCourses.size() +
                '}';
    }
}