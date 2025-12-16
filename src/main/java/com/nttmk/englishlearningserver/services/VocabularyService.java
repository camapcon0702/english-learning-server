package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.VocabularyDTO;
import com.nttmk.englishlearningserver.models.Vocabulary;
import com.nttmk.englishlearningserver.models.VocabularyTopic;
import com.nttmk.englishlearningserver.repository.VocabularyRepository;
import com.nttmk.englishlearningserver.repository.VocabularyTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;
    private final VocabularyTopicRepository vocabularyTopicRepository;

    public Vocabulary createVocabulary(String topicId, VocabularyDTO dto) {

        VocabularyTopic topic = vocabularyTopicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Vocabulary topic not found"));

        Vocabulary vocabulary = Vocabulary.builder()
                .id(UUID.randomUUID().toString())
                .word(dto.getWord())
                .pronunciation(dto.getPronunciation())
                .meaning(dto.getMeaning())
                .example(dto.getExample())
                .exampleTranslation(dto.getExampleTranslation())
                .category(dto.getCategory())
                .level(dto.getLevel())
                .topic(topicId)
                .createdAt(Instant.now())
                .build();

        topic.getVocabularies().add(vocabulary);
        topic.setVocabularyCount(topic.getVocabularies().size());

        vocabularyTopicRepository.save(topic);

        return vocabulary;
    }

    public Vocabulary updateVocabulary(String topicId, String vocabularyId, VocabularyDTO dto) {
        VocabularyTopic topic = vocabularyTopicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Vocabulary topic not found"));

        Vocabulary vocabulary = topic.getVocabularies().stream()
                .filter(v -> v.getId().equals(vocabularyId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vocabulary not found"));

        if (dto.getWord() != null) vocabulary.setWord(dto.getWord());
        if (dto.getPronunciation() != null) vocabulary.setPronunciation(dto.getPronunciation());
        if (dto.getMeaning() != null) vocabulary.setMeaning(dto.getMeaning());
        if (dto.getExample() != null) vocabulary.setExample(dto.getExample());
        if (dto.getExampleTranslation() != null)
            vocabulary.setExampleTranslation(dto.getExampleTranslation());
        if (dto.getCategory() != null) vocabulary.setCategory(dto.getCategory());
        if (dto.getLevel() != null) vocabulary.setLevel(dto.getLevel());

        vocabularyTopicRepository.save(topic);

        return vocabulary;
    }

    public void deleteVocabulary(String topicId, String vocabularyId) {
        VocabularyTopic topic = vocabularyTopicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Vocabulary topic not found"));

        boolean removed = topic.getVocabularies()
                .removeIf(v -> v.getId().equals(vocabularyId));

        if (!removed) {
            throw new RuntimeException("Vocabulary not found");
        }

        topic.setVocabularyCount(topic.getVocabularies().size());
        vocabularyTopicRepository.save(topic);
    }
}
