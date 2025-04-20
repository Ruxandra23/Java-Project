package elearning_app.models;

import java.util.*;

public class Quiz {
    private final String id;
    private String title;
    private Map<String, String> questions; // intrebare -> raspuns corect
    private int passingScore;

    public Quiz(String title, int passingScore) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.questions = new HashMap<>();
        this.passingScore = passingScore;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addQuestion(String question, String correctAnswer) {
        questions.put(question, correctAnswer);
    }

    public Map<String, String> getQuestions() {
        return questions;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }


    public int evaluateAnswers(Map<String, String> studentAnswers) {
        int correctAnswers = 0;

        for (Map.Entry<String, String> entry : studentAnswers.entrySet()) {
            String question = entry.getKey();
            String studentAnswer = entry.getValue();

            if (questions.containsKey(question) &&
                    questions.get(question).equalsIgnoreCase(studentAnswer)) {
                correctAnswers++;
            }
        }

        return (int) ((double) correctAnswers / questions.size() * 100);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", questionCount=" + questions.size() +
                ", passingScore=" + passingScore +
                '}';
    }
}