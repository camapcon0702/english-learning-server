package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.ExamDTO;
import com.nttmk.englishlearningserver.dto.ExamRandomDTO;
import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import com.nttmk.englishlearningserver.exceptions.DataNotFoundException;
import com.nttmk.englishlearningserver.models.Exam;
import com.nttmk.englishlearningserver.models.Question;
import com.nttmk.englishlearningserver.repository.ExamRepository;
import com.nttmk.englishlearningserver.repository.QuestionRepository;
import com.nttmk.englishlearningserver.responses.AnswerResponse;
import com.nttmk.englishlearningserver.responses.ExamResponse;
import com.nttmk.englishlearningserver.responses.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public ExamResponse createExam(ExamDTO dto) {
        List<String> validQuestionIds = questionRepository.findAllById(dto.getQuestionIds())
                .stream().map(Question::getId).toList();

        Exam exam = Exam.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .duration(dto.getDuration())
                .questionIds(validQuestionIds)
                .createdAt(LocalDateTime.now())
                .build();

        examRepository.save(exam);
        return mapToResponse(exam);
    }

    @Transactional
    public ExamResponse createExamRandom(ExamRandomDTO dto) {
        List<Question> questions = questionRepository.findByType(dto.getType());
        if (questions.isEmpty()) {
            throw new DataNotFoundException("Không có câu hỏi cho loại: " + dto.getType());
        }

        Collections.shuffle(questions);

        List<String> randomQuestionIds = questions.stream()
                .limit(dto.getCount())
                .map(Question::getId)
                .toList();

        Exam exam = Exam.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .duration(dto.getDuration())
                .questionIds(randomQuestionIds)
                .createdAt(LocalDateTime.now())
                .build();

        examRepository.save(exam);
        return mapToResponse(exam);
    }

    public List<ExamResponse> getAllExams() {
        return examRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ExamResponse getExamById(String id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Exam not found"));
        return mapToResponse(exam);
    }

    public List<ExamResponse> getExamByType(TestTypeEnums type) {
        List<Exam> exams = examRepository.findAllByType(type);

        return exams.stream().map(this::mapToResponse).toList();
    }

    @Transactional
    public ExamResponse updateExam(String id, ExamDTO dto) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Exam not found"));

        List<String> validQuestionIds = questionRepository.findAllById(dto.getQuestionIds())
                .stream().map(Question::getId).toList();

        exam.setTitle(dto.getTitle());
        exam.setDescription(dto.getDescription());
        exam.setType(dto.getType());
        exam.setDuration(dto.getDuration());
        exam.setQuestionIds(validQuestionIds);

        examRepository.save(exam);
        return mapToResponse(exam);
    }

    @Transactional
    public void deleteExam(String id) {
        if (!examRepository.existsById(id)) {
            throw new DataNotFoundException("Exam not found");
        }
        examRepository.deleteById(id);
    }

    public ExamResponse startExam(String examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new DataNotFoundException("Exam not found"));

        List<Question> questions = questionRepository.findAllById(exam.getQuestionIds());
        Collections.shuffle(questions);

        List<QuestionResponse> questionResponses = questions.stream()
                .map(q -> QuestionResponse.builder()
                        .id(q.getId())
                        .title(q.getTitle())
                        .content(q.getContent())
                        .audioUrl(q.getAudioUrl())
                        .type(q.getType())
                        .answers(q.getAnswers().stream()
                                .map(a -> AnswerResponse.builder()
                                        .label(a.getLabel())
                                        .content(a.getContent())
                                        .build())
                                .toList())
                        .build())
                .toList();

        return ExamResponse.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .description(exam.getDescription())
                .type(exam.getType())
                .duration(exam.getDuration())
                .questions(questionResponses)
                .build();
    }

    private ExamResponse mapToResponse(Exam exam) {
        List<Question> questions = questionRepository.findAllById(exam.getQuestionIds());
        List<QuestionResponse> questionResponses = questions.stream()
                .map(q -> QuestionResponse.builder()
                        .id(q.getId())
                        .title(q.getTitle())
                        .content(q.getContent())
                        .audioUrl(q.getAudioUrl())
                        .type(q.getType())
                        .answers(q.getAnswers().stream()
                                .map(a -> AnswerResponse.builder()
                                        .label(a.getLabel())
                                        .content(a.getContent())
                                        .build())
                                .toList())
                        .build())
                .toList();

        return ExamResponse.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .description(exam.getDescription())
                .type(exam.getType())
                .duration(exam.getDuration())
                .questions(questionResponses)
                .build();
    }
}
