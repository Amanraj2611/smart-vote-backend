package com.votingsystem.service;

import com.votingsystem.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ElectionServiceImpl implements ElectionService {

    private final VoteRepository voteRepository;

    public ElectionServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public List<Map<String, Object>> getElectionResult(Long electionId) {

        return voteRepository.getElectionResults(electionId);
    }
}