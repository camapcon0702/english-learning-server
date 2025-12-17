package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.AnsweredQuestionDTO;
import com.nttmk.englishlearningserver.dto.ExamDTO;
import com.nttmk.englishlearningserver.dto.ExamRandomDTO;
import com.nttmk.englishlearningserver.dto.ExamSubmitDTO;
import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import com.nttmk.englishlearningserver.exceptions.DataNotFoundException;
import com.nttmk.englishlearningserver.models.*;
import com.nttmk.englishlearningserver.repository.ExamRepository;
import com.nttmk.englishlearningserver.repository.ExamSubmissionRepository;
import com.nttmk.englishlearningserver.repository.QuestionRepository;
import com.nttmk.englishlearningserver.responses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final ExamSubmissionRepository examSubmissionRepository;
    private final UserService userService;

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

    public ExamViewResponse startExam(String examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new DataNotFoundException("Exam not found"));

        List<Question> questions = questionRepository.findAllById(exam.getQuestionIds());
        Collections.shuffle(questions);

        List<QuestionViewResponse> questionViewResponses = questions.stream()
                .map(q -> QuestionViewResponse.builder()
                        .id(q.getId())
                        .title(q.getTitle())
                        .content(q.getContent())
                        .audioUrl(q.getAudioUrl())
                        .type(q.getType())
                        .answers(q.getAnswers().stream()
                                .map(a -> AnswerViewResponse.builder()
                                        .label(a.getLabel())
                                        .content(a.getContent())
                                        .build())
                                .toList())
                        .build())
                .toList();

        return ExamViewResponse.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .description(exam.getDescription())
                .type(exam.getType())
                .duration(exam.getDuration())
                .questions(questionViewResponses)
                .build();
    }

    @Transactional
    public ExamResultResponse submitExam(ExamSubmitDTO dto) {
        UserResponse user = userService.getCurrentUserLoggedIn();

        Exam exam = examRepository.findById(dto.getExamId()).orElseThrow(()-> new DataNotFoundException("Exam not found"));

        List<Question> questions = questionRepository.findAllById(exam.getQuestionIds());

        int correctCount = 0;
        List<AnsweredQuestion> answeredQuestions = new ArrayList<>();
        List<AnsweredQuestionReviewResponse> review = new ArrayList<>();

        for (AnsweredQuestionDTO a : dto.getAnswers()) {
            Question q = questions.stream()
                    .filter(qq -> qq.getId().equals(a.getQuestionId()))
                    .findFirst()
                    .orElseThrow(() -> new DataNotFoundException("Question not found"));

            String correctLabel = q.getAnswers().stream()
                    .filter(Answer::isCorrect)
                    .map(Answer::getLabel)
                    .findFirst()
                    .orElse(null);

            boolean isCorrect = q.getAnswers().stream()
                    .anyMatch(ans ->
                            ans.isCorrect()
                                    && ans.getLabel().equals(a.getSelectedLabel())
                    );

            if (isCorrect) {
                correctCount++;
            }

            answeredQuestions.add(
                    AnsweredQuestion.builder()
                            .questionId(a.getQuestionId())
                            .selectedLabel(a.getSelectedLabel())
                            .correct(isCorrect)
                            .build()
            );

            review.add(
                    AnsweredQuestionReviewResponse.builder()
                            .questionId(q.getId())
                            .title(q.getTitle())
                            .content(q.getContent())
                            .audioUrl(q.getAudioUrl())
                            .selectedLabel(a.getSelectedLabel())
                            .correctLabel(correctLabel)
                            .correct(isCorrect)
                            .answers(q.getAnswers().stream()
                                    .map(ans -> AnswerViewResponse.builder()
                                            .label(ans.getLabel())
                                            .content(ans.getContent())
                                            .build())
                                    .toList())
                            .build()
            );
        }
        double score = ((double) correctCount / exam.getQuestionIds().size()) * 10;

        ExamSubmission submission = ExamSubmission.builder()
                .userId(user.getId())
                .examId(exam.getId())
                .answers(answeredQuestions)
                .totalQuestions(exam.getQuestionIds().size())
                .correctAnswers(correctCount)
                .score(score)
                .startedAt(LocalDateTime.now())
                .submittedAt(LocalDateTime.now())
                .build();

        examSubmissionRepository.save(submission);

        return ExamResultResponse.builder()
                .examId(exam.getId())
                .totalQuestions(exam.getQuestionIds().size())
                .correctAnswers(correctCount)
                .score(score)
                .review(review)
                .build();
    }

    public List<ExamResultResponse> getAllExamResult() {
        UserResponse user = userService.getCurrentUserLoggedIn();

        List<ExamSubmission> submissions =
                examSubmissionRepository.findAllByUserId(user.getId());

        return submissions.stream()
                .map(sub -> ExamResultResponse.builder()
                        .examId(sub.getExamId())
                        .totalQuestions(sub.getTotalQuestions())
                        .correctAnswers(sub.getCorrectAnswers())
                        .score(sub.getScore())
                        .build())
                .toList();
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
