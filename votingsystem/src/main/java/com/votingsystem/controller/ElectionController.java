package com.votingsystem.controller;

import com.votingsystem.entity.Election;
import com.votingsystem.entity.ElectionStatus;
import com.votingsystem.repository.ElectionRepository;
import com.votingsystem.repository.VoteRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/election")
@CrossOrigin("*")
public class ElectionController {

    private final VoteRepository voteRepository;
    private final ElectionRepository electionRepository;

    public ElectionController(
            VoteRepository voteRepository,
            ElectionRepository electionRepository) {

        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
    }

    // 🔥 FIXED GET ALL
    @GetMapping("/all")
    public List<Map<String, Object>> getAll() {
        return electionRepository.findAllWithVotes();
    }

    @PostMapping("/create")
    public Election createElection(@RequestBody Election election){
        election.setStatus(ElectionStatus.UPCOMING);
        return electionRepository.save(election);
    }

    @GetMapping("/{id}")
    public Election getElection(@PathVariable Long id){
        return electionRepository.findById(id).orElseThrow();
    }

    @PutMapping("/status/{id}")
    public Election updateStatus(@PathVariable Long id,
                                 @RequestBody Map<String,String> body){

        Election e = electionRepository.findById(id).orElseThrow();
        e.setStatus(ElectionStatus.valueOf(body.get("status")));
        return electionRepository.save(e);
    }

    @GetMapping("/result/{id}")
    public List<Map<String,Object>> results(@PathVariable Long id){
        return voteRepository.getElectionResults(id);
    }
}