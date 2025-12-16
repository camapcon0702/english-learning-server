package com.nttmk.englishlearningserver.repository;

import com.nttmk.englishlearningserver.dto.VocabularyTopicDTO;
import com.nttmk.englishlearningserver.models.Vocabulary;
import org.springframework.data.repository.CrudRepository;

public interface VocabularyRepository extends CrudRepository<Vocabulary, String> {
}
