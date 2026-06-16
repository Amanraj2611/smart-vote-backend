package com.votingsystem.dto;

public class AdminStatsResponse {

    private long totalElections;
    private long openElections;
    private long totalCandidates;
    private long totalVoters;

    public AdminStatsResponse(long totalElections,
                              long openElections,
                              long totalCandidates,
                              long totalVoters) {
        this.totalElections = totalElections;
        this.openElections = openElections;
        this.totalCandidates = totalCandidates;
        this.totalVoters = totalVoters;
    }

    public long getTotalElections() {
        return totalElections;
    }

    public long getOpenElections() {
        return openElections;
    }

    public long getTotalCandidates() {
        return totalCandidates;
    }

    public long getTotalVoters() {
        return totalVoters;
    }
}