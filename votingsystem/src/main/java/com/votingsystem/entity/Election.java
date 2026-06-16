package com.votingsystem.entity;

import jakarta.persistence.*;

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private ElectionStatus status;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ElectionStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(ElectionStatus status) {
        this.status = status;
    }
}