package com.votingsystem.repository;

import com.votingsystem.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ElectionRepository extends JpaRepository<Election, Long> {

    // 🔥 GET ALL ELECTIONS WITH TOTAL VOTES
    @Query(value = """
        SELECT 
            e.id AS id,
            e.title AS title,
            e.description AS description,
            e.status AS status,
            COUNT(v.id) AS totalVotes
        FROM election e
        LEFT JOIN votes v ON v.election_id = e.id
        GROUP BY e.id, e.title, e.description, e.status
        ORDER BY e.id DESC
    """, nativeQuery = true)
    List<Map<String, Object>> findAllWithVotes();
}