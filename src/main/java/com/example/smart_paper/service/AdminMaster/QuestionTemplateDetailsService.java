package com.example.smart_paper.service.AdminMaster;
import com.example.smart_paper.models.QuestionTemplateDetails;
import com.example.smart_paper.repository.AdminMaster.QuestionTemplateDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionTemplateDetailsService {

    @Autowired
    private QuestionTemplateDetailsRepository questionTemplateDetailsRepository;

    public QuestionTemplateDetails save(QuestionTemplateDetails questionTemplateDetails, String username) {
        questionTemplateDetails.setUsername(username);
        return questionTemplateDetailsRepository.save(questionTemplateDetails);
    }

    public QuestionTemplateDetails update(Long id, QuestionTemplateDetails updatedQuestionTemplateDetails, String username) {
        Optional<QuestionTemplateDetails> existingDetailsOpt = questionTemplateDetailsRepository.findById(id);
        if (existingDetailsOpt.isPresent()) {
            QuestionTemplateDetails existingDetails = existingDetailsOpt.get();

            existingDetails.setNoOfQuestion(updatedQuestionTemplateDetails.getNoOfQuestion());
            existingDetails.setPerQuestionMarks(updatedQuestionTemplateDetails.getPerQuestionMarks());
            existingDetails.setSortOrder(updatedQuestionTemplateDetails.getSortOrder());
            existingDetails.setActive(updatedQuestionTemplateDetails.isActive());
            existingDetails.setUsername(username);

            return questionTemplateDetailsRepository.save(existingDetails);
        }
        return null;
    }

    public Optional<QuestionTemplateDetails> getById(Long id) {
        return questionTemplateDetailsRepository.findById(id);
    }

    public void deleteById(Long id) {
        questionTemplateDetailsRepository.deleteById(id);
    }
}
