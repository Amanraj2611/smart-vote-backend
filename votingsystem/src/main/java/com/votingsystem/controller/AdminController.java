package com.votingsystem.controller;

import com.votingsystem.entity.*;
import com.votingsystem.repository.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public AdminController(ElectionRepository electionRepository,
                           CandidateRepository candidateRepository,
                           UserRepository userRepository) {
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }

    // ================= ELECTION =================

    // CREATE
    // ✅ GET ALL ELECTIONS FOR ADMIN PANEL

    @PostMapping("/election")
    public Election createElection(@RequestBody Election election) {
        election.setStatus(ElectionStatus.UPCOMING);
        return electionRepository.save(election);
    }

    // GET ALL
    @GetMapping("/elections")
    public List<Election> getAll() {
        return electionRepository.findAll();
    }

    // DELETE
    @DeleteMapping("/election/{id}")
    public String deleteElection(@PathVariable Long id) {
        electionRepository.deleteById(id);
        return "Election Deleted";
    }

    // OPEN
    @PutMapping("/open/{id}")
    public String openElection(@PathVariable Long id) {

        Election e = electionRepository.findById(id).orElseThrow();
        e.setStatus(ElectionStatus.OPEN);
        electionRepository.save(e);

        return "Election OPENED";
    }

    // CLOSE
    @PutMapping("/close/{id}")
    public String closeElection(@PathVariable Long id) {

        Election e = electionRepository.findById(id).orElseThrow();
        e.setStatus(ElectionStatus.CLOSED);
        electionRepository.save(e);

        return "Election CLOSED";
    }

    // ================= CANDIDATES =================

    @GetMapping("/pending-candidates")
    public List<Candidate> pendingCandidates() {
        return candidateRepository.findByApprovedFalse();
    }

    @PutMapping("/approve-candidate/{id}")
    public String approve(@PathVariable Long id) {

        Candidate c = candidateRepository.findById(id).orElseThrow();
        c.setApproved(true);
        candidateRepository.save(c);

        return "Approved";
    }

    // ================= VOTERS =================

    @GetMapping("/voters")
    public List<User> voters() {
        return userRepository.findAll();
    }
}