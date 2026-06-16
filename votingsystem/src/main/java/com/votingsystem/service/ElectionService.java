package com.votingsystem.service;

import java.util.List;
import java.util.Map;

public interface ElectionService {

    List<Map<String, Object>> getElectionResult(Long electionId);

}