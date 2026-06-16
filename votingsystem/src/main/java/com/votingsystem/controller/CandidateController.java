package com.votingsystem.controller;

import com.votingsystem.entity.Candidate;
import com.votingsystem.entity.Election;
import com.votingsystem.repository.CandidateRepository;
import com.votingsystem.repository.ElectionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin("*")
public class CandidateController {

    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    public CandidateController(CandidateRepository candidateRepository,
                               ElectionRepository electionRepository) {
        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    // ✅ GET candidates by election
    @GetMapping("/election/{id}")
    public List<Candidate> getCandidates(@PathVariable Long id) {
        return candidateRepository.findByElectionIdAndApprovedTrue(id);
    }

    // ✅ ADD candidate
    @PostMapping("/add")
    public Candidate addCandidate(@RequestBody Candidate candidate) {

        Election election = electionRepository
                .findById(candidate.getElection().getId())
                .orElseThrow();

        candidate.setElection(election);
        candidate.setApproved(true);

        return candidateRepository.save(candidate);
    }

    // ✅ DELETE candidate
    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateRepository.deleteById(id);
    }
}