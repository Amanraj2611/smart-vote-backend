package com.votingsystem.repository;

import com.votingsystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByUserIdAndElectionId(Long userId, Long electionId);

    @Query(value = """
    SELECT 
        c.id AS candidateId,
        c.name AS candidateName,
        COUNT(v.id) AS voteCount
    FROM candidate c
    LEFT JOIN votes v   -- 🔥 FIX HERE (votes not vote)
        ON v.candidate_id = c.id
    WHERE c.election_id = :electionId
    GROUP BY c.id, c.name
    ORDER BY voteCount DESC
    """, nativeQuery = true)
    List<Map<String, Object>> getElectionResults(Long electionId);
}