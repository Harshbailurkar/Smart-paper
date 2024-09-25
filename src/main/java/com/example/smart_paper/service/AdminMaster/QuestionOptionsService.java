package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.QuestionOptions;
import com.example.smart_paper.repository.AdminMaster.QuestionOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionOptionsService {

    @Autowired
    private QuestionOptionsRepository questionOptionsRepository;

    public QuestionOptions save(QuestionOptions questionOptions, String username) {
        questionOptions.setUsername(username);
        return questionOptionsRepository.save(questionOptions);
    }

    public QuestionOptions update(Long id, QuestionOptions updatedQuestionOptions, String username) {
        Optional<QuestionOptions> existingQuestionOptionsOpt = questionOptionsRepository.findById(id);
        if (existingQuestionOptionsOpt.isPresent()) {
            QuestionOptions existingQuestionOptions = existingQuestionOptionsOpt.get();

            existingQuestionOptions.setQuesoptions(updatedQuestionOptions.getQuesoptions());
            existingQuestionOptions.setQuestionStatement(updatedQuestionOptions.getQuestionStatement());
            existingQuestionOptions.setAnswer(updatedQuestionOptions.getAnswer());
            existingQuestionOptions.setMarks(updatedQuestionOptions.getMarks());
            existingQuestionOptions.setCorrect(updatedQuestionOptions.isCorrect());
            existingQuestionOptions.setUsername(username);

            return questionOptionsRepository.save(existingQuestionOptions);
        }
        return null;
    }

    public Optional<QuestionOptions> getById(Long id) {
        return questionOptionsRepository.findById(id);
    }

    public void deleteById(Long id) {
        questionOptionsRepository.deleteById(id);
    }
}
