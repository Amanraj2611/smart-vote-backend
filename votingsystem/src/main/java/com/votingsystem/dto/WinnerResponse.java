package com.votingsystem.dto;

public class WinnerResponse {

    private String winner;
    private Long totalVotes;

    // constructor
    public WinnerResponse(String winner, Long totalVotes) {
        this.winner = winner;
        this.totalVotes = totalVotes;
    }

    // getter
    public String getWinner() {
        return winner;
    }

    // setter
    public void setWinner(String winner) {
        this.winner = winner;
    }

    // getter
    public Long getTotalVotes() {
        return totalVotes;
    }

    // setter
    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }
}