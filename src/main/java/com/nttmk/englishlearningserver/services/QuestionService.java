package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.QuestionDTO;
import com.nttmk.englishlearningserver.exceptions.DataNotFoundException;
import com.nttmk.englishlearningserver.models.Answer;
import com.nttmk.englishlearningserver.models.Question;
import com.nttmk.englishlearningserver.repository.QuestionRepository;
import com.nttmk.englishlearningserver.responses.AnswerResponse;
import com.nttmk.englishlearningserver.responses.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionResponse createQuestion(QuestionDTO dto) {
        Question question = Question.builder()
                .title(dto.getTitle())
                .type(dto.getType())
                .content(dto.getContent())
                .audioUrl(dto.getAudioUrl())
                .answers(dto.getAnswers().stream()
                        .map(a -> Answer.builder()
                                .label(a.getLabel())
                                .content(a.getContent())
                                .correct(a.isCorrect())
                                .build())
                        .toList())
                .build();

        questionRepository.save(question);
        return mapToResponse(question);
    }

    public List<QuestionResponse> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public QuestionResponse getQuestionById(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Question not found"));
        return mapToResponse(question);
    }

    @Transactional
    public QuestionResponse updateQuestion(String id, QuestionDTO dto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Question not found"));

        question.setTitle(dto.getTitle());
        question.setType(dto.getType());
        question.setContent(dto.getContent());
        question.setAudioUrl(dto.getAudioUrl());
        question.setAnswers(dto.getAnswers().stream()
                .map(a -> Answer.builder()
                        .label(a.getLabel())
                        .content(a.getContent())
                        .correct(a.isCorrect())
                        .build())
                .toList());

        questionRepository.save(question);
        return mapToResponse(question);
    }

    @Transactional
    public void deleteQuestion(String id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found");
        }
        questionRepository.deleteById(id);
    }

    private QuestionResponse mapToResponse(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .audioUrl(question.getAudioUrl())
                .type(question.getType())
                .answers(
                        question.getAnswers().stream()
                                .map(a -> AnswerResponse.builder()
                                        .label(a.getLabel())
                                        .content(a.getContent())
                                        .correct(a.isCorrect())
                                        .build())
                                .toList()
                )
                .build();
    }
}
