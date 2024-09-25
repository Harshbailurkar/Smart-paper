package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.Question;
import com.example.smart_paper.repository.AdminMaster.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Save new question
    public Question save(Question question, String username) {
        question.setUsername(username);
        return questionRepository.save(question);
    }

    // Update existing question
    public Question update(Long id, Question updatedQuestion,String username) {
        Optional<Question> existingQuestionOpt = questionRepository.findById(id);
        if (existingQuestionOpt.isPresent()) {
            Question existingQuestion = existingQuestionOpt.get();

            // Update only fields that are non-null
            if (updatedQuestion.getQuestionStatement() != null)
                existingQuestion.setQuestionStatement(updatedQuestion.getQuestionStatement());
            if (updatedQuestion.getAnswer() != null)
                existingQuestion.setAnswer(updatedQuestion.getAnswer());
            if (updatedQuestion.getQuestionFilePath() != null)
                existingQuestion.setQuestionFilePath(updatedQuestion.getQuestionFilePath());
            if (updatedQuestion.getQuestionFilename() != null)
                existingQuestion.setQuestionFilename(updatedQuestion.getQuestionFilename());
            if (updatedQuestion.getAnswerFileName() != null)
                existingQuestion.setAnswerFileName(updatedQuestion.getAnswerFileName());
            if (updatedQuestion.getAnswerFilePath() != null)
                existingQuestion.setAnswerFilePath(updatedQuestion.getAnswerFilePath());

            // Update IP and username
            existingQuestion.setUsername(username);

            return questionRepository.save(existingQuestion);
        }
        return null;
    }

    // Get question by id
    public Optional<Question> getById(Long id) {
        return questionRepository.findById(id);
    }

    // Delete question (soft delete)
    public void softDelete(Long id) {
        Optional<Question> questionOpt = questionRepository.findById(id);
        questionOpt.ifPresent(question -> {
            question.setDeleted(true);
            questionRepository.save(question);
        });
    }
}
