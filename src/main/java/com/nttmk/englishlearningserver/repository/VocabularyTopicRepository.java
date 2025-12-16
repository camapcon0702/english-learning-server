package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.models.VocabularyTopic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VocabularyTopicRepository extends MongoRepository<VocabularyTopic, String> {

}
