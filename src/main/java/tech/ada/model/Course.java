package tech.ada.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course extends PanacheEntity {
    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private final List<Lesson> lessons = new ArrayList<>();

    protected Course() {}
    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(this.lessons);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void createLesson(Lesson lesson) {
        Lesson validatedLesson = Objects.requireNonNull(lesson,
                "lesson must not be null");
        this.lessons.add(validatedLesson);
    }
}
