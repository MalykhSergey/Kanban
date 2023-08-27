package com.example.kanban.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spaces")
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }
}
