package com.votingsystem.dto;

public class ResultDTO {

    private String candidateName;
    private int votes;

    public ResultDTO(String candidateName, int votes) {
        this.candidateName = candidateName;
        this.votes = votes;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public int getVotes() {
        return votes;
    }
}
