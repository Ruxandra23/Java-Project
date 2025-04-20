package elearning_app.services;

import elearning_app.models.*;
import java.util.*;

public class CourseService {
    private Map<String, Course> courses;
    private Map<String, Category> categories;
    private UserService userService;

    public CourseService(UserService userService) {
        this.courses = new HashMap<>();
        this.categories = new HashMap<>();
        this.userService = userService;
    }

    public Category createCategory(String name, String description) {
        Category category = new Category(name, description);
        categories.put(category.getId(), category);
        return category;
    }

    public Course createCourse(String title, String description, String mentorId,
                               String categoryId, double price) {
        Mentor mentor = userService.getMentorById(mentorId);
        Category category = categories.get(categoryId);

        if (mentor == null || category == null) {
            throw new IllegalArgumentException("Invalid mentor or category");
        }

        Course course = new Course(title, description, mentor, category, price);
        courses.put(course.getId(), course);
        return course;
    }

    public Lesson addLessonToCourse(String courseId, String title, String content,
                                    int duration, int order) {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        Lesson lesson = new Lesson(title, content, duration, order);
        course.addLesson(lesson);
        return lesson;
    }

    public Quiz addQuizToCourse(String courseId, String title, int passingScore) {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        Quiz quiz = new Quiz(title, passingScore);
        course.addQuiz(quiz);
        return quiz;
    }

    public void addQuestionToQuiz(String courseId, String quizId, String question, String answer) {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        Quiz quiz = course.getQuizzes().stream()
                .filter(q -> q.getId().equals(quizId))
                .findFirst()
                .orElse(null);

        if (quiz == null) {
            throw new IllegalArgumentException("Quiz not found");
        }

        quiz.addQuestion(question, answer);
    }

    public LearningChallenge addChallengeToCourse(String courseId, String title,
                                                  String description, int difficulty,
                                                  String task, String solution) {
        Course course = getCourseById(courseId);
        if (course == null) {
            return null;
        }

        LearningChallenge challenge = new LearningChallenge(title, description, difficulty, task, solution);
        course.addChallenge(challenge);
        return challenge;
    }

    public void enrollStudentInCourse(String studentId, String courseId) {
        Student student = userService.getStudentById(studentId);
        Course course = courses.get(courseId);

        if (student == null || course == null) {
            throw new IllegalArgumentException("Student or course not found");
        }

        student.enrollInCourse(course);
        course.incrementEnrollmentCount();
    }

    public void updateStudentProgress(String studentId, String courseId, double progress) {
        Student student = userService.getStudentById(studentId);

        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        student.updateProgress(courseId, progress);
    }

    public void addCourseRating(String courseId, double rating, String review) {
        Course course = getCourseById(courseId);
        if (course == null) {
            return;
        }

        course.addRating(rating);
        if (review != null && !review.isEmpty()) {
            course.addReview(review);
        }
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public List<Course> getCoursesByCategory(String categoryId) {
        Category category = categories.get(categoryId);
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        return category.getCourses();
    }

    public List<Course> searchCoursesByKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return courses.values().stream()
                .filter(c -> c.getTitle().toLowerCase().contains(lowerKeyword) ||
                        c.getDescription().toLowerCase().contains(lowerKeyword))
                .sorted() // utilizează compareTo pentru a sorta rezultatele
                .toList();
    }

    public Course getCourseById(String courseId) {
        return courses.get(courseId);
    }

    public Category getCategoryById(String categoryId) {
        return categories.get(categoryId);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    // Metodă pentru obținerea cursurilor sortate după rating, înscrieri, etc.
    public List<Course> getTopRatedCourses() {
        return courses.values().stream()
                .sorted()
                .limit(10)
                .toList();
    }
}
