package elearning_app.models;

import java.util.*;

public class Course implements Comparable<Course> {
    private final String id;
    private String title;
    private String description;
    private Mentor author;
    private Category category;
    private List<Lesson> lessons;
    private List<Quiz> quizzes;
    private double price;
    private double rating;
    private int enrollmentCount;

    public Course(String title, String description, Mentor author, Category category, double price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.author = author;
        this.category = category;
        this.price = price;
        this.lessons = new ArrayList<>();
        this.quizzes = new ArrayList<>();
        this.rating = 0.0;
        this.enrollmentCount = 0;

        // adauga cursul la lista de cursuri create de autor
        author.addCourse(this);
        // adauga cursul la lista de cursuri din categorie
        category.addCourse(this);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Mentor getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public void addRating(double userRating) {

        if (enrollmentCount == 0) {
            rating = userRating;
        } else {
            rating = (rating * enrollmentCount + userRating) / (enrollmentCount + 1);
        }
    }

    public void incrementEnrollmentCount() {
        enrollmentCount++;
    }

    private List<LearningChallenge> challenges = new ArrayList<>();
    private List<String> reviews = new ArrayList<>();


    public void addChallenge(LearningChallenge challenge) {
        challenges.add(challenge);
    }


    public List<LearningChallenge> getChallenges() {
        return challenges;
    }

    public void addReview(String review) {
        reviews.add(review);
    }


    public List<String> getReviews() {
        return reviews;
    }

    @Override
    public int compareTo(Course other) {
        // comparare după rating-descresc
        return Double.compare(other.rating, this.rating);
    }


    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author.getName() +
                ", category=" + category.getName() +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}