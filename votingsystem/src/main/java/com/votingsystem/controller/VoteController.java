package com.votingsystem.controller;

import com.votingsystem.entity.*;
import com.votingsystem.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin("*")
public class VoteController {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public VoteController(
            VoteRepository voteRepository,
            UserRepository userRepository,
            ElectionRepository electionRepository,
            CandidateRepository candidateRepository) {

        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
    }

    // ================= CAST VOTE (FINAL FIXED) =================
    @PostMapping
    public String castVote(
            @RequestBody Map<String, Long> body,
            Authentication authentication
    ) {

        System.out.println("🔥 Vote API HIT");

        Long electionId = body.get("electionId");
        Long candidateId = body.get("candidateId");

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Election not found"));

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        // ✅ Validate candidate belongs to election
        if (!candidate.getElection().getId().equals(electionId)) {
            return "Invalid candidate";
        }

        // ✅ Prevent double vote
        if (voteRepository.existsByUserIdAndElectionId(user.getId(), electionId)) {
            return "You already voted!";
        }

        Vote vote = new Vote();
        vote.setUser(user);
        vote.setElection(election);
        vote.setCandidate(candidate);

        voteRepository.save(vote);

        return "Vote Cast Successfully";
    }

    // ================= CHECK STATUS =================
    @GetMapping("/status/{electionId}")
    public boolean hasUserVoted(
            @PathVariable Long electionId,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return voteRepository
                .existsByUserIdAndElectionId(user.getId(), electionId);
    }
}