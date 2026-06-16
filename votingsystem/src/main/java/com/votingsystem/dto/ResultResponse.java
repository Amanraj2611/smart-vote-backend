package com.votingsystem.dto;

public class ResultResponse {

    private String candidate;
    private Long votes;

    // constructor
    public ResultResponse(String candidate, Long votes) {
        this.candidate = candidate;
        this.votes = votes;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }
}