package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.QuestionType;
import com.example.smart_paper.repository.AdminMaster.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionTypeService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    // Save a new question type
    public QuestionType save(QuestionType questionType,  String username) {
        questionType.setUsername(username);
        return questionTypeRepository.save(questionType);
    }

    // Update an existing question type
    public QuestionType update(Long id, QuestionType updatedQuestionType, String username) {
        Optional<QuestionType> existingQuestionTypeOpt = questionTypeRepository.findById(id);
        if (existingQuestionTypeOpt.isPresent()) {
            QuestionType existingQuestionType = existingQuestionTypeOpt.get();

            if (updatedQuestionType.getType() != null) existingQuestionType.setType(updatedQuestionType.getType());
            if (updatedQuestionType.getDisplayName() != null) existingQuestionType.setDisplayName(updatedQuestionType.getDisplayName());
            existingQuestionType.setActive(updatedQuestionType.isActive());
            existingQuestionType.setUsername(username);

            return questionTypeRepository.save(existingQuestionType);
        }
        return null;
    }

    // Get a question type by id
    public Optional<QuestionType> getById(Long id) {
        return questionTypeRepository.findById(id);
    }

    // Delete a question type (soft delete can be implemented if needed)
    public void deleteById(Long id) {
        questionTypeRepository.deleteById(id);
    }
}
