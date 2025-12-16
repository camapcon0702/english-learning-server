package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.VocabularyTopicDTO;
import com.nttmk.englishlearningserver.models.VocabularyTopic;
import com.nttmk.englishlearningserver.repository.VocabularyTopicRepository;
import com.nttmk.englishlearningserver.servicesInterface.IVocabularyTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyTopicService implements IVocabularyTopicService {
    private final VocabularyTopicRepository vocabularyTopicRepository;

    @Override
    public VocabularyTopic createVocabularyTopic(VocabularyTopicDTO vocabularyTopicDTO) {
        VocabularyTopic vocabularyTopic = VocabularyTopic.builder()
                .name(vocabularyTopicDTO.getName())
                .nameEn(vocabularyTopicDTO.getNameEn())
                .color(vocabularyTopicDTO.getColor())
                .icon(vocabularyTopicDTO.getIcon())
                .description(vocabularyTopicDTO.getDescription())
                .level(vocabularyTopicDTO.getLevel())
                .gradientFrom(vocabularyTopicDTO.getGradientFrom())
                .gradientTo(vocabularyTopicDTO.getGradientTo())
                .build();

        vocabularyTopicRepository.save(vocabularyTopic);

        return vocabularyTopic;
    }

    @Override
    public List<VocabularyTopic> listVocabularyTopics() {
        return vocabularyTopicRepository.findAll();
    }

    @Override
    public VocabularyTopic getVocabularyTopicById(String id) {
        return vocabularyTopicRepository.findById(id).orElse(null);
    }

    public void deleteVocabularyTopicById(String id) {
        vocabularyTopicRepository.deleteById(id);
    }

    public VocabularyTopic updateVocabularyTopic(String id, VocabularyTopicDTO vocabularyTopic) {
        VocabularyTopic existingVocabularyTopic = vocabularyTopicRepository.findById(id).orElseThrow(() -> new RuntimeException("vocabulary topic not found"));

        existingVocabularyTopic.setName(vocabularyTopic.getName());
        existingVocabularyTopic.setNameEn(vocabularyTopic.getNameEn());
        existingVocabularyTopic.setColor(vocabularyTopic.getColor());
        existingVocabularyTopic.setIcon(vocabularyTopic.getIcon());
        existingVocabularyTopic.setDescription(vocabularyTopic.getDescription());
        existingVocabularyTopic.setLevel(vocabularyTopic.getLevel());
        existingVocabularyTopic.setGradientFrom(vocabularyTopic.getGradientFrom());
        existingVocabularyTopic.setGradientTo(vocabularyTopic.getGradientTo());

        vocabularyTopicRepository.save(existingVocabularyTopic);

        return existingVocabularyTopic;
    }
}
