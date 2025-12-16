package com.nttmk.englishlearningserver.servicesInterface;

import com.nttmk.englishlearningserver.dto.VocabularyTopicDTO;
import com.nttmk.englishlearningserver.models.VocabularyTopic;

import java.util.List;

public interface IVocabularyTopicService {
    VocabularyTopic createVocabularyTopic(VocabularyTopicDTO vocabularyTopicDTO);
    List<VocabularyTopic> listVocabularyTopics();
    VocabularyTopic getVocabularyTopicById(String id);
}
