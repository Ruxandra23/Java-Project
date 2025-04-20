package elearning_app.models;

import java.util.UUID;

public class LearningChallenge {
    private final String id;
    private String title;
    private String description;
    private int difficulty; // 1-10
    private String task;
    private String solution;

    public LearningChallenge(String title, String description, int difficulty, String task, String solution) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.difficulty = Math.min(10, Math.max(1, difficulty));
        this.task = task;
        this.solution = solution;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = Math.min(10, Math.max(1, difficulty));
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "LearningChallenge{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}