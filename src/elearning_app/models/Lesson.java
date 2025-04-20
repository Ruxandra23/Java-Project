package elearning_app.models;

import java.util.UUID;

public class Lesson {
    private final String id;
    private String title;
    private String content;
    private int duration; // durata in min
    private int orderInCourse;

    public Lesson(String title, String content, int duration, int orderInCourse) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.duration = duration;
        this.orderInCourse = orderInCourse;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getOrderInCourse() {
        return orderInCourse;
    }

    public void setOrderInCourse(int orderInCourse) {
        this.orderInCourse = orderInCourse;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", order=" + orderInCourse +
                '}';
    }
}