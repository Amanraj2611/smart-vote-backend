package com.votingsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------- USER --------
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // -------- ELECTION --------
    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    // -------- CANDIDATE --------
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
