package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.QuestionTemplate;
import com.example.smart_paper.repository.AdminMaster.QuestionTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionTemplateService {

    @Autowired
    private QuestionTemplateRepository questionTemplateRepository;

    public QuestionTemplate save(QuestionTemplate questionTemplate, String username) {
        questionTemplate.setUsername(username);
        return questionTemplateRepository.save(questionTemplate);
    }

    public QuestionTemplate update(Long id, QuestionTemplate updatedQuestionTemplate, String username) {
        Optional<QuestionTemplate> existingTemplateOpt = questionTemplateRepository.findById(id);
        if (existingTemplateOpt.isPresent()) {
            QuestionTemplate existingTemplate = existingTemplateOpt.get();

            existingTemplate.setName(updatedQuestionTemplate.getName());
            existingTemplate.setTotalMarks(updatedQuestionTemplate.getTotalMarks());
            existingTemplate.setActive(updatedQuestionTemplate.isActive());
            existingTemplate.setUsername(username);

            return questionTemplateRepository.save(existingTemplate);
        }
        return null;
    }

    public Optional<QuestionTemplate> getById(Long id) {
        return questionTemplateRepository.findById(id);
    }

    public void deleteById(Long id) {
        questionTemplateRepository.deleteById(id);
    }
}
