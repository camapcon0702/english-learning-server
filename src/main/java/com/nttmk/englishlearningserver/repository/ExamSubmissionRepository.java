package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.models.ExamSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamSubmissionRepository extends MongoRepository<ExamSubmission, String> {
    List<ExamSubmission> findAllByUserId(String id);
}
