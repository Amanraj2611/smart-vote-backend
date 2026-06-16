package com.votingsystem.repository;

import com.votingsystem.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // approved candidates (used for voting)
    List<Candidate> findByElectionIdAndApprovedTrue(Long electionId);

    // pending applications
    List<Candidate> findByApprovedFalse();

    boolean existsByNameAndElectionId(String name, Long electionId);
}